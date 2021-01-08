package com.example.demo.security.util;

import com.example.demo.cache.RedisCache;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created with IDEA
 * @author:hxb
 *
 * @Date: 2019/5/22 13:22
 * @Description: 生成令牌，验证等等一些操作
 *
 */
@Component
public class JwtTokenUtil {

    @Autowired
    private RedisCache redisCache;

    /**
     * 用户名称
     */
    private static final String USERNAME = Claims.SUBJECT;

    private static final String USERID = "userid";
    /**
     * 创建时间
     */
    private static final String CREATED = "created";
    /**
     * 权限列表
     */
    private static final String AUTHORITIES = "authorities";

    private static final String secret = "secret";

    private static final Long expiration = 180000000L;

    private static final String tokenHeader = "Authorization";

    private static final  String tokenHead = "tokenHead";

    /**
     * 从数据声明生成令牌
     *
     * @param claims 数据声明
     * @return 令牌
     */
    private String generateToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 从令牌中获取数据声明
     *
     * @param token 令牌
     * @return 数据声明
     */
    private Claims getClaimsFromToken(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            claims = null;
        }
        return claims;
    }

    /**
     * 生成令牌
     *
     * @return 令牌
     */
    public String generateToken(SecurityUser userDetail) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put(USERID, userDetail.getUserId());
        claims.put(USERNAME, userDetail.getUsername());
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, userDetail.getAuthorities());
        return generateToken(claims);
    }

    /**
     * 从令牌中获取用户名
     *
     * @param token 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFromToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * 判断令牌是否过期
     *
     * @param token 令牌
     * @return 是否过期
     */
    public Boolean isTokenExpired(String token) {
        try {
            Claims claims = getClaimsFromToken(token);
            Date expiration = claims.getExpiration();
            return expiration.before(new Date());
        } catch (Exception e) {
            return true;
        }
    }

    /**
     * 刷新令牌
     *
     * @param token 原令牌
     * @return 新令牌
     */
    public String refreshToken(String token) {
        String refreshedToken;
        try {
            Claims claims = getClaimsFromToken(token);
            claims.put(Claims.ISSUED_AT, new Date());
            claims.put(CREATED, new Date());
            refreshedToken = generateRefreshToken(claims);
        } catch (Exception e) {
            refreshedToken = null;
        }
        return refreshedToken;
    }

    /**
     * refresh_token 刷新
     * @param claims
     * @return
     */
    private String generateRefreshToken(Map<String, Object> claims) {
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 2);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    /**
     * 验证令牌
     *
     * @param token       令牌
     * @param username 用户名
     * @return 是否有效
     */
    public Boolean validateToken(String token, String username) {
        String userName = getUsernameFromToken(token);
        return (userName.equals(username) && !isTokenExpired(token));
    }

    /**
     * 根据请求令牌获取登录认证信息
     *
     * @return 用户名
     */
    public SecurityUser getUserFromToken(HttpServletRequest request) {
        String authHeader = request.getHeader(tokenHeader);
        if (StringUtils.isNotBlank(authHeader) && authHeader.startsWith(tokenHead)) {
            String authToken = authHeader.substring(tokenHead.length());
            String cacheToken = (String)redisCache.get("token_" + authToken);
            if(StringUtils.isNotBlank(cacheToken)){
                Claims claims = getClaimsFromToken(cacheToken);
                if (claims == null) {
                    System.out.println("NNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNNN");
                    return null;
                }
                String username = claims.getSubject();
                if (username == null) {
                    return null;
                }
                // 解析对应的权限以及用户id
                Object authors = claims.get(AUTHORITIES);
                String userId = (String)claims.get(USERID);
                Set<String> perms = new HashSet<>();
                if (authors instanceof List) {
                    for (Object object : (List) authors) {
                        perms.add(((Map) object).get("authority").toString());
                    }
                }
                Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));

                if (!validateTokenAndRefresh(authToken, username)){
                   return null;
                }
                // 未把密码放到jwt
                return new SecurityUser(userId,username,"",authorities);
            }
        }
        return null;
    }



    /**
     *
     *  * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     *  * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面(这时候k、v值一样)，缓存有效期设置为Jwt有效时间的2倍
     *  * 2、当该用户再次请求时，通过层层校验之后会进入到此进行身份验证
     *  * 3、当该用户这次请求jwt生成的token值已经超时，
     *      但该token对应cache中的k还是存在，
     *      则表示该用户一直在操作只是JWT的token失效了，
     *      程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     *  * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     *  * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     *  *       用户过期时间 = Jwt有效时间 * 2。
     *  *
     * @param token 令牌
     * @param username 用户名
     * @return 是否有效
     */
    public Boolean validateTokenAndRefresh(String token, String username) {
        String cacheToken = (String)redisCache.get("token_" + token);
        if(StringUtils.isNotBlank(cacheToken)){
            if(!validateToken(cacheToken)){
                String newCacheToken = refreshToken(cacheToken);
                redisCache.set("token_" + token, newCacheToken, expiration * 2 / 1000);
            }
            return true;
        }
        return false;
    }

    /**
     * 校验token是否有效
     * @param token
     * @return
     */
    public Boolean validateToken(String token) {
        try {
            Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断token在指定时间内是否刚刚刷新过
     * @param token 原token
     * @param time 指定时间（秒）
     */
    private boolean tokenRefreshJustBefore(String token, long time) {
        Claims claims = getClaimsFromToken(token);
        Date created = claims.get(CREATED, Date.class);
        Date refreshDate = new Date();
        Date afterDate = new Date(refreshDate.getTime() + time);
        //刷新时间在创建时间的指定时间内
        return refreshDate.after(created) && refreshDate.before(afterDate);
    }

    /**
     * 根据请求令牌获取登录认证信息
     * @param request
     * @return 用户
     */
    public SecurityUser getUserFromTokenTemp(HttpServletRequest request){
        String token = getRequestHeader(request);
        if(StringUtils.isNotBlank(token)){
            String cacheToken = (String)redisCache.get("token_" + token);
            if(StringUtils.isNotBlank(cacheToken)){
                if(validateToken(token)){
                    return getSecurityUserFromToken(token);
                }else if(validateToken(cacheToken)){
                    cacheToken = refreshToken(cacheToken);
                    System.out.println(cacheToken);
                    redisCache.set("token_" + token, cacheToken, expiration * 2 / 1000);
                    return getSecurityUserFromToken(cacheToken);
                } else{
                    System.out.println(validateToken(cacheToken));
                }
            }
        }
        return null;
    }

    /**
     * 获取请求中的token
     * @param request
     * @return
     */
    public String getRequestHeader(HttpServletRequest request) {
        String authToken = request.getHeader(tokenHeader);
        String token = null;
        if (StringUtils.isNotBlank(authToken) && authToken.startsWith(tokenHead)) {
            token = authToken.substring(tokenHead.length());
        }
        return token;
    }

    /**
     * 从缓存token中获取用户信息
     *
     * @param cacheToken
     * @return
     */
    public SecurityUser getSecurityUserFromToken(String cacheToken){
        Claims claims = getClaimsFromToken(cacheToken);
        String username = claims.getSubject();
        Object authors = claims.get(AUTHORITIES);
        String userId = (String)claims.get(USERID);
        Set<String> perms = new HashSet<>();
        if (authors instanceof List) {
            for (Object object : (List) authors) {
                perms.add(((Map) object).get("authority").toString());
            }
        }
        Collection<? extends GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(perms.toArray(new String[0]));
        return  new SecurityUser(userId,username,"",authorities);
    }

    /**
     * 登录时创建刷新token
     * @param userDetails
     * @return
     */
    public String generateTokenRefreshToken(SecurityUser userDetails) {
        Map<String, Object> claims = new HashMap<>(4);
        claims.put(USERID, userDetails.getUserId());
        claims.put(USERNAME, userDetails.getUsername());
        claims.put(CREATED, new Date());
        claims.put(AUTHORITIES, userDetails.getAuthorities());
        Date expirationDate = new Date(System.currentTimeMillis() + expiration * 2);
        return Jwts.builder().setClaims(claims).setExpiration(expirationDate).signWith(SignatureAlgorithm.HS512, secret).compact();
    }


}

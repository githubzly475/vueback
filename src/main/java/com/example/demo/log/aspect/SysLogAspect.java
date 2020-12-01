package com.example.demo.log.aspect;

import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.SysLog;
import com.example.demo.log.event.SysLogEvent;
import com.example.demo.log.util.LogUtil;
import com.example.demo.utils.IpAddressUtil;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

/**
 * @Classname SysLogAspect
 * @Description 系统日志切面
 * @Author N469
 * @Date 2019-12-27 10:52
 * @Version 1.0
 *
 * ①切面注解得到请求数据 -> ②发布监听事件 -> ③异步监听日志入库
 *
 */
@Slf4j
@Aspect
@Component
public class SysLogAspect {

    private final ThreadLocal<SysLog> sysLogThreadLocal = new ThreadLocal<>();

    @Autowired
    private UserCache userCache;

    /**
     * 事件发布是由ApplicationContext对象管控的，我们发布事件前需要注入ApplicationContext对象调用publishEvent方法完成事件发布
     **/
    @Autowired
    private ApplicationContext applicationContext;

    /***
     * 定义controller切入点拦截规则，拦截SysLog注解的方法
     */
    @Pointcut("@annotation(com.example.demo.log.annotation.SysLog)")
    public void sysLogAspect() {

    }

    /***
     * 拦截控制层的操作日志
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Before(value = "sysLogAspect()")
    public void recordLog(JoinPoint joinPoint) throws Throwable {
        SysLog sysLog = new SysLog();
        //将当前实体保存到threadLocal
        sysLogThreadLocal.set(sysLog);
        // 开始时间
        long beginTime = Instant.now().toEpochMilli();
        HttpServletRequest request = ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        sysLog.setId(UUIDUtils.get32UUID());
        sysLog.setUserName(userCache.getUsername());
        sysLog.setActionUrl(request.getRequestURI());
        sysLog.setStartTime(new Date());
        sysLog.setRequestIp(IpAddressUtil.getIpAddress(request));
        sysLog.setRequestMethod(request.getMethod());
        sysLog.setActionUa(request.getHeader("user-agent"));
        //获取执行的方法名
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        // 类名
        sysLog.setClassPath(joinPoint.getTarget().getClass().getName());
        sysLog.setActionMethod(joinPoint.getSignature().getName());
        sysLog.setFinishTime(new Date());
        // 参数
        //访问目标方法的参数 可动态改变参数值
        Object[] args = joinPoint.getArgs();
        // TODO about args need to be processed
        sysLog.setActionParams(Arrays.toString(args));
        sysLog.setDescription(LogUtil.getControllerMethodDescription(joinPoint));
        long endTime = Instant.now().toEpochMilli();
        sysLog.setConsumingTime(endTime - beginTime);
    }

    /**
     * 返回通知
     * @param ret
     * @throws Throwable
     */
    @AfterReturning(returning = "ret", pointcut = "sysLogAspect()")
    public void doAfterReturning(Object ret) {
        //得到当前线程的log对象
        SysLog sysLog = sysLogThreadLocal.get();
        // 处理完请求，返回内容
        ResultData r = new ResultData();
        BeanUtils.copyProperties(ret, r);
        if (200 == r.getCode()){
            // 正常返回
            sysLog.setLogType(1);
        } else {
            sysLog.setLogType(2);
            sysLog.setExDetail(r.getMsg());
        }
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        //移除当前log实体
        sysLogThreadLocal.remove();
    }

    /**
     * 异常通知
     * @param e
     */
    @AfterThrowing(pointcut = "sysLogAspect()",throwing = "e")
    public void doAfterThrowable(Throwable e){
        SysLog sysLog = sysLogThreadLocal.get();
        // 异常
        sysLog.setLogType(2);
        // 异常对象
        sysLog.setExDetail(LogUtil.getStackTrace(e));
        // 异常信息
        sysLog.setExDesc(e.getMessage());
        // 发布事件
        applicationContext.publishEvent(new SysLogEvent(sysLog));
        //移除当前log实体
        sysLogThreadLocal.remove();
    }

}

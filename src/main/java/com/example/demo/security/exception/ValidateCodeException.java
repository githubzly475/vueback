package com.example.demo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Classname ValidateCodeException
 * @Description TODO
 * @Author N469
 * @Date 2019-12-24 10:06
 * @Version 1.0
 */
public class ValidateCodeException extends AuthenticationException {

    private static final long serialVersionUID = 5022575393500654459L;

    public ValidateCodeException(String message) {
        super(message);
    }
}

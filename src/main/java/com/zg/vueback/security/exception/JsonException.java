package com.zg.vueback.security.exception;

import com.zg.vueback.security.util.ResultEnum;
import lombok.Getter;

/**
 * 错误处理类
 *
 * @author:  N469
 * @date:  2019-12-16
 *
 */
@Getter
public class JsonException extends RuntimeException {

    private final Integer code;

    public JsonException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();
    }

    public JsonException(ResultEnum resultEnum, String message) {
        super(message);
        this.code = resultEnum.getCode();
    }

    public JsonException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}

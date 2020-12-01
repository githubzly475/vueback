package com.example.demo.log.event;

import com.example.demo.entity.system.SysLog;
import org.springframework.context.ApplicationEvent;

/**
 * @Classname SysLogEvent
 * @Description 系统日志事件
 * @Author N469
 * @Date 2019-12-27 11:05
 * @Version 1.0
 */
public class SysLogEvent extends ApplicationEvent {

    public SysLogEvent(SysLog sysLog) {
        super(sysLog);
    }
}

package com.example.demo.log.event;

import com.example.demo.entity.system.SysLog;
import com.example.demo.service.system.SysLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @Classname SysLogListener
 * @Description 注解形式的监听 异步监听日志事件
 * @Author N469
 * @Date 2019-12-27 11:15
 * @Version 1.0
 */
@Slf4j
@Component
public class SysLogListener {

    @Autowired
    private SysLogService sysLogService;

    @Async
    @Order
    @EventListener(SysLogEvent.class)
    public void saveSysLog(SysLogEvent event) {
        SysLog sysLog = (SysLog) event.getSource();
        // 保存日志
//        TODO
//        sysLogService.save(sysLog);
    }
}

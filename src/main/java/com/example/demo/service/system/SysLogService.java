package com.example.demo.service.system;


import com.example.demo.entity.system.SysLog;

/**
 * 系统日志Service
 *
 * @author: N469
 * @date: 2019-12-04 14:45
 */
public interface SysLogService {

    /**
     * 存储日志
     * @param sysLog
     */
    void save(SysLog sysLog);

}

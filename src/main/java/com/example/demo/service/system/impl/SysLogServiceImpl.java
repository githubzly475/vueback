package com.example.demo.service.system.impl;

import com.example.demo.entity.system.SysLog;
import com.example.demo.mapper.system.SysLogMapper;
import com.example.demo.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 系统日志实现类
 *
 * @author: N469
 * @date: 2019-12-27 11:17
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogMapper sysLogMapper;

    @Override
    public void save(SysLog sysLog) {
        sysLogMapper.insert(sysLog);
    }

}

package com.example.demo.mapper.system;

import com.example.demo.entity.system.SysLog;
import org.springframework.stereotype.Repository;

/**
 * 系统日志mapper
 *
 * @author:  N469
 * @date:  2019-12-27
 *
 */
@Repository
public interface SysLogMapper {

    /**
     * 存储日志
     * @param record
     * @return
     */
    int insert(SysLog record);

    SysLog selectByPrimaryKey(String id);

}

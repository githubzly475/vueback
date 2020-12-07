package com.example.demo.service.system;

import com.example.demo.entity.system.SysUser;
import com.example.demo.utils.ResultData;

import java.util.Set;

/**
 * 字典Service
 *
 * @author: N469
 * @date: 2019-12-04 14:45
 */
public interface SysDictService {

    /**
     * 获取字典列表-分页、关键字查询
     *
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param code code
     * @param name name
     * @return ResultData
     */
    ResultData getKeyList(Integer pageNo, Integer pageSize, String code, String name);

    /**
     * 获取某字典下字典值
     *
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param keyId keyId
     * @param code code
     * @param name name
     * @return ResultData
     */
    ResultData getValueList(Integer pageNo, Integer pageSize, String keyId, String code, String name);
}

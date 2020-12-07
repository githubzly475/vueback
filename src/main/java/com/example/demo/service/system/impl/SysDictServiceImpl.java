package com.example.demo.service.system.impl;

import com.example.demo.entity.sysDictKey.SysDictKey;
import com.example.demo.mapper.sysDictKey.SysDictKeyMapper;
import com.example.demo.service.system.SysDictService;
import com.example.demo.utils.ResultData;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 系统字典表实现类
 *
 * @author: N469
 * @date: 2019-12-27 11:17
 */
@Service
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictKeyMapper sysDictKeyMapper;


    /**
     * 获取字典列表-分页、关键字查询
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @param code     code
     * @param name     name
     * @return ResultData
     */
    @Override
    public ResultData getKeyList(Integer pageNo, Integer pageSize, String code, String name) {
        SysDictKey sysDictKey = new SysDictKey();
        sysDictKey.setKeyCode(code);
        sysDictKey.setKeyCode(name);
        RowBounds rowBounds = new RowBounds(pageNo, pageSize);
        sysDictKeyMapper.selectByExampleAndRowBounds(sysDictKey, rowBounds);
        return null;
    }

    /**
     * 获取某字典下字典值
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @param keyId    keyId
     * @param code     code
     * @param name     name
     * @return ResultData
     */
    @Override
    public ResultData getValueList(Integer pageNo, Integer pageSize, String keyId, String code, String name) {
        return null;
    }
}

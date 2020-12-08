package com.example.demo.service.system;

import com.example.demo.entity.sysDictKey.SysDictKey;
import com.example.demo.entity.sysDictValue.SysDictValue;
import com.example.demo.utils.ResultData;

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
     * @param sysDictKey sysDictKey
     * @return ResultData
     */
    ResultData getKeyList(Integer pageNo, Integer pageSize, SysDictKey sysDictKey);

    /**
     * 新增数据
     * @param sysDictKey sysDictKey
     * @return
     */
    ResultData saveKey(SysDictKey sysDictKey);

    /**
     * 更新数据
     * @param sysDictKey sysDictKey
     * @return
     */
    ResultData updateKey(SysDictKey sysDictKey);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultData deleteKeyById(String id);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    ResultData deleteKeyByIdBatch(String ids);

    /***********************************************************************
     *****************************字典值设置*********************************
     ***********************************************************************/

    /**
     * 获取某字典下字典值
     *
     * @param pageNo pageNo
     * @param pageSize pageSize
     * @param sysDictValuee sysDictValuee
     * @return ResultData
     */
    ResultData getValueList(Integer pageNo, Integer pageSize, SysDictValue sysDictValuee);

    /**
     * 新增
     * @param sysDictValue sysDictValue
     * @return
     */
    ResultData saveValue(SysDictValue sysDictValue);

    /**
     * 修改
     * @param sysDictValue sysDictValue
     * @return
     */
    ResultData updateValue(SysDictValue sysDictValue);

    /**
     * 删除
     * @param id
     * @return
     */
    ResultData deleteValueById(String id);

}

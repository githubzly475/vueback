package com.example.demo.service.system.impl;

import com.example.demo.cache.UserCache;
import com.example.demo.entity.sysDictKey.SysDictKey;
import com.example.demo.entity.sysDictValue.SysDictValue;
import com.example.demo.mapper.sysDictKey.SysDictKeyMapper;
import com.example.demo.mapper.sysDictValue.SysDictValueMapper;
import com.example.demo.service.system.SysDictService;
import com.example.demo.utils.DateUtil;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import javax.annotation.Resource;
import java.util.List;

/**
 * 系统字典表实现类
 *
 * @author: N469
 * @date: 2019-12-27 11:17
 */
@Service
@Transactional(rollbackFor = {Exception.class})
public class SysDictServiceImpl implements SysDictService {

    @Resource
    private SysDictKeyMapper sysDictKeyMapper;
    @Resource
    private SysDictValueMapper sysDictValueMapper;
    @Resource
    private UserCache userCache;

    /**
     * 获取字典列表-分页、关键字查询
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @param sysDictKey     sysDictKey
     * @return ResultData
     */
    @Override
    public ResultData getKeyList(Integer pageNo, Integer pageSize, SysDictKey sysDictKey) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysDictKey> sysDictKeys = sysDictKeyMapper.queryKeyList(sysDictKey);
        return ResultData.ok(sysDictKeys);
    }

    /**
     * 新增数据
     *
     * @param sysDictKey sysDictKey
     * @return
     */
    @Override
    public ResultData saveKey(SysDictKey sysDictKey) {
        sysDictKey.setId(UUIDUtils.getUUID());
        sysDictKey.setCreateBy(userCache.getUserId());
        sysDictKey.setCreateTime(DateUtil.getCurrentLocalDateTime());
        if(sysDictKeyMapper.insertSelective(sysDictKey) > 0){
            return ResultData.ok();
        }
        return ResultData.error("添加失败");
    }

    /**
     * 更新数据
     *
     * @param sysDictKey sysDictKey
     * @return
     */
    @Override
    public ResultData updateKey(SysDictKey sysDictKey) {
        sysDictKey.setUpdateBy(userCache.getUserId());
        sysDictKey.setUpdateTime(DateUtil.getCurrentLocalDateTime());
        if(sysDictKeyMapper.updateByPrimaryKeySelective(sysDictKey) > 0){
            return ResultData.ok();
        }
        return ResultData.error("修改失败");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public ResultData deleteKeyById(String id) {
        if(sysDictKeyMapper.deleteByPrimaryKey(id) > 0){
            return ResultData.ok();
        }
        return ResultData.error("删除失败！");
    }

    /**
     * 批量删除
     *
     * @param ids
     * @return
     */
    @Override
    @Transactional(rollbackFor = {Exception.class})
    public ResultData deleteKeyByIdBatch(String ids) {
        try {
            String[] idArr = ids.split(",");
            sysDictKeyMapper.batchDeleteById(idArr);
        } catch (Exception e) {
            e.printStackTrace();
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return ResultData.error("删除失败！");
        }
        return ResultData.ok();
    }


    /***********************************************************************
     *****************************字典值设置*********************************
     ***********************************************************************/

    /**
     * 获取某字典下字典值
     *
     * @param pageNo   pageNo
     * @param pageSize pageSize
     * @param sysDictValue    sysDictValue
     * @return ResultData
     */
    @Override
    public ResultData getValueList(Integer pageNo, Integer pageSize, SysDictValue sysDictValue) {
        PageHelper.startPage(pageNo, pageSize);
        List<SysDictKey> sysDictKeys = sysDictValueMapper.queryValueList(sysDictValue);
        return ResultData.ok(sysDictKeys);
    }

    /**
     * 新增
     *
     * @param sysDictValue sysDictValue
     * @return
     */
    @Override
    public ResultData saveValue(SysDictValue sysDictValue) {
        sysDictValue.setId(UUIDUtils.getUUID());
        sysDictValue.setCreateBy(userCache.getUserId());
        sysDictValue.setCreateTime(DateUtil.getCurrentLocalDateTime());
        if(sysDictValueMapper.insertSelective(sysDictValue) > 0){
            return ResultData.ok();
        }
        return ResultData.error("添加失败");
    }

    /**
     * 修改
     *
     * @param sysDictValue sysDictValue
     * @return
     */
    @Override
    public ResultData updateValue(SysDictValue sysDictValue) {
        sysDictValue.setUpdateBy(userCache.getUserId());
        sysDictValue.setUpdateTime(DateUtil.getCurrentLocalDateTime());
        if(sysDictValueMapper.updateByPrimaryKeySelective(sysDictValue) > 0){
            return ResultData.ok();
        }
        return ResultData.error("修改失败");
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    @Override
    public ResultData deleteValueById(String id) {
        if(sysDictValueMapper.deleteByPrimaryKey(id) > 0){
            return ResultData.ok();
        }
        return ResultData.error("删除失败！");
    }
}

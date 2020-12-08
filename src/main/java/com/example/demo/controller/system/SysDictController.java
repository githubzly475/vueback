package com.example.demo.controller.system;

import com.example.demo.entity.sysDictKey.SysDictKey;
import com.example.demo.entity.sysDictValue.SysDictValue;
import com.example.demo.service.system.SysDictService;
import com.example.demo.utils.ResultData;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 系统字典表
 *
 * @author: N469
 * @date: 2020-01-13 09:10
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {

    /**
     * 引入服务
     */
    @Resource
    private SysDictService sysDictService;

    /**
     * 字典列表分页条件查询创建时间倒序显示
     *
     * @param pageNo 页码
     * @param pageSize 页大小
     * @param sysDictKey keyCode：精确查找
     *                   keyName：模糊查找
     * @return
     */
    @GetMapping("list")
    public ResultData getDictList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  SysDictKey sysDictKey){
        return sysDictService.getKeyList(pageNo, pageSize, sysDictKey);
    }

    /**
     * 新增
     * @param sysDictKey sysDictKey
     * @return
     */
    @PostMapping("insert")
    public ResultData insertKeyData(@RequestBody SysDictKey sysDictKey){
        return sysDictService.saveKey(sysDictKey);
    }

    /**
     * 修改
     * @param sysDictKey sysDictKey
     * @return
     */
    @PutMapping("update")
    public ResultData updateKeyData(@RequestBody SysDictKey sysDictKey){
        return sysDictService.updateKey(sysDictKey);
    }

    /**
     * 主键删除
     * @param id id
     * @return
     */
    @DeleteMapping("delete")
    public ResultData deleteKeyData(String id){
        return sysDictService.deleteKeyById(id);
    }


    /**
     * 批量 主键删除
     * @param id id
     * @return
     */
    @DeleteMapping("delete/batch")
    public ResultData deleteKeyDataBatch(String ids){
        return sysDictService.deleteKeyByIdBatch(ids);
    }





    /***********************************************************************
     *****************************字典值设置*********************************
     ***********************************************************************/

    /**
     * 字典下字典值列表分页条件查询创建排序值升序显示
     *
     * @param pageNo 页码
     * @param pageSize 页大小
     * @param sysDictValue keyId: 必填字典主键
     *                     valueCode：精确查找
     *                     valueName：模糊查找
     * @return
     */
    @GetMapping("value/list")
    public ResultData getDictValueList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                       SysDictValue sysDictValue){
        return sysDictService.getValueList(pageNo, pageSize, sysDictValue);
    }

    /**
     * 新增
     * @param sysDictValue sysDictValue
     * @return
     */
    @PostMapping("value/insert")
    public ResultData insertValueData(@RequestBody SysDictValue sysDictValue){
        return sysDictService.saveValue(sysDictValue);
    }

    /**
     * 修改
     * @param sysDictValue sysDictValue
     * @return
     */
    @PutMapping("value/update")
    public ResultData updateValueData(@RequestBody SysDictValue sysDictValue){
        return sysDictService.updateValue(sysDictValue);
    }

    /**
     * 主键删除
     * @param id id
     * @return
     */
    @DeleteMapping("value/delete")
    public ResultData deleteValueData(String id){
        return sysDictService.deleteValueById(id);
    }

}

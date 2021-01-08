package com.example.demo.controller.system;

import com.example.demo.cache.UserCache;
import com.example.demo.entity.system.sysdept.SysDept;
import com.example.demo.log.annotation.SysLog;
import com.example.demo.mapper.system.sysdept.SysDeptMapper;
import com.example.demo.utils.DeptTree;
import com.example.demo.utils.ResultData;
import com.example.demo.utils.UUIDUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 此处请修改
 *
 * @author: N469
 * @date: 2019-12-19 13:18
 */
@RestController
@RequestMapping("sysDept")
public class SysDeptController {
/*
    @SysLog(description = "查询部门列表")
    @PreAuthorize("hasAuthority('sys:user:dept')")
    @RequestMapping("get/dept/list")
    public ResultData getDeptList(){
        System.out.println("___________________________________________________________");
        return ResultData.ok();
    }
*/

    /**
     * 根据id删除部门信息
     *
     * @return
     */
/*
    @DeleteMapping("dept/{id}")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public ResultData delete(@PathVariable("id") Integer id) {
        return ResultData.ok();
    }
*/

    @Autowired
    private SysDeptMapper sysDeptMapper;
    @Autowired
    private UserCache userCache;

    @RequestMapping("/queryDeptList")
    public ResultData queryDeptList(@RequestParam(value = "page", required = false, defaultValue = "1")Integer page,
                                    @RequestParam(value = "pageSize", required = false, defaultValue = "5") Integer pageSize,
                                    SysDept sysDept){
        HashMap<String, Object> params = new HashMap<>();
        if(null !=sysDept){
            if(null !=sysDept.getDeptName() && StringUtils.isNotBlank(sysDept.getDeptName())){
                params.put("deptName",sysDept.getDeptName());
            }
        }
        PageHelper.startPage(page,pageSize);
        List<SysDept> sysDepts = sysDeptMapper.querySysDept(params);
        PageInfo<SysDept> sysDeptPageInfo = new PageInfo<>(sysDepts);

        return ResultData.ok("成功",sysDeptPageInfo);
    }

    @RequestMapping("/add")
    public ResultData add(@RequestBody SysDept sysDept){
        if(null !=sysDept){
            if(null !=sysDept.getId() && StringUtils.isNotBlank(sysDept.getId())){
                sysDeptMapper.updateByPrimaryKeySelective(sysDept);
            }else {
                sysDept.setId(UUIDUtils.get32UUID());
                sysDept.setCreateTime(new Date());
                sysDept.setCreateBy(userCache.getUserId());
                sysDeptMapper.insertSelective(sysDept);
            }

        }

        return ResultData.ok();

    }

    @RequestMapping("/queryDeptById")
    public ResultData queryDeptById(String id){

        SysDept sysDept = sysDeptMapper.selectByPrimaryKey(id);

        return ResultData.ok(sysDept);

    }
    @RequestMapping("/getAllDept")
    public ResultData getAllDept(){

        List<SysDept> list = sysDeptMapper.getAllDept();

        return ResultData.ok(list);

    }


    @RequestMapping("/getDetpTree")
    public ResultData getDetpTree(){
        List<DeptTree> childreByParentId = sysDeptMapper.getChildreByParentId("-1");
        List<DeptTree> deptTrees = makeTree(childreByParentId);
        return ResultData.ok(deptTrees);
    }


    public  List<DeptTree> makeTree(List<DeptTree> list) {
        for (DeptTree d:list) {
           List<DeptTree> children=sysDeptMapper.getChildreByParentId(d.getId());
           d.setChildren(children);
           makeTree(children);
        }
        return list;

    }


}

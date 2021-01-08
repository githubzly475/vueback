package com.example.demo.mapper.system.sysdept;

import com.example.demo.entity.system.sysdept.SysDept;
import com.example.demo.utils.DeptTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;
import java.util.Map;

@Repository
public interface SysDeptMapper extends Mapper<SysDept> {

    List<SysDept>  querySysDept(Map params);


    List<DeptTree> getChildreByParentId(@Param("parentId") String parentId);

    List<SysDept> getAllDept();
}

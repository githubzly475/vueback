package com.example.demo.controller.system;

import com.example.demo.log.annotation.SysLog;
import com.example.demo.utils.ResultData;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 此处请修改
 *
 * @author: N469
 * @date: 2019-12-19 13:18
 */
@RestController
@RequestMapping("sysDept")
public class SysDeptController {
//
//    @Autowired
//    private SysDeptService sysDeptService;
    @SysLog(description = "查询部门列表")
    @PreAuthorize("hasAuthority('sys:user:dept')")
    @RequestMapping("get/dept/list")
    public ResultData getDeptList(){
//        int a = 1 / 0;
        System.out.println("___________________________________________________________");
        return ResultData.ok();
    }

    /**
     * 根据id删除部门信息
     *
     * @return
     */
    @DeleteMapping("dept/{id}")
    @PreAuthorize("hasAuthority('sys:dept:delete')")
    public ResultData delete(@PathVariable("id") Integer id) {
        return ResultData.ok();
    }


}

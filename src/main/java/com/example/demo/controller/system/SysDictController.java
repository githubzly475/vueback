package com.example.demo.controller.system;

import com.example.demo.service.system.SysDictService;
import com.example.demo.utils.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @Resource
    private SysDictService sysDictService;

    @GetMapping("list")
    public ResultData getDictList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                  @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                  String code, String name){
        return sysDictService.getKeyList(pageNo, pageSize, code, name);
    }

    @GetMapping("value/list")
    public ResultData getDictValueList(@RequestParam(value = "pageNo", required = false, defaultValue = "1") Integer pageNo,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "10") Integer pageSize,
                                       String keyId, String code, String name){
        return sysDictService.getValueList(pageNo, pageSize, keyId, code, name);
    }

}

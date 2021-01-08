package com.example.demo.controller.system;

import com.example.demo.entity.system.Temp;
import com.example.demo.utils.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 系统字典表
 *
 * @author: N469
 * @date: 2020-01-13 09:10
 */
@RestController
@RequestMapping("sys/dict")
public class SysDictController {

    @GetMapping("list")
    public ResultData getDictList(String code, String name, Integer pageNo, Integer pageSize){
        List<Temp> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++){
            list.add(new Temp(i+"","字典Name"+i,"字典Code"+i));
        }
        return ResultData.ok(list);
    }

    @GetMapping("value/list")
    public ResultData getDictValueList(String keyId, String code, String name, Integer pageNo, Integer pageSize){
        List<Temp> list = new ArrayList<>();
        for (int i = 0; i <= 9; i++){
            list.add(new Temp(i+""+keyId,"字典Name"+i,"字典Code"+i));
        }
        return ResultData.ok(list);
    }

}

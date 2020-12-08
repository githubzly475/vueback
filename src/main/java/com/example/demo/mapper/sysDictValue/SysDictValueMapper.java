package com.example.demo.mapper.sysDictValue;

import com.example.demo.entity.sysDictKey.SysDictKey;
import com.example.demo.entity.sysDictValue.SysDictValue;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author
 */
@Repository
public interface SysDictValueMapper extends Mapper<SysDictValue> {

    /**
     * 获取某字典下字典值列表
     * @param sysDictValue
     * @return
     */
    List<SysDictKey> queryValueList(SysDictValue sysDictValue);
}

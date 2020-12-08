package com.example.demo.mapper.sysDictKey;

import com.example.demo.entity.sysDictKey.SysDictKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @author
 */
@Repository
public interface SysDictKeyMapper extends Mapper<SysDictKey> {

    /**
     * 分页列表查询
     *
     * @param sysDictKey
     * @return
     */
    List<SysDictKey> queryKeyList(SysDictKey sysDictKey);

    /**
     * 批量删除
     * @param ids
     */
    void batchDeleteById(@Param("ids") String[] ids);
}

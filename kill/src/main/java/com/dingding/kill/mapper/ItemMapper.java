package com.dingding.kill.mapper;

import com.dingding.kill.entity.Item;
import org.apache.ibatis.annotations.Param;

/**
 * @author liudingding
 * @ClassName ItemMapper
 * @description
 * @date 2020/3/31 14:31
 * Version 1.0
 */
public interface ItemMapper {

    /**
     * 删除商品
     * @param id
     * @return
     */
    int deleteById(@Param("id") Integer id);

    /**
     * 添加商品
     * @param record
     * @return
     */
    int insert(@Param("record") Item record);

    int insertSelective(@Param("record") Item record);

    /**
     * 通过主键查询商品
     * @param id
     * @return
     */
    Item selectById(@Param("id") Integer id);

    /**
     * 更新商品
     * @param record
     * @return
     */
    int updateByIdSelective(@Param("record") Item record);

    int updateById(Item record);
}

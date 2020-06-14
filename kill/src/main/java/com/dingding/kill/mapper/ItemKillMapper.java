package com.dingding.kill.mapper;

import com.dingding.kill.entity.ItemKill;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liudingding
 * @ClassName ItemKillMapper
 * @description
 * @date 2020/3/31 14:30
 * Version 1.0
 */
@Mapper
@Repository
public interface ItemKillMapper {

    /**
     * 查询待秒杀的活动商品列表
     * @return
     */
    List<ItemKill> selectAll();

    /**
     *  根据主键查询秒杀商品
     *  商品表和待秒杀表关联
     * @param id
     * @return
     */
    ItemKill selectById(@Param("id") Integer id);

    /**
     * 减库存
     * @param killId
     * @return
     */
    int updateKillItem(@Param("killId") Integer killId);

    ItemKill selectByIdV2(@Param("id") Integer id);

    int updateKillItemV2(@Param("killId") Integer killId);
}

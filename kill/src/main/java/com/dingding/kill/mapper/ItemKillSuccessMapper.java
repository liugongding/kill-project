package com.dingding.kill.mapper;

import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.entity.ItemKillSuccess;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author liudingding
 * @ClassName ItemKillSuccessMapper
 * @description
 * @date 2020/3/31 14:31
 * Version 1.0
 */
@Mapper
@Repository
public interface ItemKillSuccessMapper {
    /**
     * 通过秒杀成功生成的订单编号删除秒杀成功记录
     * @param code
     * @return
     */
    int deleteByPrimaryKey(String code);

    /**
     * 插入秒杀成功记录
     * @param record
     * @return
     */
    int insert(ItemKillSuccess record);

    /**
     * 插入秒杀成功记录
     * @param record
     * @return
     */
    int insertSelective(ItemKillSuccess record);

    /**
     * 通过主键(code)查询秒杀成功记录
     * @param code
     * @return
     */
    ItemKillSuccess selectByPrimaryKey(String code);

    /**
     * 成功秒杀记录
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ItemKillSuccess record);

    int updateByPrimaryKey(ItemKillSuccess record);

    /**
     * 根据秒杀活动id(商品id)和用户id查询用户抢购数量
     * @param killId
     * @param userId
     * @return
     */
    int countByKillUserId(@Param("killId") Integer killId, @Param("userId") Integer userId);

    /**
     * 根据订单编号查询订单
     * @param code
     * @return
     */
    KillSuccessUserInfo selectByCode(@Param("code") String code);

    /**
     * 将过期订单的状态改为-1（无效）
     * @param code
     * @return
     */
    int expireOrder(@Param("code") String code);

    /**
     * 查询过期订单
     * @return
     */
    List<ItemKillSuccess> selectExpireOrders();

    /**
     *
     * @param killId
     * @param userId
     * @return
     */
    KillSuccessUserInfo selectByKillIdUserId(@Param("killId") Integer killId,@Param("userId") Integer userId);
}

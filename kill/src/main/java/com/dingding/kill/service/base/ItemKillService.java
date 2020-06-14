package com.dingding.kill.service.base;

import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.entity.ItemKill;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ItemKillService {

    /**
     * 查询待秒杀的活动商品列表
     * @return
     */
    List<ItemKill> selectAll() throws Exception;

    /**
     *  根据主键查询秒杀商品
     * @param id
     * @return
     */
    ItemKill selectById(@Param("id") Integer id) throws Exception;

    /**
     * 根据订单号查询成功秒杀商品详情
     * @param code
     * @return
     */
    KillSuccessUserInfo selectByCode(@Param("code") String code) throws Exception;
}

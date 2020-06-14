package com.dingding.kill.service.base.impl;

import com.dingding.kill.dto.KillSuccessUserInfo;
import com.dingding.kill.entity.ItemKill;
import com.dingding.kill.mapper.ItemKillMapper;
import com.dingding.kill.mapper.ItemKillSuccessMapper;
import com.dingding.kill.service.base.ItemKillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author liudingding
 * @ClassName ItemKillServiceImpl
 * @description
 * @date 2020/3/31 16:27
 * Version 1.0
 */
@Service
@Slf4j
public class ItemServiceImpl implements ItemKillService {

    @Autowired
    private ItemKillMapper itemKillMapper;

    @Autowired
    private ItemKillSuccessMapper itemKillSuccessMapper;

    @Override
    public List<ItemKill> selectAll() throws Exception {
        List<ItemKill> itemKills = itemKillMapper.selectAll();
        if (itemKills.isEmpty()) {
            throw new Exception("获取秒杀详情-待秒杀商品记录不存在");
        }
        return itemKills;
    }

    @Override
    public ItemKill selectById(Integer id) throws Exception {
        ItemKill itemKill = itemKillMapper.selectById(id);
        if (itemKill == null) {
            throw new Exception("获取秒杀详情-待秒杀商品记录不存在");
        }
        return itemKill;
    }

    @Override
    public KillSuccessUserInfo selectByCode(String code) throws Exception{
        KillSuccessUserInfo killSuccessUserInfo = itemKillSuccessMapper.selectByCode(code);
        if (killSuccessUserInfo == null) {
            throw new Exception("获取订单详情失败");
        }
        return killSuccessUserInfo;
    }


}

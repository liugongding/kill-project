package com.dingding.kill.service.base;

/**
 * @author liudingding
 * @ClassName KillService
 * @description 执行秒杀逻辑
 * @date 2020/4/2 0:11
 * Version 1.0
 */
public interface KillService {

    Boolean killItem(Integer killId, Integer userId) throws Exception;
}

package com.dingding.kill.mapper;

import com.dingding.kill.entity.RandomCode;

/**
 * @author liudingding
 * @ClassName RandomCodeMapper
 * @description
 * @date 2020/3/31 14:31
 * Version 1.0
 */
public interface RandomCodeMapper {

    int deleteByPrimaryKey(Integer id);

    int insert(RandomCode record);

    int insertSelective(RandomCode record);

    RandomCode selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RandomCode record);

    int updateByPrimaryKey(RandomCode record);
}


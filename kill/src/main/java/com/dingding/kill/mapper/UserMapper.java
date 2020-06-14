package com.dingding.kill.mapper;

import com.dingding.kill.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author liudingding
 * @ClassName UserMapper
 * @description
 * @date 2020/3/31 14:31
 * Version 1.0
 */
@Mapper
@Repository
public interface UserMapper {

    int deleteByPrimaryKey(Integer id);

    /**
     * 插入注册用户
     * @param record
     * @return
     */
    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    /**
     * 根据用户名查询用户
     * @param username
     * @return
     */
    User selectByUserName(@Param("username") String username);

    User selectByUserNamePsd(@Param("username") String userName, @Param("password") String password);
}

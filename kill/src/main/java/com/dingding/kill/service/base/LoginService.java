package com.dingding.kill.service.base;

import com.dingding.kill.entity.User;
import org.apache.ibatis.annotations.Param;

public interface LoginService {

     /**
      * 插入注册用户
      * @param user
      * @return
      */
     int register(User user);


     /**
      * 根据用户名查询用户
      * @param username
      * @return
      */
     User selectByUserName(@Param("username") String username);


}

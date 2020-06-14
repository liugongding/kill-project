package com.dingding.kill.service.base.impl;

import com.dingding.kill.entity.Audience;
import com.dingding.kill.entity.User;
import com.dingding.kill.enums.StatusCode;
import com.dingding.kill.exception.CustomException;
import com.dingding.kill.mapper.UserMapper;
import com.dingding.kill.response.BaseResponse;
import com.dingding.kill.service.base.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @author liudingding
 * @ClassName LoginServiceImpl
 * @description
 * @date 2020/3/31 21:11
 * Version 1.0
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Autowired
    private Audience audience;

    @Autowired
    private UserMapper userMapper;

    @Override
    public int register(User user) {
        return userMapper.insert(user);
    }

    @Override
    public User selectByUserName(String username) {
        User user = userMapper.selectByUserName(username);
        if (user != null) {
            return user;
        } else {
            log.error("该用户不存在");
            throw new CustomException(StatusCode.USER_NOT_EXIST);
        }
    }

    public BaseResponse login(HttpServletResponse response, User loginUser){
        User user = userMapper.selectByUserName(loginUser.getUsername());
        if (user == null) {
            log.error("该用户不存在");
            throw new CustomException(StatusCode.USER_NOT_EXIST);
        } else {
            if (!user.getPassword().equals(loginUser.getPassword())){
                log.info("password:{}", loginUser.getPassword());
                log.error("用户密码错误");
                throw new CustomException(StatusCode.USER_LOGIN_ERROR);
            } else {
                String userId = UUID.randomUUID().toString();
                //创建token
//                String token = JwtTokenUtil.createJWT(userId, username, password, audience);
                //将token放在响应头
//                response.setHeader(JwtTokenUtil.AUTO_HEADER_KEY, JwtTokenUtil.TOKEN_PREFIX+token);
                //返回给客户端
//                JSONObject result = new JSONObject();
//                result.put("token", token);
                return new BaseResponse(StatusCode.SUCCESS);
            }
        }
    }
}

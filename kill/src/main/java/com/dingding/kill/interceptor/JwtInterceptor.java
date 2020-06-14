//package com.dingding.kill.interceptor;
//
//import com.dingding.kill.annotation.JwtIgnore;
//import com.dingding.kill.entity.Audience;
//import com.dingding.kill.enums.StatusCode;
//import com.dingding.kill.exception.CustomException;
//import com.dingding.kill.token.util.JwtTokenUtil;
//import jodd.util.StringUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpMethod;
//import org.springframework.web.context.support.WebApplicationContextUtils;
//import org.springframework.web.method.HandlerMethod;
//import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
///**
// * @author liudingding
// * @ClassName JwtInterceptor
// * @description
// * @date 2020/4/1 0:12
// * Version 1.0
// */
//@Slf4j
//public class JwtInterceptor extends HandlerInterceptorAdapter {
//
//    @Autowired
//    private Audience audience;
//
//    @Override
//    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object){
//        // 忽略带JwtIgnore注解的请求, 不做后续token认证校验
//        if (object instanceof HandlerMethod) {
//            HandlerMethod handlerMethod = (HandlerMethod) object;
//            JwtIgnore jwtIgnore = handlerMethod.getMethodAnnotation(JwtIgnore.class);
//            if (jwtIgnore != null) {
//                return true;
//            }
//        }
//
//        if (HttpMethod.OPTIONS.equals(request.getMethod())){
//            response.setStatus(HttpServletResponse.SC_OK);
//            return true;
//        }
//
//        // 获取请求头信息authorization信息
//        final String authHeader = request.getHeader(JwtTokenUtil.AUTO_HEADER_KEY);
//        log.info("authHeader: {}", authHeader);
//
//        if (StringUtil.isBlank(authHeader) || !authHeader.startsWith(JwtTokenUtil.TOKEN_PREFIX)) {
//            log.info("## 用户未登陆，请先登陆 ##");
//            throw new CustomException(StatusCode.USER_NOT_LOGGED_IN);
//        }
//
//        //获取token
//        final String token = authHeader.substring(7);
//        if (audience == null) {
//            BeanFactory beanFactory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            audience = (Audience) beanFactory.getBean("audience");
//        }
//        JwtTokenUtil.parseJWT(token, audience.getBase64Secret());
//
//        return true;
//    }
//}

//package com.dingding.kill.token.util;
//
//import com.dingding.kill.exception.CustomException;
//import com.dingding.kill.entity.Audience;
//import com.dingding.kill.enums.StatusCode;
//import io.jsonwebtoken.*;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.crypto.spec.SecretKeySpec;
//import javax.xml.bind.DatatypeConverter;
//import java.security.Key;
//import java.util.Date;
//
///**
// * @author liudingding
// * @ClassName JwtTokenUtil
// * @description
// * @date 2020/3/31 22:52
// * Version 1.0
// */
//@Slf4j
//public class JwtTokenUtil {
//
//    public static final String AUTO_HEADER_KEY = "Authorization";
//
//    public static final String TOKEN_PREFIX = "Bearer";
//
//    /**
//     * 解析token
//     * @param jsonWebToken
//     * @param base64Security
//     * @return
//     */
//    public static Claims parseJWT(String jsonWebToken, String base64Security){
//        try{
//            Claims claims = Jwts.parser()
//                    .setSigningKey(DatatypeConverter.parseBase64Binary(base64Security))
//                    .parseClaimsJws(jsonWebToken).getBody();
//            return claims;
//        } catch (ExpiredJwtException e){
//            log.error("=======token过期========", e.getMessage());
//            throw new CustomException(StatusCode.PERMISSION_TOKEN_EXPIRED);
//        } catch (Exception e){
//            log.error("======token解析异常=====", e.getMessage());
//            throw new CustomException(StatusCode.PERMISSION_TOKEN_INVALID);
//        }
//    }
//
//    /**
//     * 构建jwt
//     * @param userId
//     * @param username
//     * @param role
//     * @param audience
//     * @return
//     */
//    public static String createJWT(String userId, String username, String role, Audience audience){
//        // 使用HS256加密算法
//        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
//
//        long nowMillis = System.currentTimeMillis();
//        Date now = new Date(nowMillis);
//
//        //生成签名秘钥
//        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(audience.getBase64Secret());
//        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());
//
//        //添加构成JWT的参数
//        JwtBuilder builder = Jwts.builder().setHeaderParam("type", "JWT")
//                .claim("role", role)
//                .claim("userId", userId)
//                // 代表这个JWT的主体，即它的所有人
//                .setSubject(username)
//                // 代表这个JWT的签发主体；
//                .setIssuer(audience.getClientId())
//                // 是一个时间戳，代表这个JWT的签发时间
//                .setIssuedAt(new Date())
//                // 代表这个JWT的接收对象；
//                .setAudience(audience.getName())
//                .signWith(signatureAlgorithm, signingKey);
//
//        //添加Token过期时间
//        int TTLMillis = audience.getExpiresSecond();
//        if (TTLMillis >= 0){
//            long expMillis = nowMillis + TTLMillis;
//            Date exp = new Date(expMillis);
//            // 是一个时间戳，代表这个JWT的过期时间；
//            builder.setExpiration(exp)
//                    // 是一个时间戳，代表这个JWT生效的开始时间，意味着在这个时间之前验证JWT是会失败的
//                    .setNotBefore(now);
//        }
//
//        //返回token
//        return builder.compact();
//    }
//
//    /**
//     * 从token获取用户名
//     * @param token
//     * @param base64Security
//     * @return
//     */
//    public static String getUserName(String token, String base64Security){
//        return parseJWT(token, base64Security).getSubject();
//    }
//
//    /**
//     * 从token获取用户id
//     * @param token
//     * @param base64Security
//     * @return
//     */
//    public static String getUserId(String token, String base64Security){
//        String userId = parseJWT(token, base64Security).get("userId", String.class);
//        return Base64Util.decode(userId);
//    }
//
//    /**
//     * 是否过期
//     * @param token
//     * @param base64Security
//     * @return
//     */
//    public static boolean isExpiration(String token, String base64Security){
//        return parseJWT(token, base64Security).getExpiration().before(new Date());
//    }
//}

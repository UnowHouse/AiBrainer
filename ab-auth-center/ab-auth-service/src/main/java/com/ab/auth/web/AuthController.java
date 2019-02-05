package com.ab.auth.web;


import com.ab.auth.config.JwtProperties;
import com.ab.auth.entity.UserInfo;
import com.ab.auth.service.AuthService;
import com.ab.auth.utils.JwtUtils;
import com.ab.commons.utils.CookieUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/*
 *  @项目名：  ai-brainer
 *  @包名：    com.ab.auth.web
 *  @文件名:   AuthController
 *  @创建者:   Unow
 *  @创建时间:  2018/12/26 1:15
 *  @描述：    TODO
 */

@RestController
@EnableConfigurationProperties(JwtProperties.class)
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JwtProperties props;

    /**
     * 登录授权
     *
     * @param username
     * @param password
     * @param request
     * @param response
     * @return
     */
    @PostMapping("accredit")
    public ResponseEntity<Void> login(
            @RequestParam("username") String username,
            @RequestParam("password") String password,
            HttpServletRequest request,
            HttpServletResponse response
    ) {

        String token = authService.login(username, password);

        //将Token写入cookie中
        CookieUtils.newBuilder(response).httpOnly()
                .maxAge(props.getCookieMaxAge())
                .request(request)
                .build(props.getCookieName(), token);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * 验证用户信息
     * @param token
     * @return
     */
    @GetMapping("verify")
    public ResponseEntity<UserInfo> verifyUser(@CookieValue("AB_TOKEN") String token,
                                               HttpServletResponse response,
                                               HttpServletRequest request
                                               ) {
        try {
            // 获取token信息s
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, props.getPublicKey());
            //成功，刷新Token
            String newToken = JwtUtils.generateToken(userInfo, props.getPrivateKey(), props.getExpire());
            //将新的Token写入cookie中，并设置httpOnly
            CookieUtils.newBuilder(response).httpOnly()
                    .maxAge(props.getCookieMaxAge())
                    .request(request)
                    .build(props.getCookieName(), newToken);
            // 成功后直接返回
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            // 抛出异常，证明token无效，直接返回401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    @GetMapping("userInfo")
    public ResponseEntity<UserInfo> getCurrrentUserInfo(@RequestParam("token") String token) {

        try {
            // 获取token信息s
            UserInfo userInfo = JwtUtils.getInfoFromToken(token, props.getPublicKey());
            // 成功后直接返回
            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            // 抛出异常，证明token无效，直接返回401
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }
    }

    /**
     * 注销登录
     *
     * @param token
     * @param response
     * @return
     */
    @GetMapping("logout")
    public ResponseEntity<Void> logout(@CookieValue("AB_TOKEN") String token, HttpServletResponse response) {
        if (StringUtils.isNotBlank(token)) {
            CookieUtils.newBuilder(response).maxAge(0).build(props.getCookieName(), token);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}

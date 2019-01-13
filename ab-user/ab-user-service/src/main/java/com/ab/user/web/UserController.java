package com.ab.user.web;

import com.ab.user.pojo.User;
import com.ab.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.user.web
 *  @文件名:   UserController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 15:44
 *  @描述：    用户登录注册api
 */


@RestController
public class UserController {

    @Autowired
    private UserService userService;


    @GetMapping("check/{data}/{type}")
    public ResponseEntity<Boolean> checkUserData(@PathVariable("data") String data,
                                                 @PathVariable("type") Integer type) {
        return ResponseEntity.ok(userService.checkData(data,type));
    }

    /**
     * 用户注册
     * @param user
     * @param code
     * @return
     */
    @PostMapping("register")
    public ResponseEntity<Void> register(@Valid User user, @RequestParam("code")String code){
        userService.register(user,code);
        return ResponseEntity.status(HttpStatus.CREATED).build();

    }

    /**
     * 发送手机验证码
     * @param phone
     * @return
     */
    @PostMapping("send")
    public ResponseEntity<Void> sendVerifyCode(String phone) {
        userService.sendVerifyCode(phone);
        return ResponseEntity.noContent().build();
    }

    /**
     * 根据用户名和密码查询用户
     * @param username
     * @param password
     * @return
     */
    @GetMapping("query")
    public ResponseEntity<User> queryUser(@RequestParam("username") String username,
                                          @RequestParam("password") String password) {
        return ResponseEntity.ok(userService.queryUser(username, password));
    }

}

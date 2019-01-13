package com.ab.user.pojo;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.user.pojo
 *  @文件名:   User
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 15:23
 *  @描述：    User对象，采用了Hibernate-validator校验，在控制层只需要给本对象注上@Valid注解，即可实现本类中设置的校验方案
 *
 */

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Table(name = "tb_user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String sickname;

    @Length(max = 30, min = 4, message = "用户名长度只能在4-30之间")
    private String username;

    @JsonIgnore
    @Length(max = 30, min = 4, message = "密码长度只能在4-30之间")
    private String password;

    @Pattern(regexp = "^1[35678]\\d{9}$",message = "手机号格式不正确")
    private String phone;

    @JsonIgnore
    private String salt;

    private String identity;
    private Long exp;
    private Date created;
    private String face;
}

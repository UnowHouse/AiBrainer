package com.ab.user.service;

import com.ab.auth.entity.UserInfo;
import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.commons.utils.NumberUtils;
import com.ab.label.pojo.Task;
import com.ab.label.pojo.TaskWork;
import com.ab.user.client.AuthClient;
import com.ab.user.mapper.UserMapper;
import com.ab.user.pojo.User;
import com.ab.user.utils.CodecUtils;
import com.ab.user.vo.Personal;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.user.service
 *  @文件名:   UserService
 *  @创建者:   Unow
 *  @创建时间:  2019/1/1 15:45
 *  @描述：    TODO
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AuthClient authClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

//    @Autowired
//    private AmqpTemplate amqpTemplate;



    static final String KEY_PREFIX = "user:code:phone:";

    /**
     * 注册用户
     * 1.将参数User中的phone与常量KEY_PREFIX拼接，作为key
     *   向redis中获取该key的value，进行匹配，匹配正确继续，失败退出
     * 2.生成密码盐值，拼拼接用户设置的密码与盐值，进行md5加密
     * 3.数据处理完毕，插入数据库
     * 4.删除redis中的key缓存
     * @param user
     * @param code
     */
    public void register(User user, String code) {
        user.setId(null);
        user.setCreated(new Date());

        String key = KEY_PREFIX + user.getPhone();

        String value = stringRedisTemplate.opsForValue().get(key);

        if(!StringUtils.equals(value,code)){
            throw new AbException(ExceptionEnum.USER_CHECK_FAILURE);
        }

        String salt = CodecUtils.generateSalt();
        user.setSalt(salt);

        String md5Hex = CodecUtils.md5Hex(user.getPassword(), user.getSalt());

        user.setPassword(md5Hex);

        int i = userMapper.insert(user);
        if(i != 1){
            throw new AbException(ExceptionEnum.INVALID_PARAM);
        }

        stringRedisTemplate.delete(key);
    }

    /**
     * 发送验证码
     * 1.随机生成验证码
     * 2.存入redis缓存
     * 3.amqp同步发送消息
     * @param phone
     */
    public void sendVerifyCode(String phone) {

        String code = NumberUtils.generateCode(6);
        code = "123456";
        String key = KEY_PREFIX+phone;

        stringRedisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);

        Map<String,String> map = new HashMap<>();
        map.put("code",code);
        map.put("phone",key);

//        amqpTemplate.convertAndSend("ab.sms.exchange","sms.verify.code",map);

    }

    public Boolean checkData(String data, Integer type) {
        User record = new User();
        switch (type) {
            case 1:
                record.setUsername(data);
                break;
            case 2:
                record.setPhone(data);
                break;
            default:
                throw new AbException(ExceptionEnum.INVALID_PARAM);
        }
        int i = userMapper.selectCount(record);
        if(i != 1){
            throw new AbException(ExceptionEnum.USER_CHECK_FAILURE);
        }
        return Boolean.TRUE;
    }

    public User queryUser(String username, String password) {
        User record = new User();
        record.setUsername(username);

        //首先根据用户名查询用户
        User user = userMapper.selectOne(record);

        if (user == null) {
            throw new AbException(ExceptionEnum.USERNAME_OR_PASSWORD_ERROR);
        }

        //检验密码是否正确

        if (!StringUtils.equals(CodecUtils.md5Hex(password, user.getSalt()), user.getPassword())) {
            //密码不正确
            throw new AbException(ExceptionEnum.PASSWORD_NOT_MATCHING);
        }

        return user;
    }

    public Personal personalPage(String token) {

        UserInfo userInfo = authClient.getCurrrentUserInfo(token);
        User user = userMapper.selectByPrimaryKey(userInfo.getId());
        if(user == null)
            throw new AbException(ExceptionEnum.USER_CHECK_FAILURE);
        List<Task> userTasks = userMapper.getUserTasks(userInfo.getId());
        List<TaskWork> userWorks = userMapper.getUserWorks(userInfo.getId());
        Personal personal = new Personal();
        personal.setUser(user);
        personal.setTaskList(userTasks);
        personal.setTaskWorkList(userWorks);
        return personal;
    }
}

package com.ab.sms.listener;

/*
 *  @项目名：  leiyou 
 *  @包名：    com.leyou.listener
 *  @文件名:   SmsListener
 *  @创建者:   Unow
 *  @创建时间:  2018/12/25 11:14
 *  @描述：    TODO
 */

import com.ab.sms.config.SmsProperties;
import com.ab.sms.utils.SmsUtils;
import com.aliyuncs.exceptions.ClientException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
@EnableConfigurationProperties(SmsProperties.class)
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties properties;

    @RabbitListener(
            bindings = @QueueBinding(value = @Queue(value = "ab.sms.queue",durable = "true"),
                    exchange = @Exchange(value="ab.sms.exchange",ignoreDeclarationExceptions = "true"),
                    key = {"sms.verify.code"})
        )
    public void listenSms(Map<String, String> msg) throws ClientException {
        if (msg == null || msg.size() <= 0) {
            // 放弃处理
            return;
        }
        String phone = msg.get("phone");
        String code = msg.get("code");

        if (StringUtils.isBlank(phone) || StringUtils.isBlank(code)) {
            // 放弃处理
            return;
        }
        // 发送消息，暂不可用
        //this.smsUtils.sendSms(phone, code, properties.getSignName(), properties.getVerifyCodeTemplate());
        this.smsUtils.sendSmsTest(phone,code,properties.getSignName(),properties.getVerifyCodeTemplate());
    }

}

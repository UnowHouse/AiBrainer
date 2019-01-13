package com.ab.upload.sms.config;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload.sms.config
 *  @文件名:   FdfsClientImporter
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 17:04
 *  @描述：    TODO
 */

import com.github.tobato.fastdfs.FdfsClientConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.context.annotation.Import;
import org.springframework.jmx.support.RegistrationPolicy;

@Configuration
@Import(FdfsClientConfig.class)
// 解决jmx重复注册bean的问题
@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
public class FdfsClientImporter {
}

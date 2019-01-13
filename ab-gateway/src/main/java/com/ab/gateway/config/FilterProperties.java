package com.ab.gateway.config;

/*
 *  @项目名：  ai-brainer 
 *  @包名：    com.ab.gateway.config
 *  @文件名:   FilterProperties
 *  @创建者:   Unow
 *  @创建时间:  2019/1/2 10:48
 *  @描述：    TODO
 */

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "ab.filter")
public class FilterProperties {
    private List<String> allowPaths;

    public List<String> getAllowPaths() {
        return allowPaths;
    }

    public void setAllowPaths(List<String> allowPaths) {
        this.allowPaths = allowPaths;
    }
}

package com.ab.upload.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload.api
 *  @文件名:   FileApi
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 21:17
 *  @描述：    TODO
 */
public interface FileApi {
    @PostMapping(value = "upload",
            produces = {MediaType.APPLICATION_JSON_UTF8_VALUE},
            consumes =  MediaType.MULTIPART_FORM_DATA_VALUE)
    String uploadImage(MultipartFile files);
}

package com.ab.upload.web;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload.web
 *  @文件名:   FileController
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 17:02
 *  @描述：    TODO
 */

import com.ab.upload.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {

    @Autowired
    private FileService fileService;

    /**
     * 上传图片（多文件）
     * @param files
     * @return
     */
    @PostMapping("upload")
    public ResponseEntity<String> uploadImage(MultipartFile files){
        String image = fileService.uploadImage(files);
        return ResponseEntity.ok(image);
    }
}

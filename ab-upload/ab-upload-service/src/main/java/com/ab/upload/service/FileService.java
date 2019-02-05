package com.ab.upload.service;

import com.ab.commons.enums.ExceptionEnum;
import com.ab.commons.exception.AbException;
import com.ab.upload.sms.config.UploadProperties;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/*
 *  @项目名：  aibrainer 
 *  @包名：    com.ab.upload.service
 *  @文件名:   FileService
 *  @创建者:   Unow
 *  @创建时间:  2019/1/8 17:03
 *  @描述：    TODO
 */


@Slf4j
@Service
@EnableConfigurationProperties(UploadProperties.class)
public class FileService {

    @Autowired
    private FastFileStorageClient fastFileStorageClient;
    @Autowired
    private UploadProperties uploadProperties;


    /**
     * @description 上传图片，分3个步骤，
     *              1.判断文件类型是否符合要求
     *              2.检验文件内容
     *              3.上传
     * @param file
     * @return
     */
    public String uploadImage(MultipartFile file) {
        try {

            //校验文件类型
            String type = file.getContentType();
            if(!uploadProperties.getAllowTypes().contains(type)){
                throw new AbException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            //校验文件内容
            BufferedImage image = ImageIO.read(file.getInputStream());
            if(image == null){
                throw new AbException(ExceptionEnum.INVALID_FILE_TYPE);
            }
            if(file.getSize()>uploadProperties.getMaxSize()){
                System.out.println(file.getSize()+":"+uploadProperties.getMaxSize());
                throw new AbException(ExceptionEnum.FILE_EXCEEDING_MAXIMUM);
            }
            //上传到fastFDS
            String extendsion = StringUtils.substringAfterLast(file.getOriginalFilename(),".");
            StorePath storePath = fastFileStorageClient.uploadFile(file.getInputStream(), file.getSize(), extendsion, null);
            //放回路径
            log.info(file.getName()+"-->"+uploadProperties.getBaseUrl()+storePath.getFullPath());
            return uploadProperties.getBaseUrl()+storePath.getFullPath();


        }catch (IOException e){
            log.error(ExceptionEnum.UPLOAD_FILE_ERROR.getMessage(),e);
            throw new AbException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
    }
}

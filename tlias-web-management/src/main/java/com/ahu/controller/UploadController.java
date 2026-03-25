package com.ahu.controller;

import com.ahu.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {

    private static final String UPLOAD_DIR = "D:\\javaweb\\web-ai-project\\images\\";
    //上传文件
//    @PostMapping("/upload")                         //需与前端请求参数名保持一致 或通过@RequestParam("file") MultipartFile image 进行参数绑定
//    public Result upload(String username, Integer age, MultipartFile file) throws Exception{
//        log.info("上传文件：{},{},{}",username,age,file);
//        if(!file.isEmpty()){
//            file.transferTo(new File("D:\\javaweb\\web-ai-project\\images\\"+file.getOriginalFilename()));
//        }
    //上述方式存在问题：上传文件名相同时，后面的文件会覆盖前面的文件
    @PostMapping("/upload")
    public Result upload(String username, Integer age, MultipartFile file) throws Exception {
        log.info("上传文件：{}, {}, {}", username, age, file);
        String imageUrl = null;

        if (!file.isEmpty()) {
            // 生成唯一文件名
            String originalFilename = file.getOriginalFilename();
            String extName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFileName = UUID.randomUUID().toString().replace("-", "") + extName;
            // 拼接完整的文件路径
            File targetFile = new File(UPLOAD_DIR + uniqueFileName);

            // 如果目标目录不存在，则创建它
            if (!targetFile.getParentFile().exists()) {
                targetFile.getParentFile().mkdirs();
            }
            // 保存文件
            file.transferTo(targetFile);

            //生成可访问的URL
            imageUrl = "http://localhost:90/images/" + uniqueFileName;
        }
        return Result.success(imageUrl);
    }
}

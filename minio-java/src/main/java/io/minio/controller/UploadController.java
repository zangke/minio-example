package io.minio.controller;

import io.minio.*;
import io.minio.config.MinioConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.FastByteArrayOutputStream;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * UploadController.
 *
 * @author andy
 * @date 2021/11/3 6:55
 */
@RestController
@Slf4j
public class UploadController {
    @Resource
    private MinioClient minioClient;
    @Autowired
    private MinioConfig minioConfig;

    /**
     * 上传文件.
     *
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestPart("file") MultipartFile file) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucketName())
                    .build());
            if (!found) {
                minioClient.makeBucket(MakeBucketArgs.builder().bucket(minioConfig.getBucketName())
                        .build());
            }
            PutObjectArgs objectArgs = PutObjectArgs.builder().object(file.getOriginalFilename())
                    .bucket(minioConfig.getBucketName())
                    .contentType(file.getContentType())
                    .stream(file.getInputStream(), file.getSize(), -1).build();
            minioClient.putObject(objectArgs);
            return "ok";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return e.getMessage();
        }
    }

    /**
     * 下载文件.
     *
     * @param filename
     * @param res
     */
    @GetMapping("/download/{filename}")
    public void download(@PathVariable String filename, HttpServletResponse res) {
        try {
            boolean found = minioClient.bucketExists(BucketExistsArgs.builder().bucket(minioConfig.getBucketName())
                    .build());
            if (!found) {
                throw new Exception("Bucket " + minioConfig.getBucketName() + " not exists.");
            } else {
                GetObjectArgs objectArgs = GetObjectArgs.builder().bucket(minioConfig.getBucketName())
                        .object(filename).build();
                try (GetObjectResponse response = minioClient.getObject(objectArgs)) {
                    byte[] buf = new byte[1024];

                    int len;

                    try (FastByteArrayOutputStream os = new FastByteArrayOutputStream()) {
                        while ((len = response.read(buf)) != -1) {
                            os.write(buf, 0, len);
                        }
                        os.flush();

                        byte[] bytes = os.toByteArray();
                        res.setCharacterEncoding("utf-8");
                        // 设置强制下载不打开
                        res.setContentType("application/force-download");
                        res.addHeader("Content-Disposition", "attachment;fileName=" + filename);
                        try (ServletOutputStream stream = res.getOutputStream()) {
                            stream.write(bytes);
                            stream.flush();
                        }
                    }

                } catch (Exception e) {
                    log.error(e.getMessage(), e);
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }
}

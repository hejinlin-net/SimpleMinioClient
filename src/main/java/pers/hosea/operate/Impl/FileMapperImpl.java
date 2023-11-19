package pers.hosea.operate.Impl;

import io.minio.GetObjectArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;
import pers.hosea.config.MinioClientFactory;
import pers.hosea.config.MinioFactoryConfigProperties;
import pers.hosea.operate.FileMapper;

import java.io.*;

@Slf4j
@Component
@Import(MinioClientFactory.class)
@EnableConfigurationProperties(MinioFactoryConfigProperties.class)
public class FileMapperImpl implements FileMapper {
    private MinioFactoryConfigProperties minioFactoryConfigProperties;
    private MinioClient minioClient;

    @Autowired
    public void setMinioClient(MinioClient minioClient) {
        this.minioClient = minioClient;
    }

    @Autowired
    public void setMinioFactoryConfigProperties(MinioFactoryConfigProperties minioFactoryConfigProperties) {
        this.minioFactoryConfigProperties = minioFactoryConfigProperties;
    }

    @Override
    public String uploadFile(String fileFullPath, InputStream inputStream) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object(fileFullPath)
                    .bucket(minioFactoryConfigProperties.getBucket())
                    .stream(inputStream, inputStream.available(), -1)
                    .build();
            minioClient.putObject(putObjectArgs);
            log.info("上传文件 {} 成功", fileFullPath);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return fileFullPath;
    }

    @Override
    public String uploadFile(String filePath, String fileName, InputStream inputStream) {
        String fileFullPath = filePath + "/" + fileName;
        return this.uploadFile(fileFullPath, inputStream);
    }

    @Override
    public InputStream downloadFile(String fileFullPath) {
        InputStream inputStream = null;
        try {
            GetObjectArgs getObjectArgs = GetObjectArgs.builder()
                    .bucket(minioFactoryConfigProperties.getBucket())
                    .object(fileFullPath)
                    .build();
            inputStream = minioClient.getObject(getObjectArgs);
            log.info("下载文件 {} 成功", fileFullPath);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return inputStream;
    }

    @Override
    public byte[] downloadFileToByteArray(String fillFullPath) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        InputStream inputStream = downloadFile(fillFullPath);
        byte[] buff = new byte[80];
        int length;
        try {
            while ((length = inputStream.read(buff, 0, 80)) > 0) {
                byteArrayOutputStream.write(buff, 0, length);
            }
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return byteArrayOutputStream.toByteArray();
    }

    @Override
    public boolean deleteFile(String fillFullPath) {
        boolean flag = false;
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs
                    .builder()
                    .bucket(minioFactoryConfigProperties.getBucket())
                    .object(fillFullPath)
                    .build();
            minioClient.removeObject(removeObjectArgs);
            log.info("删除文件 {} 成功", fillFullPath);
            flag = true;
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return flag;
    }
}

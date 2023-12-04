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
import pers.hosea.enums.ContentType;
import pers.hosea.config.MinioClientFactory;
import pers.hosea.config.MinioFactoryConfigProperties;
import pers.hosea.exception.SimpleMinioException;
import pers.hosea.operate.FileMapper;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Component
@Import(MinioClientFactory.class)
@EnableConfigurationProperties(MinioFactoryConfigProperties.class)
public class FileMapperImpl implements FileMapper {
    private final static Pattern PATTERN = Pattern.compile("(\\S+?://\\S+?)/(\\S+?)/(\\S+\\.\\S+)");
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
    public String uploadFile(String fileFullPath, String contentType, InputStream inputStream) {
        try {
            PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                    .object(fileFullPath)
                    .bucket(minioFactoryConfigProperties.getBucket())
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(contentType)
                    .build();
            minioClient.putObject(putObjectArgs);
            log.info("Successfully uploaded file {}", fileFullPath);
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return buildFilePath(fileFullPath);
    }

    @Override
    public String uploadFile(String fileFullPath, ContentType contentType, InputStream inputStream) {
        return this.uploadFile(fileFullPath, contentType.toString(), inputStream);
    }

    @Override
    public String uploadFile(String filePath, String fileName, String contentType, InputStream inputStream) {
        String fileFullPath = filePath + "/" + fileName;
        return this.uploadFile(fileFullPath, contentType, inputStream);
    }

    @Override
    public String uploadFile(String filePath, String fileName, ContentType contentType, InputStream inputStream) {
        return this.uploadFile(filePath, fileName, contentType.toString(), inputStream);
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
            log.info("Successfully downloaded file {}", fileFullPath);
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
    public boolean deleteFileByFillFullPath(String fillFullPath) {
        boolean flag = false;
        try {
            RemoveObjectArgs removeObjectArgs = RemoveObjectArgs
                    .builder()
                    .bucket(minioFactoryConfigProperties.getBucket())
                    .object(fillFullPath)
                    .build();
            minioClient.removeObject(removeObjectArgs);
            log.info("Successfully deleted file {} ", fillFullPath);
            flag = true;
        } catch (Exception exception) {
            log.error(exception.getMessage());
        }
        return flag;
    }

    @Override
    public boolean deleteFileByURL(String URL) {
        Matcher matcher = PATTERN.matcher(URL);
        if (!matcher.find()) {
            throw new SimpleMinioException("bad parameter");
        }
        String ip = matcher.group(1);
        String bucket = matcher.group(2);
        String fillFullPath = matcher.group(3);
        if (!minioFactoryConfigProperties.getUrl().equals(ip)) {
            throw new SimpleMinioException("Wrong host address: " + minioFactoryConfigProperties.getUrl() + "->" + ip);
        }
        if (!minioFactoryConfigProperties.getBucket().equals(bucket)) {
            try {
                log.warn("Parameter bucket wrong: {} -> {}", bucket, minioFactoryConfigProperties.getBucket());
                RemoveObjectArgs removeObjectArgs = RemoveObjectArgs
                        .builder()
                        .bucket(bucket)
                        .object(fillFullPath)
                        .build();
                minioClient.removeObject(removeObjectArgs);
                log.info("Successfully deleted file {} ", fillFullPath);
                return true;
            } catch (Exception exception) {
                throw new SimpleMinioException(exception.getMessage());
            }
        }

        return deleteFileByFillFullPath(fillFullPath);
    }

    private String buildFilePath(String fullFillPath) {
        return minioFactoryConfigProperties.getUrl() +
                '/' +
                minioFactoryConfigProperties.getBucket() +
                '/' +
                fullFillPath;
    }
}

# SimpleMinioClient

### 介绍
为适配SpringBoot开发的MiniO客户端

这个包只是写项目时为简化操作制作的包 方便以后复用 如果你愿意使用这是我的荣幸
#### 使用说明
1. 配置yml文件
  ```
    minio:
      url: MiniOIP
      access-key: MiniO用户名
      secret-key: MiniO密码
      bucket: 桶名
  ```
2. 使用
```
    文件上传
    String uploadFile(String fileFullPath, String contentType, InputStream inputStream);

    String uploadFile(String fileFullPath, ContentType contentType, InputStream inputStream);

    String uploadFile(String filePath, String fileName, ContentType contentType, InputStream inputStream);

    String uploadFile(String filePath, String fileName, String contentType, InputStream inputStream);

    文件下载

    InputStream downloadFile(String fileFullPath);

    byte[] downloadFileToByteArray(String fillFullPath);

    删除文件
    boolean deleteFileByFillFullPath(String fileFullPath);
    
    boolean deleteFileByURL(String URL);
```

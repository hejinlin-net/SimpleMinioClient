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
    String uploadFile(String fileFullPath, String contentType, InputStream inputStream);
    String uploadFile(String fileFullPath, ContentType contentType, InputStream inputStream);
    String uploadFile(String filePath, String fileName, ContentType contentType, InputStream inputStream);
    String uploadFile(String filePath, String fileName, String contentType, InputStream inputStream);
```

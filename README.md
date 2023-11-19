# SimpleMinioClient

### 介绍
基于SpringBoot开发的简单的MiniO客户端API

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
   String uploadFile(String fileFullPath, InputStream inputStream); 全路径上传
   String uploadFile(String filePath, String fileName, InputStream inputStream); 路径加文件名上传
   InputStream downloadFile(String fileFullPath); 下载
   byte[] downloadFileToByteArray(String fillFullPath); 下载
   boolean deleteFile(String fileFullPath); 删除
```

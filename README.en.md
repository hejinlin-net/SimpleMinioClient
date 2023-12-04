# SimpleMinioClient

### introduce
MiniO client developed for adapting to SpringBoot

This package is only created for the purpose of simplifying operations during project writing, making it easy to reuse in the future. If you are willing to use it, it is my honor
#### Instructions for use
1. Configure yml files
  ```
    minio:
      url: MiniOIP
      access-key: MiniO用户名
      secret-key: MiniO密码
      bucket: 桶名
  ```
2. Ues
```
    upload
    String uploadFile(String fileFullPath, String contentType, InputStream inputStream);

    String uploadFile(String fileFullPath, ContentType contentType, InputStream inputStream);

    String uploadFile(String filePath, String fileName, ContentType contentType, InputStream inputStream);

    String uploadFile(String filePath, String fileName, String contentType, InputStream inputStream);

    download
    InputStream downloadFile(String fileFullPath);

    byte[] downloadFileToByteArray(String fillFullPath);

    delete
    boolean deleteFileByFillFullPath(String fileFullPath);
    
    boolean deleteFileByURL(String URL);
```

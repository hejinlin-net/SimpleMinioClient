package pers.hosea.operate;

import pers.hosea.enums.ContentType;

import java.io.InputStream;

public interface FileMapper {
    /**
     * 文件上传
     * @param fileFullPath 文件全路径
     * @param contentType 文件类型
     * @param inputStream 输入流
     * @return 文件MinioURL地址
     */
    String uploadFile(String fileFullPath, String contentType, InputStream inputStream);

    /**
     * 文件上传
     * @param fileFullPath 文件全路径
     * @param contentType 文件类型
     * @param inputStream 输入流
     * @return 文件MinioURL地址
     */
    String uploadFile(String fileFullPath, ContentType contentType, InputStream inputStream);

    /**
     * 文件上传
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param contentType 文件类型
     * @param inputStream 输入流
     * @return 文件MinioURL地址
     */
    String uploadFile(String filePath, String fileName, ContentType contentType, InputStream inputStream);

    /**
     * 文件上传
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param contentType 文件类型
     * @param inputStream 输入流
     * @return 文件MinioURL地址
     */
    String uploadFile(String filePath, String fileName, String contentType, InputStream inputStream);

    /**
     * 文件下载
     * @param fileFullPath 文件全路径
     */
    InputStream downloadFile(String fileFullPath);

    /**
     * 文件下载
     * @param fillFullPath 文件全路径
     * @return byte[]
     */
    byte[] downloadFileToByteArray(String fillFullPath);

    /**
     * 全路径删除文件
     * @param fileFullPath 文件全路径
     */
    boolean deleteFileByFillFullPath(String fileFullPath);

    /**
     * URL路径删除文件
     */
    boolean deleteFileByURL(String URL);
}

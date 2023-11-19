package pers.hosea.operate;

import java.io.InputStream;

public interface FileMapper {
    /**
     * 上传文件
     * @param fileFullPath 文件全路径
     * @param inputStream 传输流
     * @return 文件全路径
     */
    String uploadFile(String fileFullPath, InputStream inputStream);
    /**
     * 上传文件
     * @param filePath 文件路径
     * @param fileName 文件名
     * @param inputStream 传输流
     * @return 文件全路径
     */
    String uploadFile(String filePath, String fileName, InputStream inputStream);
    InputStream downloadFile(String fileFullPath);
    byte[] downloadFileToByteArray(String fillFullPath);
    boolean deleteFile(String filePath);
}

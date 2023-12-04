package pers.hosea.config;

import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableConfigurationProperties({MinioFactoryConfigProperties.class})
public class MinioClientFactory {
    private MinioFactoryConfigProperties minioFactoryConfigProperties;

    @Autowired
    public void setMinioFactoryConfigProperties(MinioFactoryConfigProperties minioFactoryConfigProperties) {
        this.minioFactoryConfigProperties = minioFactoryConfigProperties;
    }

    @Bean
    public MinioClient buildMinioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(minioFactoryConfigProperties.getUrl())
                .credentials(minioFactoryConfigProperties.getAccessKey(), minioFactoryConfigProperties.getSecretKey())
                .build();
        log.info("Minio client configuration successful");
        return minioClient;
    }
}

package pers.hosea.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioFactoryConfigProperties implements Serializable {
    private String url;
    private String accessKey;
    private String secretKey;
    private String bucket;
}

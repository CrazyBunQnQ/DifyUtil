package org.crazybun.difyutil.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Dify API配置属性
 */
@Data
@Component
@ConfigurationProperties(prefix = "dify")
public class DifyProperties {
    
    /**
     * Dify API基础URL
     */
    private String apiBaseUrl;
    
    /**
     * Dify API密钥
     */
    private String apiKey;
}
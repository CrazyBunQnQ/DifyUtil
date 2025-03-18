package com.crazybunqnq.mvn.difyutil.config;

import com.crazybunqnq.mvn.difyutil.client.DifyClient;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Dify 自动配置类
 */
@Configuration
@EnableConfigurationProperties(DifyProperties.class)
public class DifyAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public DifyClient difyClient(DifyProperties properties) {
        return new DifyClient(properties);
    }
}
package cn.com.ogtwelve.config;

import cn.com.ogtwelve.entity.OpenAI;
import cn.com.ogtwelve.service.OpenAIService;
import cn.com.ogtwelve.utils.OpenAIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.time.Duration;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:54
 * @Description: chatgpt自动配置
 */

@Configuration
@EnableConfigurationProperties({OpenAI.class})
public class ChatGPTAutoConfigure {
    @Resource
    private OpenAI properties;

    public ChatGPTAutoConfigure() {
    }

    @Bean
    public OpenAIService openAiService() {
        return new OpenAIService(this.properties, Duration.ZERO);
    }

    @Bean
    public OpenAIUtils openAiUtils(OpenAIService openAiService) {
        return new OpenAIUtils(openAiService);
    }
}
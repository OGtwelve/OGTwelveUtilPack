package cn.com.ogtwelve.annotation;

import cn.com.ogtwelve.config.ChatGPTAutoConfigure;
import cn.com.ogtwelve.utils.GlobalTimeConfig;
import cn.com.ogtwelve.utils.GlobalTimeConverter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 11:38
 * @Description: 启用gpt的配置
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({ChatGPTAutoConfigure.class})
@Inherited

public @interface EnableChatGPT {
}

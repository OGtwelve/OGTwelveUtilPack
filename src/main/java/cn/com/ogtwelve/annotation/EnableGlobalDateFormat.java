package cn.com.ogtwelve.annotation;

import cn.com.ogtwelve.utils.GlobalTimeConfig;
import cn.com.ogtwelve.utils.GlobalTimeConverter;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @Author OGtwelve
 * @Date 2023/7/11 10:11
 * @Description: 全局时间配置
 */

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import({GlobalTimeConfig.class, GlobalTimeConverter.class})
@Inherited
public @interface EnableGlobalDateFormat {

}

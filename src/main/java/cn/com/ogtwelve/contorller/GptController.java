package cn.com.ogtwelve.contorller;

import cn.com.ogtwelve.utils.OpenAIUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 15:12
 * @Description: 测试专用
 */
@RequestMapping("/gpt")
@RestController
public class GptController {

    @RequestMapping("/test")
    public List<String> test(String content){
        return OpenAIUtils.createChatCompletion(content);
    }

}

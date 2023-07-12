package cn.com.ogtwelve.enums;

import cn.com.ogtwelve.exceptions.ChatGPTException;

import java.util.Objects;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:00
 * @Description: 模型枚举
 */
public enum ModelEnum {
    GPT_35_TURBO_INSTRUCT("gpt-3.5-turbo-instruct", 4096),
    GPT_35_TURBO("gpt-3.5-turbo", 4096),
    GPT_35_TURBO_0301("gpt-3.5-turbo-0301", 4096),
    TEXT_DAVINCI_003("text-davinci-003", 4000),
    TEXT_CURIE_001("text-curie-001", 2048),
    TEXT_BABBAGE_001("text-babbage-001", 2048),
    TEXT_ADA_001("text-ada-001", 2048);

    private final String modelName;
    private final Integer maxTokens;

    private ModelEnum(String modelName, Integer maxTokens) {
        this.modelName = modelName;
        this.maxTokens = maxTokens;
    }

    public String getModelName() {
        return this.modelName;
    }

    public Integer getMaxTokens() {
        return this.maxTokens;
    }

    public static Integer getMaxTokens(String modelName) {
        ModelEnum[] var1 = values();
        for (ModelEnum modelEnum : var1) {
            if (Objects.equals(modelEnum.getModelName(), modelName)) {
                return modelEnum.getMaxTokens();
            }
        }
        throw new ChatGPTException(ChatGPTErrorEnum.MODEL_SELECTION_ERROR);
    }
}

package cn.com.ogtwelve.enums;

/**
 * @Author OGtwelve
 * @Date 2023/7/12 12:02
 * @Description: 默认角色枚举
 */
public enum RoleEnum {
    SYSTEM("system"),
    ASSISTANT("assistant"),
    USER("user");

    private final String roleName;

    private RoleEnum(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleName() {
        return this.roleName;
    }
}
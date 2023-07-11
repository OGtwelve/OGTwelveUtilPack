package cn.com.ogtwelve.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneRegex {
    /*中国移动号码格式验证 手机段：134(0-8),135,136,137,138,139,147,148,150,151,152,157,158,159,172,178,182,183,184,187,188,195,197,198,1440,1703,1705,1706*/
    private static final String CHINA_MOBILE_PATTERN = "(?:^(?:\\+86)?1(?:34[0-8]|3[5-9]|4[78]|5[0-27-9]|7[28]|8[2-478]|9[578])\\d{8}\\$)|(?:^(?:\\+86)?1440\\d{7}\\$)|(?:^(?:\\+86)?170[356]\\d{7}\\$)";

    /*中国联通号码格式验证 手机段：130,131,132,140,145,146,155,156,166,185,186,171,175,176,196,1704,1707,1708,1709*/
    private static final String CHINA_UNICOM_PATTERN = "(?:^(?:\\+86)?1(?:3[0-2]|4[056]|5[56]|66|7[156]|8[56]|96)\\d{8}\\$)|(?:^(?:\\+86)?170[47-9]\\d{7}\\$)";

    /*中国电信号码格式验证 手机段：133,149,153,177,173,180,181,189,190,191,193,199,1349,1410,1700,1701,1702*/
    private static final String CHINA_TELECOM_PATTERN = "(?:^(?:\\+86)?1(?:33|49|53|7[37]|8[019]|9[0139])\\d{8}\\$)|(?:^(?:\\+86)?1349\\d{7}\\$)|(?:^(?:\\+86)?1410\\d{7}\\$)|(?:^(?:\\+86)?170[0-2]\\d{7}\\$)";

    /*截止2022年2月,中国大陆四家运营商以及虚拟运营商手机号码正则验证*/
    private static final String CHINA_PATTERN = "(?:^(?:\\+86)?1(?:3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}\\$)";

    static boolean isMobile(String mobiles) {
        Pattern pattern = Pattern.compile(CHINA_PATTERN);
        Matcher matcher = pattern.matcher(mobiles);
        return matcher.matches();
    }
}

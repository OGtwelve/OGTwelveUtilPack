package cn.com.ogtwelve.utils;

import java.util.regex.Pattern;

public class IpRegex {

    private static final String IP_REGEX = "^([1-9]|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])(\\.(\\d|[1-9]\\d|1\\d{2}|2[0-4]\\d|25[0-5])){3}\\$";

    static boolean isValidIPAddress(String ipAddress) {
        if ((ipAddress != null) && (!ipAddress.isEmpty())) {
            return Pattern.matches(IP_REGEX, ipAddress);
        }
        return false;
    }
}

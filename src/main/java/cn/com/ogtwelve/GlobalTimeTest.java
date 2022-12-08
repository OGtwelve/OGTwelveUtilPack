package cn.com.ogtwelve;

import org.joda.time.DateTime;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 使用该测试类测试全局时间配置是否生效
 */
public class GlobalTimeTest {
    public static void main(String[] args) {
        Map<String,Object> result = test();
        System.out.println(result);
    }
    public static Map<String,Object> test(){
        Date date = new Date();
        LocalDate localDate = LocalDate.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        org.joda.time.LocalDate jodaLocalDate = new org.joda.time.LocalDate();
        org.joda.time.LocalDateTime jodaLocalDateTime = new org.joda.time.LocalDateTime();
        Instant instant = Instant.now();
        org.joda.time.Instant jodaInstant = new org.joda.time.Instant();
        Calendar calendar = Calendar.getInstance();
        DateTime dateTime = new DateTime();
        Map<String,Object> map = new LinkedHashMap<String,Object>();
        map.put("java.util.Date:", date);
        map.put("java.time.LocalDate:", localDate);
        map.put("java.time.LocalDateTime:", localDateTime);
        map.put("org.joda.time.LocalDate:", jodaLocalDate);
        map.put("org.joda.time.LocalDateTime:", jodaLocalDateTime);
        map.put("java.time.Instant:", instant);
        map.put("org.joda.time.Instant:", jodaInstant);
        map.put("java.util.Calendar:", calendar);
        map.put("org.joda.time.DateTime:", dateTime);
        return map;
    }
}

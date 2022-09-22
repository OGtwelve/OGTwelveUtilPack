package cn.com.ogtwelve.utils;


import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.TemporalUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * TODO 时间处理工具类
 *
 * @author ws
 * @mail 1720696548@qq.com
 * @date 2020/4/24 0024 9:54
 * @return
 */
@Component
public class LocalDateTimeUtil {

    private static LocalDateTimeUtil instance;

    public static LocalDateTimeUtil getInstance() {
        if (null == instance) {
            synchronized (LocalDateTimeUtil.class) {
                if (null == instance) {
                    instance = new LocalDateTimeUtil();
                }
            }
        }
        return instance;
    }

    //获取当前时间的LocalDateTime对象
    //LocalDateTime.now();

    //根据年月日构建LocalDateTime
    //LocalDateTime.of();

    //比较日期先后
    //LocalDateTime.now().isBefore(),
    //LocalDateTime.now().isAfter(),

    // TODO  获取当前时间
    public static LocalDateTime now() {
        return LocalDateTime.now();
    }

    // TODO  获取指定时间是周几
    public static int week(LocalDateTime time) {
        return time.getDayOfWeek().getValue();
    }

    // TODO  获取加或减N月的第一天
    public static LocalDateTime monthFirst(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.firstDayOfMonth());
        return getDayStart(newTime);
    }

    // TODO  获取加或减N月的最后天
    public static LocalDateTime monthLast(int num) {
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.MONTHS);
        newTime = newTime.with(TemporalAdjusters.lastDayOfMonth());
        return getDayEnd(newTime);
    }


    // TODO 获取加或减N周的第一天
    public static LocalDateTime weekFirst(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = subtract(LocalDateTime.now(), week - 1, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7, ChronoUnit.DAYS);
        //formatTime(, "yyyy-MM-dd HH:mm:ss:SSS");
        return getDayStart(newTime);
    }

    // TODO  获取加或减N周的最后一天
    public static LocalDateTime weekLast(int num) {
        int week = week(LocalDateTime.now());
        LocalDateTime newTime = plus(LocalDateTime.now(), 7 - week, ChronoUnit.DAYS);
        newTime = plus(newTime, num * 7, ChronoUnit.DAYS);
        return getDayEnd(newTime);
    }

    // TODO  获取加或减N周的最后一天
    public static LocalDateTime plusDays(int num) {
//        int week = week(LocalDateTime.now());
        LocalDateTime newTime = plus(LocalDateTime.now(), num, ChronoUnit.DAYS);
        return getDayEnd(newTime);
    }

    // TODO  获取加或减N天的日期
    public  LocalDate plusDays(LocalDate localDate,int num) {
        LocalDate newTime = plus(localDate, num, ChronoUnit.DAYS);
        return newTime;
    }


    // TODO  判断时间 ==>  t1 < t2 = true （2019-10-13 11:11:00 < 2020-11-13 13:13:00 = true）
    public static boolean isBefore(LocalDateTime t1, LocalDateTime t2) {
        return t1.isBefore(t2);
    }

    // TODO  判断时间 ==>  t1 > t2 = true（2019-10-13 11:11:00 > 2020-11-13 13:13:00 = false）
    public static boolean isAfter(LocalDateTime t1, LocalDateTime t2) {
        return t1.isAfter(t2);
    }

    // TODO  自构建 LocalDateTime ==> 年，月，日，时，分
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute);
    }

    // TODO  自构建 LocalDateTime ==> 年，月，日，时，分，秒，毫秒（精确到9位数）
    public static LocalDateTime of(int year, int month, int dayOfMonth, int hour, int minute, int second, int nanoOfSecond) {
        return LocalDateTime.of(year, month, dayOfMonth, hour, minute, second, nanoOfSecond);
    }

    // TODO  Date 转 LocalDateTime
    public static LocalDateTime convertDateToLDT(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    // TODO  LocalDateTime 转 Date
    public static Date convertLDTToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    // TODO  获取指定日期的毫秒
    public static Long getMilliByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    // TODO  获取指定日期的秒
    public static Long getSecondsByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    // TODO 获取指定时间的指定格式 ==> yyyy-MM-dd HH:mm:ss:SSS  (HH是24小时制，而hh是12小时制, ss是秒，SSS是毫秒)
    public static String formatTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    // TODO 获取指定时间的指定格式 ==> yyyy-MM-dd HH:mm:ss:SSS  (HH是24小时制，而hh是12小时制, ss是秒，SSS是毫秒)
    public static String formatDate(LocalDate localDate, String pattern) {
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串转LocalDateTime
     *
     * @param time
     * @param pattern
     * @return
     */
    public static LocalDateTime formatTime(String time, String pattern) {
        return LocalDateTime.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 字符串转LocalDate
     *
     * @param time
     * @param pattern
     * @returnD
     */
    public LocalDate formatDate(String time, String pattern) {
        return LocalDate.parse(time, DateTimeFormatter.ofPattern(pattern));
    }

    // TODO 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    // TODO 日期加上一个数,根据field不同加不同值,field为ChronoUnit.*
    public static LocalDate plus(LocalDate time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    // TODO 日期减去一个数,根据field不同减不同值,field参数为ChronoUnit.*
    public static LocalDateTime subtract(LocalDateTime time, long number, TemporalUnit field) {
        return time.minus(number, field);
    }


    /**
     * TODO 获取两个日期的差  field参数为ChronoUnit.*
     *
     * @param startTime
     * @param endTime
     * @param field     单位(年月日时分秒)
     **/
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS) {
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }


    // TODO  获取指定某一天的开始时间 00:00:00
    public static LocalDateTime getDayStart(LocalDateTime time) {
        return time.withHour(0)
                .withMinute(0)
                .withSecond(0)
                .withNano(0);
    }


    // TODO 获取指定某一天的结束时间  23:59:59.999
    public static LocalDateTime getDayEnd(LocalDateTime time) {
        return time
                //.withDayOfMonth(1)    // 月
                //.withDayOfYear(2)     // 天
                .withHour(23)           // 时
                .withMinute(59)         // 分
                .withSecond(59)         // 秒
                .withNano(999999999);   // 毫秒（这里精确到9位数）
    }

    //获取本周周一
    public static LocalDateTime getWeekOfFirst(LocalDateTime time) {
        return time.with(TemporalAdjusters.previous(DayOfWeek.SUNDAY)).
                plusDays(1).withHour(0).withMinute(0).withSecond(0);
    }

    //获取本周周日
    public static LocalDateTime getWeekOfLast(LocalDateTime time) {
        return time.with(TemporalAdjusters.next(DayOfWeek.MONDAY)).
                minusDays(1).withHour(23).withMinute(59).withSecond(59);
    }

    //获取本月第一天
    public static LocalDateTime getMonthOfFirst(LocalDateTime time) {
        LocalDateTime firstday = time.with(TemporalAdjusters.firstDayOfMonth());
        return LocalDateTime.of(firstday.toLocalDate(), LocalTime.MIN);
    }

    //获取本月最后一天
    public static LocalDateTime getMonthOfLast(LocalDateTime time) {
        LocalDateTime lastDay = time.with(TemporalAdjusters.lastDayOfMonth());
        return LocalDateTime.of(lastDay.toLocalDate(), LocalTime.MAX);
    }

    // TODO 获取当前时间月到 +N月的所有时间，一天一条数据 List<DateDays>
    public static List<DateDays> getDateDaysUpList(Integer num) {
        LocalDateTime startTime = monthFirst(0);        // 本月第一天  00:00:00
        LocalDateTime entTime = monthLast(num);              // 两月后的最后一天 23:59:59.999
        // 3个月的数据
        List<DateDays> everyDays = new ArrayList<>();
        // 第一天数据
        everyDays.add(new DateDays(startTime, week(startTime)));
        while (true) {
            // 获取一天后时间
            LocalDateTime nextDay = plus(everyDays.get(everyDays.size() - 1).dayTime, 1, ChronoUnit.DAYS);
            //大于两月后最后一天-跳出循环
            if (isAfter(nextDay, entTime)) {
                break;
            }
            everyDays.add(new DateDays(nextDay, week(nextDay)));
        }
        return everyDays;
    }

    /**
     * TODO  获取日期端的数据保存对象
     *
     * @author ws
     * @mail 1720696548@qq.com
     * @date 2020/5/7 0007 9:41
     */
    @Data
    @AllArgsConstructor
    public static class DateDays {
        //当天时间- 年月日/00:00:00
        private LocalDateTime dayTime;
        //当天是周几
        private int week;

    }

    /**
     * 获得指定月天数
     *
     * @param num
     * @return
     */
    public static int getDaysOfMonth(int num) {
        //获得指定月最后一天日期
        LocalDateTime preDateTime = monthLast(num);
        //月多少天
        return preDateTime.toLocalDate().getDayOfMonth();
    }

    /**
     * 获得旬
     *
     * @param localDate
     * @return
     */
    public int getTenDay(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        if (dayOfMonth < 11) {
            return 1;

        } else if (dayOfMonth < 21) {
            return 2;
        } else {
            return 3;
        }
    }

    /**
     * 获得10DAY对应的日期
     * @param localDate
     * @return
     */
    public LocalDate getTenDate(LocalDate localDate) {
        int tenDay = getTenDay(localDate);
        if (tenDay == 1) {
            return localDate.withDayOfMonth(10);

        } else if (tenDay == 2) {
            return localDate.withDayOfMonth(20);
        } else {
            return localDate.withDayOfMonth(localDate.lengthOfMonth());
        }

    }


    /**
     * 获得上一寻日期
     * @param localDate
     * @return
     */
    public LocalDate getLastTenDate(LocalDate localDate){
        int dayOfMonth = localDate.getDayOfMonth();
        if(dayOfMonth< 11){
            //获得上一个月的最后一旬(及最后一天日期)
            LocalDate newTime = localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            return getTenDate(newTime);
        }else if(dayOfMonth< 21){
            return localDate.withDayOfMonth(10);
        }else{
            return localDate.withDayOfMonth(20);
        }

    }

    /**
     * 获得上一侯日期
     * @param localDate
     * @return
     */
    public LocalDate getLastFDate(LocalDate localDate){
        int dayOfMonth = localDate.getDayOfMonth();
        if(dayOfMonth< 6){
            //获得上一个月的最后一旬(及最后一天日期)
            LocalDate newTime = localDate.minusMonths(1).with(TemporalAdjusters.lastDayOfMonth());
            return getTenDate(newTime);
        }else if(dayOfMonth< 11){
            return localDate.withDayOfMonth(5);
        }else if(dayOfMonth< 16){
            return localDate.withDayOfMonth(10);
        }else if(dayOfMonth< 21){
            return localDate.withDayOfMonth(15);
        }else if(dayOfMonth< 26){
            return localDate.withDayOfMonth(20);
        }else{
            return localDate.withDayOfMonth(25);
        }

    }


    /**
     * 获得侯，5，10，15，20，25，30
     *
     * @param localDate
     * @return
     */
    public int getFDay(LocalDate localDate) {
        int dayOfMonth = localDate.getDayOfMonth();
        if (dayOfMonth < 6) {
            return 1;

        } else if (dayOfMonth < 11) {
            return 2;
        } else if (dayOfMonth < 16) {
            return 3;
        } else if (dayOfMonth < 21) {
            return 4;
        } else if (dayOfMonth < 26) {
            return 5;
        } else {
            return 6;
        }
    }

    /**
     * 获得5DAY对应的上周期日期
     * @param localDate
     * @return
     */
    public LocalDate getFDate(LocalDate localDate) {
        int fDay = getFDay(localDate);

        if (fDay == 1) {
            return localDate.withDayOfMonth(5);

        } else if (fDay == 2) {
            return localDate.withDayOfMonth(10);
        } else if (fDay == 3) {
            return localDate.withDayOfMonth(15);
        } else if (fDay == 4) {
            return localDate.withDayOfMonth(20);
        } else if (fDay == 5) {
            return localDate.withDayOfMonth(25);
//        } else if (fDay == 6){
//            return localDate.withDayOfMonth(30);
//        }
        }else {
            return localDate.withDayOfMonth(localDate.lengthOfMonth());
        }

    }

    /**
     * 获取格式化后的路径字符串
     *
     * @param path
     * @return
     */
    public String getDataFormatePath(String path, LocalDate dt) {
        String result = "";
        try {
            String[] array = path.split("/");
            for (int i = 0; i < array.length; i++) {
                String item = array[i];
                if (item.endsWith("|") && item.startsWith("|")) {
                    String formateStr = item.substring(1, item.length() - 1);
                    array[i] = LocalDateTimeUtil.getInstance().formatDate(dt, formateStr);
                }
            }
            for (String item : array) {
                result += item + "/";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return result.substring(0, result.length() - 1);
    }


    /**
     * 获取格式化后的字符串
     *
     * @param sourceFileDate
     * @return
     */
    public String getDataFormate(String sourceFileDate, LocalDate dt) {
        String result = sourceFileDate;
        try {
//            String[] array = path.split("/");
//            for (int i = 0; i < array.length; i++) {
//                String item = array[i];
                if (sourceFileDate.contains("|")) {
                    String formateStr = sourceFileDate.substring(sourceFileDate.indexOf("|")+1, sourceFileDate.lastIndexOf("|") );
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(formateStr);
                    result=sourceFileDate.replace(formateStr,dt.format(dateTimeFormatter)).replace("|","");
                }
//                else{
//                    result= LocalDate.parse(sourceFileDate, DateTimeFormatter.ofPattern(formateStr));
//                }
//            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    //两位补零
    public String appendZero (int obj) {
        if (obj < 10) {
            return "0" + obj;
        } else {
            return String.valueOf(obj);
        }

    }
        public static void main(String[] args) {
        LocalDateTimeUtil localDateTimeUtil = new LocalDateTimeUtil();
//        List<DateDays> dateDaysUpList = util.getDateDaysUpList(0);
//        System.out.println(dateDaysUpList.size());
//        LocalDateTime localDateTime =util.monthFirst(-1);
//        String date = util.formatTime(localDateTime, "yyyyMM");
//        System.out.println(date);
            LocalDate localDate=LocalDate.now();
            String path="{yyyyMMdd}";
            localDateTimeUtil.getDataFormatePath(path,localDate);

        int days = localDateTimeUtil.getDaysOfMonth(1);
        System.out.println(days);

    }

}




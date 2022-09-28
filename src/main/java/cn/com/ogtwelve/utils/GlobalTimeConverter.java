package cn.com.ogtwelve.utils;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


//Converter<S,T>  S: 要转换的数据类型  T:转成什么数据类型
@Component
public class GlobalTimeConverter implements Converter<String, Date> {

    //静态初始化定义日期字符串参数列表（需要转换的）
    private static final List<String> paramList = new ArrayList<>();

    //声明都有哪些日期格式
    private static final String param1 = "yyyy-MM";
    private static final String param2 = "yyyy-MM-dd";
    private static final String param3 = "yyyy-MM-dd HH:mm";
    private static final String param4 = "yyyy-MM-dd HH:mm:ss";

    //把日期格式信息添加到静态块
    static {
        paramList.add(param1);
        paramList.add(param2);
        paramList.add(param3);
        paramList.add(param4);
    }

    //自定义函数，将字符串转Date  参1：传入的日期字符串  参2：格式参数
    public Date parseDate(String source, String format) {
        Date date = null;
        try {
            //日期格式转换器
            DateFormat dateFormat = new SimpleDateFormat(format);
            date = dateFormat.parse(source);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }


    //convert转换方法 ，s是将会传递过来的日期的字符串
    @Override
    public Date convert(String source) {
        if(StringUtils.isEmpty(source)){
            return null;
        }
        source = source.trim();

        //正则表达式判断是哪一种格式参数
        if (source.matches("^\\d{4}-\\d{1,2}$")) {
            return parseDate(source, paramList.get(0));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2}$")) {
            return parseDate(source, paramList.get(1));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, paramList.get(2));
        } else if (source.matches("^\\d{4}-\\d{1,2}-\\d{1,2} {1}\\d{1,2}:\\d{1,2}:\\d{1,2}$")) {
            return parseDate(source, paramList.get(3));
        } else {
            throw new IllegalArgumentException("参数未定义：" + source);
        }
    }
}

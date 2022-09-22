package cn.com.ogtwelve;

import cn.com.ogtwelve.utils.ThreeTuple;
import cn.com.ogtwelve.utils.TupleUtil;
import org.springframework.boot.autoconfigure.SpringBootApplication;

public class Application {
    public static void main(String[] args) {
        System.out.println("输出成功");
        ThreeTuple<String, Integer, String> tuple = TupleUtil.tuple("1", 2, "3");
        //遍历tuple
        System.out.println(tuple.first);
        System.out.println(tuple.second);
        System.out.println(tuple.third);

    }
}

package cn.thislx.lambda.test3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试启动类
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 17:05
 **/
public class APP {

    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        List<String> tempList = new ArrayList<>();
        for (String temp : strings) {
            if (!temp.isEmpty()) {
                tempList.add(temp);
            }
        }
        System.out.println(tempList);

        // filter 过滤字符串为空的
        Stream<String> stream = strings.stream();
        Stream<String> stringStream = stream.filter(temp -> !temp.isEmpty());
        List<String> collect = stringStream.collect(Collectors.toList());


        List<String> filtered = strings.stream().filter(string -> !string.isEmpty()).collect(Collectors.toList());
        System.out.println(filtered);
    }
}

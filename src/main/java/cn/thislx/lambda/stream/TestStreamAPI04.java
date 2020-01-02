package cn.thislx.lambda.stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 14:24
 **/
public class TestStreamAPI04 {

    public static void main(String[] args) {
        toMapTest();
    }

    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 38, 7777.77),
            new Employee(104, "赵六", 38, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    //3. 终止操作

    /**
     * 规约
     * reduce(T identity, BinaryOperator) / reduce(BinaryOperator)
     * ——可以将流中元素反复结合起来，得到一个值。
     */
    public static void fn1() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Integer reduce1 = list.stream()
                .reduce((a, b) -> a + b).get();

//        相对于一个参数的方法来说，它多了一个T类型的参数；实际上就相当于需要计算的值在Stream的基础上多了一个初始化的值。
//        同理，当对n个元素的数组进行运算时，其表达的含义如下：
        Integer reduce = list.stream()
                .reduce(1, (a, b) -> a + b);

        System.out.println(reduce);
        System.out.println(reduce1);
    }

    /**
     * map-reduce模式
     */
    public static void fn2() {
        Optional<Double> optional = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::max);
        System.out.println(optional);
    }

    /**
     * collect——将流转换为其他形式。
     * 接收一个 Collector接口的实现，用于给Stream中元素做汇总的方法
     */
    // 用List接收 默认ArrayList
    public static void fn4() {
        List<String> nameList = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toList());
        System.out.println(nameList);
    }

    // 用Set接收 默认HastSet
    public static void fn5() {
        Set<String> nameSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toSet());
        System.out.println(nameSet);
    }

    // 用指定容器接收,如TreeSet
    public static void fn6() {
        TreeSet<String> nameTreeSet = emps.stream()
                .map(Employee::getName)
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(nameTreeSet);
    }


    // 求均值
    public static void fn8() {
        Double d = emps.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(d);
    }

    // 求和
    public static void fn9() {
        DoubleSummaryStatistics sum = emps.stream()
                .collect(Collectors.summarizingDouble(Employee::getSalary));
        System.out.println(sum);
    }


    // 单列分组
    public static void fn12() {
        Map<String, List<Employee>> map = emps.stream()
                .collect(Collectors.groupingBy(Employee::getName));
        System.out.println(map);
    }

    // 多列分组
    public static void fn13() {
        Map<String, Map<String, List<Employee>>> mapMap = emps.stream()
                .collect(Collectors.groupingBy(Employee::getName, Collectors.groupingBy((e) -> {
                    if (e.getAge() > 50)
                        return "老年";
                    else if (e.getAge() > 35)
                        return "中年";
                    else
                        return "青年人";
                })));
        System.out.println(mapMap);
    }

    // 分区
    public static void fn14() {
        Map<Boolean, List<Employee>> listMap = emps.stream()
                .collect(Collectors.partitioningBy((e) -> e.getSalary() >= 4000));
        System.out.println(listMap);
    }

    // 拼接
    public static void joiningTest() {
        List<String> list = Arrays.asList("123", "456", "789", "1101", "212121121", "asdaa", "3e3e3e", "2321eew");
        // 无参方法
        String s = list.stream().collect(Collectors.joining());
        System.out.println(s);
        // 指定连接符
        String ss = list.stream().collect(Collectors.joining("-"));
        System.out.println(ss);
        // 指定连接符和前后缀
        String sss = list.stream().collect(Collectors.joining("-", "S", "E"));
        System.out.println(sss);
    }

    // 转Map
    public static void toMapTest() {
        List<String> list = Arrays.asList("123", "456", "789", "1101", "212121121", "asdaa", "3e3e3e", "2321eew");
        Map<String, String> map = list.stream().limit(3).collect(Collectors.toMap(e -> e.substring(0, 1), e -> e));
        Map<String, String> map1 = list.stream().collect(Collectors.toMap(e -> e.substring(0, 1), e -> e, (a, b) -> b));
        Map<String, String> map2 = list.stream().collect(Collectors.toMap(e -> e.substring(0, 1), e -> e, (a, b) -> b, HashMap::new));
        System.out.println(map.toString() + "\n" + map1.toString() + "\n" + map2.toString());
    }

}

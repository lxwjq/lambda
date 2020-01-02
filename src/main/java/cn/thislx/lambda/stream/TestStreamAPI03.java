package cn.thislx.lambda.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 短路状态 API
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 14:20
 **/
public class TestStreamAPI03 {

    public static void main(String[] args) {
        fn1();
    }

    /*
        allMatch——检查是否匹配所有元素
        anyMatch——检查是否至少匹配一个元素
        noneMatch——检查是否没有匹配的元素
        findFirst——返回第一个元素
        findAny——返回当前流中的任意元素
        count——返回流中元素的总个数
        max——返回流中最大值
        min——返回流中最小值
     */
    static List<Employee> emps = Arrays.asList(
            new Employee(102, "李四", 59, 6666.66),
            new Employee(101, "张三", 18, 9999.99),
            new Employee(103, "王五", 28, 3333.33),
            new Employee(104, "赵六", 8, 7777.77),
            new Employee(104, "赵六", 38, 7777.77),
            new Employee(104, "赵六", 38, 7777.77),
            new Employee(105, "田七", 38, 5555.55)
    );

    // 终止操作集锦
    public static void fn1() {
        // 是否全部满足
        Stream<Employee> stream = emps.stream();
        boolean b = stream
                .allMatch((e) -> e.getAge() > 7);
        System.out.println("allMatch()=================" + b);
//        stream.forEach(System.out::println);

        // 是否有一个满足
        boolean b1 = emps.stream()
                .anyMatch((e) -> e.getAge() > 70);
        System.out.println("anyMatch()=================" + b1);

        boolean b2 = emps.stream()
                .noneMatch((e) -> e.getAge() == 80);
        System.out.println("noMatch()================" + b2);

        Optional<Employee> optional = emps.stream()
                .sorted((e1, e2) -> e1.getAge().compareTo(e2.getAge()))
                // Comparator.comparing(Employee::getAge)
                .findFirst();
        System.out.println("findFirst()================" + optional.get());

        Optional<Employee> any = emps.stream()
                .filter((e) -> e.getAge() > 30)
                .findAny();
        System.out.println("findAny()=============" + any.get());

        // 终端状态
        long count = emps.stream()
                .count();
        System.out.println("count()=========" + count);

        // 获取前*位
        List<Employee> collect = emps.stream()
                .limit(3).collect(Collectors.toList());
        System.out.println("limit()=========" + collect);

        // 最大值
        Optional<Employee> max = emps.stream()
                .max((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        // Comparator.comparingDouble(Employee::getSalary)
        System.out.println("max()===================" + max);

        // 最小值
        Optional<Employee> min = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        // Comparator.comparingDouble(Employee::getSalary)
        System.out.println("min()=====================" + min);

    }
}
package cn.thislx.lambda.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 中间操作API
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 13:49
 **/
public class TestStreamAPI02 {

    public static void main(String[] args) {
        fn8();
    }

    /**
     * 筛选与切片
     * filter--接收Lambda,从流中排除某些元素
     * limit(n)--截断流,使其元素不超过给定数量n
     * skip(n)--跳过元素,返回一个扔掉了前n个元素的流,若元素不足n个,则返回null,与limit(n)互补
     * distinct--筛选,通过流所生成的hashCode(),和equals()去除重复元素
     * <p>
     * map--接收Lambda表达式,将元素转换成其他形式或提取信息,接收一个函数作为参数,该函数会应用到每个元素上,并将其映射成一个新的元素.
     * flatMap--接收一个函数作为参数,将流中的每个值都换成另一个流,然后把所有流凑成一个流
     * map与flatMap的关系类似于List中的add()与addALl()
     * <p>
     * sorted--自然排序
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

    // 筛选元素
    public static void fn1() {
        // 内部迭代,由StreamAPI完成
        Stream<Employee> employeeStream = emps.stream()
                .filter((e) -> e.getAge() > 30);
        // 一次性执行完所有内容,即"惰性求值"
        employeeStream.forEach(System.out::println);
//        emps.stream()
//                .filter((e) -> e.getAge() > 30).forEach(System.out::println);
    }

    // 只选前几个元素
    public static void fn2() {
        emps.stream()
                .filter((e) -> e.getSalary() > 6000)
                .limit(3)
                .forEach(System.out::println);
    }

    // 跳过前几个元素
    public static void fn3() {
        emps.stream()
                .filter((e) -> e.getAge() > 20)
                .skip(2)
                .forEach(System.out::println);

    }

    // 去重
    public static void fn4() {
        emps.stream()
                .distinct()
                .forEach(System.out::println);
    }

    // map
    public static void fn5() {
        emps.stream()
                .map(Employee::getName)
                // x -> x.getName()
                .forEach(System.out::println);
    }

    // 拼接
    public static void fn6() {
        List<String> list = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<Character> characterStream = list.stream()
                .flatMap(TestStreamAPI02::filterCharacter);
        characterStream.forEach(System.out::println);
    }

    /**
     * 将每个字符串按字符拆分转化为stream
     *
     * @param str
     * @return
     */
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (Character character : str.toCharArray()) {
            list.add(character);
        }
        return list.stream();
    }

    // 排序
    public static void fn7() {
        List<String> list = Arrays.asList("b", "c", "a", "m", "d");
        list.stream()
                .sorted()
                .forEach(System.out::println);
    }

    public static void fn8() {
        emps.stream()
                .sorted((e1, e2) -> {
                    if (e1.getAge().equals(e2.getAge())) {
                        return e1.getName().compareTo(e2.getName());
                    } else {
                        return -e1.getAge().compareTo(e2.getAge());
                    }
                })
                .forEach(System.out::println);
    }
}
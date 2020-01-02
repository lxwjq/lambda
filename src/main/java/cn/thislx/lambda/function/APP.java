package cn.thislx.lambda.function;

import cn.thislx.lambda.function.Person1;

import java.util.function.*;

/**
 * 常用的函数接口（java.utils.function包中）：
 * Predicate<T>——接收T对象并返回boolean
 * Consumer<T>——接收T对象，不返回值
 * Function<T, R>——接收T对象，返回R对象
 * Supplier<T>——提供T对象（例如工厂），不接收值
 * UnaryOperator<T>——接收T对象，返回T对象
 * BinaryOperator<T>——接收两个T对象，返回T对象
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/11/29 18:29
 **/
public class APP {


    // 变量访问操作
    private String s1 = "全局变量";

    public void testInnerClass() {
        String s2 = "局部变量";
        new Thread(new Runnable() {

            String s3 = "内部变量";

            @Override
            public void run() {

                // this代表当前内部类对象
                System.out.println(s1);

                // 局部变量访问不能对数据进行修改   final
                System.out.println(s2);
                System.out.println(this.s3);
            }
        }).start();
    }


    public void lambdaInnerClass() {
        String s2 = "局部变量";
        new Thread(() -> {
            String s3 = "内部变量";

            // this代表所属方法所在类型的对象
            System.out.println(this.s1);
            System.out.println(s2);
            System.out.println(s3);
        });
    }


    public static void main(String[] args) {
        unaryOperatorTest();
    }

    public static void consumerTest() {
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
        Consumer consumer = f.andThen(f2);
        consumer.accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
    }

    public static Integer functionTest(String param) {
        Function<String, Integer> function = temp -> {
            if ("admin".equals(temp)) {
                return 0;
            } else {
                return 1;
            }
        };
        Integer result = function.apply(param);
        return result;
    }

    public static String supplierTest(String param) {
        Supplier<String> supplier = () -> {
            return "这是你要的字符串";
        };
        return supplier.get();
    }

    public static void unaryOperatorTest() {
        UnaryOperator<Person1> unaryOperator = person1 -> {
            person1.setAge(10);
            person1.setName("test");
            return person1;
        };
        Person1 person1 = new Person1();
        unaryOperator.apply(person1);
        System.out.println(person1);
    }

    public static void binaryOperatorTest() {
        BinaryOperator<Double> binaryOperator = (x, y) -> {
            return x + y;
        };
        binaryOperator.apply(1.2, 2.1);
    }
}

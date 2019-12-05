package cn.thislx.lambda.test2;

import java.util.function.Predicate;

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

        Predicate<String> predicate = userName -> {
            if ("admin".equals(userName)) {
                return true;
            }
            return false;
        };

        Person1 persion1 = new Person1();
    }
}

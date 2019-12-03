package cn.thislx.lambda.test1;

import cn.thislx.lambda.test1.impl.FunctionInterfaceServerImpl;

import java.util.*;

/**
 * 测试方法
 * 1、测试Lambda表达式线程
 * 2、测试函数式接口
 * 3、测试Lambda+函数式接口
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/11/29 14:56
 **/
public class APP {

    private static List<String> staticList = Arrays.asList("my", "name", "is", "uber", "and", "uc");

    public static void oldSort() {
        Collections.sort(staticList, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if (o1.charAt(0) <= o2.charAt(0)) {
                    return 1;
                } else {
                    return -1;
                }
            }
        });
    }

    /**
     * lambda方式
     */
    public static void newSort() {
        Collections.sort(staticList, (a, b) -> a.charAt(0) <= b.charAt(0) ? 1 : -1);
    }

    // 基本语法:
    //(parameters) -> expression 或 (parameters) ->{ statements; }
    //即: 参数 -> 带返回值的表达式/无返回值的陈述
    public static void main(String[] args) {

        /********************线程篇 start***********************/
        // 原始代码实现Thread
        // Anonymous new Runnable() can be replaced with lambda
        // 【译】 匿名的创建Runnable可以被lambda替换
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("当前线程Id:" + Thread.currentThread().getId());
            }
        }).start();

        // Lambda实现Thread
        // 优点：代码简洁、可读性强、去除无关代码
        new Thread(() -> {
            System.out.println("当前Lambda线程Id：" + Thread.currentThread().getId());
        }).start();

        // 缩减版（逻辑代码只有一行时，可以移除{}）
        new Thread(() -> System.out.println("当前Lambda线程Id：" + Thread.currentThread().getId())).start();
        /********************线程篇 end***********************/

        /********************函数式接口篇 start*********************/
        FunctionInterfaceServer functionalInterface = new FunctionInterfaceServerImpl();
        System.out.println(functionalInterface.ouathUser("admin"));
        System.out.println(functionalInterface.ouathUser("lixiang"));

        String userName = functionalInterface.defaultFunction("admin");
        System.out.println("默认实现:" + userName);

        String result = FunctionInterfaceServer.staticFunction();
        System.out.println(result);

        /********************函数式接口篇 end***********************/

        /********************函数式接口+Lambda篇 start*********************/
        //可选类型声明：不声明参数类型也可以，编译器可以统一识别参数值
        //可选的参数圆括号：一个参数可以不定义圆括号，但是多个参数需要定义圆括号
        //可选的大括号：如果主体包含了一个语句，可以不使用大括号
        //可选的返回（return）关键字：如果主体只有一个表达式返回值则编译器会自动返回值，如果使用了return 关键字，则需要加上大括号。
        FunctionInterfaceServer fis = (String name) -> {
            if ("admin".equals(name)) {
                return true;
            } else {
                return false;
            }
        };

        boolean admin = fis.ouathUser("admin");
        System.out.println(admin);


        // 简化版
        FunctionInterfaceServer fis2 = name -> "admin".equals(name) ? true : false;
        boolean admin2 = fis.ouathUser("admin");
        System.out.println(admin2);


        /********************函数式接口+Lambda篇 end***********************/

    }
}

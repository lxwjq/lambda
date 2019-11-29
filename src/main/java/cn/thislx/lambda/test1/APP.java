package cn.thislx.lambda.test1;

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


    }
}

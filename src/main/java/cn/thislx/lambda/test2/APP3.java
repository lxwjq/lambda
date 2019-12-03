package cn.thislx.lambda.test2;

/**
 * 测试方法
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 19:56
 **/
public class APP3 {

    interface Param1 {
        void outInfo();
    }

    interface Param2 {
        void outInfo();
    }


    public static void test(Param1 param1) {
        System.out.println("param1");
    }

    public static void test(Param2 param2) {
        System.out.println("param2");
    }

    public static void main(String[] args) {

        // 自动推导限制了传统的功能操作
//        test(()->{
//
//        });
    }
}

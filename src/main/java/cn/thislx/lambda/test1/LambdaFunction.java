package cn.thislx.lambda.test1;

/**
 * 函数式接口（含义：有且仅有一个抽象方法，但可以有多个非抽象方法的接口）
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/11/29 11:11
 **/
@FunctionalInterface
public interface LambdaFunction {

    /**
     * JDK1.8+新特性，接口的默认实现方法
     * 优点：
     * 1、对于一些公有的方法，直接使用默认的方法，就不用再实现类中写重复代码
     * 2、可以对代码进行零入侵的加入一些新特性
     */
    default String testDefault(String name) {
        return name;
    }

    /**
     * JDK1.8+新特性 接口的静态实现类
     */
    static String staticTest() {
        return "static method";
    }

    /**
     * 认证用户
     *
     * @param name 用户名
     */
    boolean ouathUser(String name);


    /**
     * 实现父类Object类
     */
    @Override
    boolean equals(Object obj);
}

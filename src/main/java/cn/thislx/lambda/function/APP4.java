package cn.thislx.lambda.function;

/**
 * 测试方法
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 19:56
 **/
public class APP4 {

    public static void main(String[] args) {
        IMarkUp iMarkUp = message -> System.out.println(message);
        iMarkUp.markUp("Hello Lambda");
    }

//    private static void lambda$main$0(java.lang.String){
//        System.out.println("Hello Lambda");
//    }
}

interface IMarkUp {
    void markUp(String msg);
}

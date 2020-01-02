package cn.thislx.lambda.function;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 19:42
 **/
public class APP2 {

    public static void test(MyInterface<String, List> inter) {
        List list = inter.strategy("hello", new ArrayList());
        System.out.println(list);
    }

    public static void main(String[] args) {
        test(new MyInterface<String, List>() {
            @Override
            public List strategy(String s, List list) {
                list.add(s);
                return list;
            }
        });

        test((x, y) -> {
            y.add(x);
            return y;
        });



        // (x, y) -> {...}  --> test(param) --> param == MyInterface  所以当前的Lambda属于MyInterface类型
        // 这就是对于Lambda表达式的类型检查，Myinterface接口类型就是lambda表达式的目标类型（target typing）

        // 对于参数类型的检查
        // (x ,y) -> {...} --> MyInterface.strategy(T t, R r)  --> MyInterface<String, List> -->
        // T == String，R == List  -->  x == T == String y == R == List

    }

}


@FunctionalInterface
interface MyInterface<T, R> {

    List strategy(T t, R r);
}
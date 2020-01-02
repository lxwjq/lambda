package cn.thislx.lambda;

/**
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 11:31
 **/
public class TestDemo {
    public static void main(String[] args) {
        InterDemo interDemo = temp -> System.out.println(temp);
        interDemo.mark("test");
    }
}

interface InterDemo{
    void mark(String temp);
}

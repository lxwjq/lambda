##函数式接口：
- 函数式接口只能操作一个方法
- Lambda表达式，只能操作一个方法
- Java中的Lambda表达式，核心就是函数式接口的实现类

##JDK中常用的函数式接口
 * Predicate<T>——接收T对象并返回boolean
 * Consumer<T>——接收T对象，不返回值
 * Function<T, R>——接收T对象，返回R对象
 * Supplier<T>——提供T对象（例如工厂），不接收值
 * UnaryOperator<T>——接收T对象，返回T对象
 * BinaryOperator<T>——接收两个T对象，返回T对象
 
 
## Lambda表达式的基本语法
- 声明：就是和Lambda表达式绑定的接口类型
- 参数：包含在一对圆括号中，和绑定的接口中的抽象方法中的参数个数以及顺序一致
- 操作符：->
- 执行代码块：包含在一对大括号中，出现在操作符号的右侧
[接口声明] = （参数） -> {执行代码块};
```java
interface ILambda1{
    void test();
}

interface ILambda2{
    void test(String a, int b);
}

interface ILambda3{
    void test(int a, int b);
}


public class Test{
    public static void main(String[] args){
      // 执行代码只有一行可以省略{}
      ILamdba1 l1 = () -> {System.out.println("test");};
      l1.test();
        
      // 多参数可以移除参数类型，Lambda自动会去绑定接口判断类型
      ILambda2 i2 = (String a, int b) ->{
        
      };

      // 如果只有一行代码可以直接省略return关键字   ILambda3 i3 = (a ,b) -> a+b;
      ILambda3 i3 = (a ,b) ->{
          int c = a+b;
          return c;    
      };
      i3.test();
    }
}
```

##Lambda变量访问操作
详细见 cn.thislx.lambda.test2.APP

##Lambda表达式类型检查

##方法重载遇见Lambda表达式
详细见 cn.thislx.lambda.test2.APP3

##Lambda表达式底层运行原理
以APP4类为例
```linux
# 将java文件编译成class文件生成APP4.class文件
javac APP4.java

# 查询Class
javap -p APP4.class
```


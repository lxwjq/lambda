## JDK1.8新特性

### 1. 函数式接口
含义：有且仅有一个抽象方法，但可以有多个非抽象方法的接口

函数式接口，就是Java类型系统中的接口

函数式接口，是只包含一个接口方法的特殊接口

语义化检测注解：@FunctionalInterface

- 函数式接口只能操作一个方法
- Lambda表达式，只能操作一个方法
- Java中的Lambda表达式，核心就是函数式接口的实现类

**代码示例：**

```java
@FunctionalInterface
public interface FunctionInterfaceServer {

    /**
     * JDK1.8+新特性，接口的默认实现方法
     * 优点：
     * 1、对于一些公有的方法，直接使用默认的方法，就不用再实现类中写重复代码
     * 2、可以对代码进行零入侵的加入一些新特性
     */
    default String defaultFunction(String name) {
        if ("admin".equals(name)) {
            return name += ":admin";
        }
        return name += ":user";
    }



    /**
     * JDK1.8+新特性 接口的静态实现类
     */
    static String staticFunction() {
        return "This is FunctionInterface static method";
    }

    /**
     * 认证用户（抽象方法）
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
```



#### 1.1 常用的函数式接口
常用的函数接口（java.utils.function包中）：

Predicate<T>——接收T对象并返回boolean

```java
        Predicate<String> predicate = userName -> {
                    if ("admin".equals(userName)) {
                        return true;
                    }
                    return false;
                };
        boolean result = predicate.test("admin");
```

Consumer<T>——接收T对象，不返回值

```java
        Consumer f = System.out::println;
        Consumer f2 = n -> System.out.println(n + "-F2");

        //执行完F后再执行F2的Accept方法
        Consumer consumer = f.andThen(f2);
        consumer.accept("test");

        //连续执行F的Accept方法
        f.andThen(f).andThen(f).andThen(f).accept("test1");
```

Function<T, R>——接收T对象，返回R对象

```java
        Function<String, Integer> function = temp -> {
            if ("admin".equals(temp)) {
                return 0;
            } else {
                return 1;
            }
        };
        Integer result = function.apply(param);
```

Supplier<T>——提供T对象（例如工厂），不接收值

```java
        Supplier<String> supplier = () -> {
            return "这是你要的字符串";
        };
        return supplier.get();
```

UnaryOperator<T>——接收T对象，返回T对象

```java
        UnaryOperator<Person1> unaryOperator = person1 -> {
            person1.setAge(10);
            person1.setName("test");
            return person1;
        };
        Person1 person1 = new Person1();
        unaryOperator.apply(person1);
        System.out.println(person1);
```

BinaryOperator<T>——接收两个T对象，返回T对象

```java
        BinaryOperator<Double> binaryOperator = (x, y) -> {
            return x + y;
        };
        binaryOperator.apply(1.2, 2.1);
```

### 2. 方法引用

#### 2.1 方法引用是什么

是lambda表达式的一个简化写法. 方法引用语法:左边是容器（可以是类名，实例名），中间是" :: "，右边是相应

的方法名。

通过方法引用，可以将方法的引用赋值给一个变量,通过赋值给Function，说明方法引用也是一种函数式接口的书

写方式，Lambda表达式也是一种函数式接口，Lambda表达式一般用于自己提供方法体，而方法引用一般直接引

用现成的方法。

```java
ObjectReference::methodName
```

#### 2.2 一般方法的引用格式

如果是静态方法，则是ClassName::methodName。如 Object ::equals

如果是实例方法，则是Instance::methodName。

```java
Object obj=new Object();
obj::equals;
// 必须与函数式接口结合使用，并且函数式接口的参数为实体类的属性
构造函数.则是ClassName::new
```

详细见**MethodReferenceTest.java**

### 3. Optional

源码解析

```java
package java.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @since 1.8
 */
public final class Optional<T> {
    private static final Optional<?> EMPTY = new Optional<>();

    private final T value;

    private Optional() {
        this.value = null;
    }

    // 返回一个空的 Optional实例
    public static<T> Optional<T> empty() {
        @SuppressWarnings("unchecked")
        Optional<T> t = (Optional<T>) EMPTY;
        return t;
    }

    private Optional(T value) {
        this.value = Objects.requireNonNull(value);
    }

    // 返回具有 Optional的当前非空值的Optional
    public static <T> Optional<T> of(T value) {
        return new Optional<>(value);
    }

    // 返回一个 Optional指定值的Optional，如果非空，则返回一个空的 Optional
    public static <T> Optional<T> ofNullable(T value) {
        return value == null ? empty() : of(value);
    }

    // 如果Optional中有一个值，返回值，否则抛出 NoSuchElementException 。
    public T get() {
        if (value == null) {
            throw new NoSuchElementException("No value present");
        }
        return value;
    }

    // 返回true如果存在值，否则为 false 
    public boolean isPresent() {
        return value != null;
    }

    // 如果存在值，则使用该值调用指定的消费者，否则不执行任何操作。
    public void ifPresent(Consumer<? super T> consumer) {
        if (value != null)
            consumer.accept(value);
    }

    // 如果一个值存在，并且该值给定的谓词相匹配时，返回一个 Optional描述的值，否则返回一个空的 Optional
    public Optional<T> filter(Predicate<? super T> predicate) {
        Objects.requireNonNull(predicate);
        if (!isPresent())
            return this;
        else
            return predicate.test(value) ? this : empty();
    }

    // 如果存在一个值，则应用提供的映射函数，如果结果不为空，则返回一个 Optional结果的 Optional 。
    public<U> Optional<U> map(Function<? super T, ? extends U> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Optional.ofNullable(mapper.apply(value));
        }
    }

    // 如果一个值存在，应用提供的 Optional映射函数给它，返回该结果，否则返回一个空的 Optional 。
    public<U> Optional<U> flatMap(Function<? super T, Optional<U>> mapper) {
        Objects.requireNonNull(mapper);
        if (!isPresent())
            return empty();
        else {
            return Objects.requireNonNull(mapper.apply(value));
        }
    }

    // 如果值存在，就返回值，不存在就返回指定的其他值
    public T orElse(T other) {
        return value != null ? value : other;
    }


    public T orElseGet(Supplier<? extends T> other) {
        return value != null ? value : other.get();
    }

    public <X extends Throwable> T orElseThrow(Supplier<? extends X> exceptionSupplier) throws X {
        if (value != null) {
            return value;
        } else {
            throw exceptionSupplier.get();
        }
    }   
}

```

使用案例：

```Java
Optional.ofNullable(对象).ifPresent(r -> this.riskSuperviseService.saveBatch(r));
```

### 4. Lambda表达式

Lambda 表达式，也可称为闭包，它是推动 Java 8 发布的最重要新特性。

Lambda 允许把**函数**作为一个方法的参数（函数作为参数传递进方法中）。

使用 Lambda 表达式可以使代码变的更加简洁紧凑。

以下是lambda表达式的重要特征:

- **可选类型声明：**不需要声明参数类型，编译器可以统一识别参数值。
- **可选的参数圆括号：**一个参数无需定义圆括号，但多个参数需要定义圆括号。
- **可选的大括号：**如果主体包含了一个语句，就不需要使用大括号。
- **可选的返回关键字：**如果主体只有一个表达式返回值则编译器会自动返回值，大括号需要指定明表达式返回了一个数值。

详细请查看LambdaTest.java

#### 4.1 Lambda运行原理

**一、手动利用记事本编写Java测试代码**

```java
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

```

**二、使用命令行进行编译**

```java
javac TestDemo.java
```

编译后会在当前文件夹生成TestDemo.class与InterDemo.class两个文件

查看TestDemo.class源码

```java
javap -p TestDemo.class
```

得到下面的结果

```java
Compiled from "TestDemo.java"
public class TestDemo {
  public TestDemo();
  public static void main(java.lang.String[]);
  private static void lambda$main$0(java.lang.String);
}
```

这里可以看到有一个构造方法，一个main方法，还有一个私有的静态方法，这个静态方法只要你使用都会生成，是一样的。

这个静态私有方法就是Lambda表达式中的方法实现。也就是Lambda表达式在底层构建中生成了一个

```java
private static void lambda$main$0(java.lang.String message){
	System.out.println(message);
}
```

这个私有静态方法什么时候被调用了呐？

我们对它内部生成的所有接口的源代码进行一个输出，通过如下代码我们可以查看TestDemo底层详细的编译过程

```java
java -Djdk.internal.lambda.dumpProxyClasses TestDemo
```

执行完毕会在当前文件夹生成TestDemo$$Lambda$1.class

查看TestDemo$$Lambda$1.class源码

```java
javap -p TestDemo.class
```

结果

```java
final class TestDemo$$Lambda$1 implements InterDemo {
  private TestDemo$$Lambda$1();
  public void mark(java.lang.String);
}
```

在这里边可以看到第一个是一个构造方法，第二个markUp的实现实际上就是调用了上边的私有静态方法。在调用静态方法的过程中将message传递了过来。

**三、总结**

Lambda表达式在编译的过程中会生成一个私有的静态方法，在静态方法中做了一个方法的基本实现。

另外在编译的同时会针对Lambda表达式的目标接口，生成一个内部类型实现定义的接口，在实现这个接口的方法中完成对这个静态方法具体执行过程的调用

这两个方法在底层解析执行的时候其实他是在底层new 了一个当前内部类接口的对象，通过这个内部类接口的对象调用这个方法的marUp方法。

实际上Lambda表达式的运行过程可以理解为如下代码：

```java
public class TestDemo{
	public static void main(String [] args){
		InterDemo inter=(string)->System.out.println(string);
		inter.markUp("WangJun");
		/**
			new TestDemo$$Lambda$1().markUp("WangJun");
		*/
	}
}
/**
public class TestDemo {
  public TestDemo();
  public static void main(java.lang.String[]);
  private static void lambda$main$0(java.lang.String){
	  System.out.println(message);
  }
}
*/
/**
final class TestDemo$$Lambda$1 implements InterDemo {
  private TestDemo$$Lambda$1();
  public void markUp(java.lang.String){
	  TestDemo.lambda$main$0(java.lang.String message){
		  TestDemo.lambda$main$0(message);
	  }
  }
}
*/
interface InterDemo{
	void markUp(String string);
}
```

### 5. Stream

#### 5.1 为什么需要Stream

Stream 作为 Java 8 的一大亮点，它与 java.io 包里的 InputStream 和 OutputStream 是完全不同的概念。它也不同于 StAX 对 XML 解析的 Stream，也不是 Amazon Kinesis 对大数据实时处理的 Stream。Java 8 中的 Stream 是对集合（Collection）对象功能的增强，它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务和加速处理过程。通常编写并行代码很难而且容易出错, 但使用 Stream API 无需编写一行多线程的代码，就可以很方便地写出高性能的并发程序。所以说，Java 8 中首次出现的 java.util.stream 是一个函数式语言+多核时代综合影响的产物。

JDK1.7 排序取值的实现

```java
List<Transaction> groceryTransactions = new ArrayList<>();
for(Transaction t: transactions){
     if(t.getType() == Transaction.GROCERY){
        groceryTransactions.add(t);
     }
}
Collections.sort(groceryTransactions, new Comparator(){
     public int compare(Transaction t1, Transaction t2){
    	 return t2.getValue().compareTo(t1.getValue());
     }
});
List<Integer> transactionIds = new ArrayList<>();
for(Transaction t: groceryTransactions){
 	transactionsIds.add(t.getId());
}
```

而在 Java 8 使用 Stream，代码更加简洁易读；而且使用并发模式，程序执行速度更快。

JDK1.8 排序取值的实现

```java
List<Integer> transactionsIds = transactions.parallelStream().
 filter(t -> t.getType() == Transaction.GROCERY).
 sorted(comparing(Transaction::getValue).reversed()).
 map(Transaction::getId).
 collect(toList());
```


#### 5.2  什么是流

Stream 不是集合元素，它不是数据结构并不保存数据，它是有关算法和计算的，它更像一个高级版本的 Iterator。原始版本的 Iterator，用户只能显式地一个一个遍历元素并对其执行某些操作；高级版本的 Stream，用户只要给出需要对其包含的元素执行什么操作，比如 “过滤掉长度大于 10 的字符串”、“获取每个字符串的首字母”等，Stream 会隐式地在内部进行遍历，做出相应的数据转换。

Stream 就如同一个迭代器（Iterator），单向，不可往复，数据只能遍历一次，遍历过一次后即用尽了，就好比流水从面前流过，一去不复返。

而和迭代器又不同的是，Stream 可以并行化操作，迭代器只能命令式地、串行化操作。顾名思义，当使用串行方式去遍历时，每个 item 读完后再读下一个 item。而使用并行去遍历时，数据会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出。Stream 的并行操作依赖于 Java7 中引入的 Fork/Join 框架（JSR166y）来拆分任务和加速处理过程。

Stream 的另外一大特点是，数据源本身可以是无限的。

#### 5.3 流的构造与转换

```java
// 1. Individual values
Stream stream = Stream.of("a", "b", "c");
// 2. Arrays
String [] strArray = new String[] {"a", "b", "c"};
stream = Stream.of(strArray);
stream = Arrays.stream(strArray);
// 3. Collections
List<String> list = Arrays.asList(strArray);
stream = list.stream();
```

需要注意的是，对于基本数值型，目前有三种对应的包装类型 Stream：

`IntStream、LongStream、DoubleStream`。当然我们也可以用 Stream<Integer>、Stream<Long> >、Stream<Double>，但是 **boxing** 和 **unboxing** 会很耗时，所以特别为这三种基本数值型提供了对应的**Stream**。

Java 8 中还没有提供其它数值型 **Stream**，因为这将导致扩增的内容较多。而常规的数值型聚合运算可以通过上面三种 Stream 进行。

**流转换为其它数据结构**

```java
// 1. Array
String[] strArray1 = stream.toArray(String[]::new);
// 2. Collection
List<String> list1 = stream.collect(Collectors.toList());
List<String> list2 = stream.collect(Collectors.toCollection(ArrayList::new));
Set set1 = stream.collect(Collectors.toSet());
Stack stack1 = stream.collect(Collectors.toCollection(Stack::new));
// 3. String
String str = stream.collect(Collectors.joining()).toString();
```

一个 Stream 只可以使用一次，上面的代码为了简洁而重复使用了数次。

#### 5.4 Stream常用API

接下来，当把一个数据结构包装成 Stream 后，就要开始对里面的元素进行各类操作了。常见的操作可以归类如下。

- Intermediate（中间状态）
  - map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
- Terminal（终端状态）
  - forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
- Short-circuiting（短路状态：遍历中途停止操作，例如查找第一个满足条件的元素或者limit操作）
  - anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

详细API操作请查看**cn.thislx.lambda.stream**包下源代码

#### 5.5 Collectors API

详细请查看 https://www.jianshu.com/p/7eaa0969b424

#### 5.6 性能分析

详细请查看 https://mp.weixin.qq.com/s/-p-N-K_oY-vGtvBIKUqShA

#### 5.7 parallelStream的注意事项

详细请查看源代码TestStreamAPI05.java
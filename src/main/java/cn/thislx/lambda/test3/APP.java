package cn.thislx.lambda.test3;

import cn.thislx.lambda.test2.Person1;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 测试启动类
 * 1、静态方法引用的使用
 * 类型名称.方法名称()  ==> 类型名称::方法名称
 * 2、实例方法引用的使用
 * 创建类型对应的一个对象 ==> 对应应用::实例方法名称
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 17:05
 **/
public class APP {

    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("zhangsan", "男", 23));
        personList.add(new Person("wangwu", "女", 40));
        personList.add(new Person("lisi", "男", 30));
        personList.add(new Person("zhaoliu", "女", 26));

        // 原始版
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge() - o2.getAge();
            }
        });


        // Lambda版
        Comparator<Person> personComparator = (p1, p2) -> p1.getAge() - p2.getAge();
        Collections.sort(personList, personComparator);


        // Lambda版  Lambda can be replaced with method reference (警告：lambda可以被方法引用替换)
        Comparator<Person> personComparator2 = (p1, p2) -> Person.compareAge(p1, p2);
        Collections.sort(personList, personComparator2);

        // 静态方法引用版
        Collections.sort(personList, Person::compareAge);

        // 实例方法引用的使用
        PersonUtils personUtils = new PersonUtils();
        Collections.sort(personList, personUtils::compareByName);

        // 必须与函数式接口结合使用，并且函数式接口的参数为实体类的属性
        IPerson iPerson = Person::new;
        Person person = iPerson.initPerson("tet", "男", 2);
        System.out.println(person);

    }
}

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
class Person {
    private String name;

    private String gender;

    private int age;

    public static int compareAge(Person p1, Person p2) {
        return p1.getAge() - p2.getAge();
    }
}

class PersonUtils {
    public int compareByName(Person p1, Person p2) {
        return p1.getName().hashCode() - p2.getName().hashCode();
    }
}

interface IPerson {

    Person initPerson(String name, String gender, int age);
}

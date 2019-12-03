package cn.thislx.lambda.test2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 实体类
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/12/3 17:52
 **/
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Persion {
    private String name;

    private int age;

}
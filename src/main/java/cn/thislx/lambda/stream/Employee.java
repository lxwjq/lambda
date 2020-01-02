package cn.thislx.lambda.stream;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 员工实体
 *
 * @author lixiang
 * @version V1.0
 * @date 2020/1/2 13:39
 **/
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements Serializable {

    private Integer id;
    private String name;
    private Integer age;
    private double salary;
}

package cn.thislx.lambda.test1.impl;

import cn.thislx.lambda.test1.LambdaFunction;

/**
 * 函数式接口实现类
 *
 * @author lixiang
 * @version V1.0
 * @date 2019/11/29 11:22
 **/
public class LambdaFunctionImpl implements LambdaFunction {


    @Override
    public boolean ouathUser(String name) {
        if ("admin".equals(name)) {
            return true;
        } else {
            return false;
        }
    }
}

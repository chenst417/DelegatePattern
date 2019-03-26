package com.cn.study.delegatepattern.simple;

public class EmployeeA implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工A，我擅长架构，我现在开始干活，执行：" + command);
    }
}

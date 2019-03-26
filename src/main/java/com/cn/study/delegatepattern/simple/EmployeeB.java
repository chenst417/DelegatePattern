package com.cn.study.delegatepattern.simple;

public class EmployeeB implements IEmployee {

    @Override
    public void doing(String command) {
        System.out.println("我是员工B，我擅长加密，我现在开始干活，执行：" + command);
    }
}

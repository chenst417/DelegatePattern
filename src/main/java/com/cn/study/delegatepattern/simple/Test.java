package com.cn.study.delegatepattern.simple;

public class Test {

    public static void main(String[] args) {

        Boss boss = new Boss();
        boss.command("加密", new Leader());
        System.out.println("----");
        boss.command("架构", new Leader());
    }

}

package com.cn.study.delegatepattern.simple;

import java.util.HashMap;
import java.util.Map;

public class Leader {
    //预先知道每个员工的特长
    private Map<String, IEmployee> register = new HashMap<String, IEmployee>();

    public Leader() {
        register.put("架构", new EmployeeA());
        register.put("加密", new EmployeeB());
    }

    public void doing(String command){
        register.get(command).doing(command);
    }

}

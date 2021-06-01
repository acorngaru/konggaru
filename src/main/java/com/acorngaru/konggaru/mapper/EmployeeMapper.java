package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeMapper {

    public int countItemsByName(String name);
    public List<Employee> searchEmp(HashMap<String, String> map);
    public void addEmp(Employee employee);
    public int updateEmp(Employee employee);
}

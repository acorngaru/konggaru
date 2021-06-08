package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Mapper
@Repository
public interface EmployeeMapper {

    public int countItemsByName(String name);
    public List<Employee> searchEmp(HashMap<String, String> map);
    public void addEmp(Employee employee);
    public int updateEmp(Employee employee);
}

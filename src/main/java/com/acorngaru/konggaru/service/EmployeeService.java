package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.EmployeeMapper;
import com.acorngaru.konggaru.model.Employee;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.print.attribute.standard.MediaSize;
import java.util.HashMap;
import java.util.List;

@Repository
public class EmployeeService implements EmployeeMapper {
    @Autowired
    private SqlSession sqlSession;

    protected static final String NAMESPACE = "employeeMapper";


    @Override
    public int countItemsByName(String name){
        int amount = sqlSession.selectOne(NAMESPACE + ".countItemsByName", name);
        return amount;
    }

    @Override
    public List<Employee> searchEmp(HashMap<String, String> map) {
        List<Employee> list = sqlSession.selectList(NAMESPACE + ".searchEmp", map);
        System.out.println("요기는 searchEMP");
        for (Employee emp : list){
            System.out.println(emp);
        }
        return list;
    }

    @Override
    public void addEmp(Employee employee) {
        sqlSession.insert(NAMESPACE + ".addEmp", employee);
    }

    @Override
    public int updateEmp(Employee employee) {
        int n = sqlSession.update(NAMESPACE + ".updateEmp", employee);
        return n;
    }
}

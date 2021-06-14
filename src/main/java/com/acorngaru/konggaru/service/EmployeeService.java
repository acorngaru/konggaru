package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.EmployeeMapper;
import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;

@Transactional
@Service
public class EmployeeService{

    @Autowired
    EmployeeMapper mapper;

    @Transactional(readOnly = true)
    public Page<Employee> searchEmp(String name, int currentPageNo, int count){
        Page page = new Page();
        page.setPageCount(3); page.setRows(3);

        HashMap<String,String> map = new HashMap<>();
        map.put("first" , Integer.toString((currentPageNo-1)*page.getRows()+1));
        map.put("last", Integer.toString(currentPageNo*page.getRows()));
        map.put("name",name);
        page.process(
                page.getRows(),
                currentPageNo,
                count,
                mapper.searchEmp(map)
        );
        return page;
    }

    public int countItemsByName(String name){
        int amount = mapper.countItemsByName(name);
        return amount;
    }

    public void addEmp(Employee employee) {
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        //employee.setPasswd(encoder.encode(employee.getPasswd()));
        mapper.addEmp(employee);
    }

    public int updateEmp(Employee employee) {
        int n = mapper.updateEmp(employee);
        return n;
    }
}

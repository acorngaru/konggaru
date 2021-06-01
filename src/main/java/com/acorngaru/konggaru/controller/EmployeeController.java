package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping
    public String showEmployeeHome(){ return "employee/list"; }

    @ResponseBody
    @Transactional(readOnly = true)
    @PostMapping(value = "/list")
    public Page showHome(
            @RequestParam("selectName") String name,
            @RequestParam("currentPageNo") int currentPageNo) throws IOException {
        Page page = new Page();
        page.setPageCount(3); page.setRows(3);
        page.setCurrentPageNo(currentPageNo);
        HashMap<String,String> map = new HashMap<>();

        map.put("first" , Integer.toString((page.getCurrentPageNo()-1)*page.getRows()+1));
        map.put("last", Integer.toString(page.getCurrentPageNo()*page.getRows()));
        map.put("name",name);
        page.process(page.getRows(),
                     page.getCurrentPageNo(),
                     service.countItemsByName(name),
                     service.searchEmp(map));

        System.out.println(map);
        System.out.println(page);

        return page;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @PostMapping(value = "/addEmp", headers = {"Accept=application/json"})
    public String addEmp(
            Employee employee
    ){

        System.out.println("추가 되는 사람 == " + employee);
        service.addEmp(employee);
        log.info(String.valueOf(employee));
        return "redirect:/employee";
    }

    @PostMapping(value = "/updateEmp")
    public String updateEmployee(
            @RequestParam("id") int id,
            @RequestParam("name") String name,
            @RequestParam("phone") String phone,
            @RequestParam("role") String  role,
            @RequestParam("salary") int salary,
            @RequestParam("hiredate") String hiredate
    ){
        Employee employee = new Employee(id, name, phone, role, salary, hiredate, null, "0");
        int n = service.updateEmp(employee);
        log.info("업데이트 완료>>>>>>"+String.valueOf(n));
        return "redirect:/employee";
    }
}

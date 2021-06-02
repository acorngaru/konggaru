package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Employee;

import java.util.HashMap;
import java.util.List;

public interface EmployeeMapper {

    public int countItemsByName(String name);
    public List<Employee> searchEmp(HashMap<String, String> map);
    public void addEmp(Employee employee);
    public int updateEmp(Employee employee);
	
    public int RollBookCountItemsByName(String name);
	public List searchRollBook(HashMap<String, String> map);
	public int RollBookCountItemsByNameInTime(String name);
	public List searchRollBookInTime(HashMap<String, String> map);
	public int RollBookCountItemsByNameoutTime(String name);
	public List searchRollBookoutTime(HashMap<String, String> map);
	public Employee searchId(String clockId);
	public void addInTime(HashMap<String, String> map);
	public String searchInTime(String id);
	public void updateOutTime(HashMap<String, String> map);
	public int checkintime(String id);
}

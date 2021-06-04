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
	
    public int RollBookCountItemsByName(String name);
	public List searchRollBook(HashMap<String, String> map);
	public List searchRollBookInTime(HashMap<String, String> map);
	public List searchRollBookoutTime(HashMap<String, String> map);
	public int RollBookCountItemsByNameInTime(String name);
	public int RollBookCountItemsByNameoutTime(String name);
	public Employee searchId(String clockId);
	public int checkintime(String id);
	public void addInTime(HashMap<String, String> map);
	public String searchInTime(String id);
	public int searchSumWorkTime(HashMap<String, String> map);
	public void updateWorkTime(HashMap<String, String> map);
	public void updateOutTime(HashMap<String, String> map);
	public int idCheck(String clockId);
}

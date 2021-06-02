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

    @Override
	public int RollBookCountItemsByName(String name) {
        int amount = sqlSession.selectOne(NAMESPACE + ".RollBookCountItemsByName", name);
        return amount;
	}

    @Override
	public List searchRollBook(HashMap<String, String> map) {
		List<Employee> list = sqlSession.selectList(NAMESPACE + ".searchRollBook", map);
		System.out.println("searchRollBook service부분 list = "+ list);
		return list;
	}
    
    @Override
	public int RollBookCountItemsByNameInTime(String name) {
        int amount = sqlSession.selectOne(NAMESPACE + ".RollBookCountItemsByNameInTime", name);
        return amount;
	}

    @Override
	public List searchRollBookInTime(HashMap<String, String> map) {
		List<Employee> list = sqlSession.selectList(NAMESPACE + ".searchRollBookInTime", map);
		return list;
	}

    @Override
	public int RollBookCountItemsByNameoutTime(String name) {
        int amount = sqlSession.selectOne(NAMESPACE + ".RollBookCountItemsByNameoutTime", name);
        return amount;
	}

    @Override
	public List searchRollBookoutTime(HashMap<String, String> map) {
		List<Employee> list = sqlSession.selectList(NAMESPACE + ".searchRollBookoutTime", map);
		return list;
	}

    @Override
	public Employee searchId(String clockId) {
		Employee emp = sqlSession.selectOne(NAMESPACE + ".searchId", clockId);
		return emp;
	}

    @Override
	public void addInTime(HashMap<String, String> map) {
		sqlSession.selectOne(NAMESPACE + ".addInTime", map);
	}

    @Override
	public String searchInTime(String id) {
		String inTime = sqlSession.selectOne(NAMESPACE + ".searchInTime", id);
		return inTime;
	}

    @Override
	public void updateOutTime(HashMap<String, String> map) {
		sqlSession.update(NAMESPACE + ".updateOutTime", map);
		
	}

    @Override
	public int checkintime(String id) {
		int n = sqlSession.selectOne(NAMESPACE + ".checkintime", id);
		return n;
	}
    
    
	public int searchSumWorkTime(String id) {
		int n = sqlSession.selectOne(NAMESPACE + ".searchSumWorkTime", id);
		return n;
	}

	public void updateWorkTime(HashMap<String, String> map) {
		sqlSession.update(NAMESPACE + ".updateWorkTime", map);
	}


}

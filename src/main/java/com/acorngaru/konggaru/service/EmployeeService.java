package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.EmployeeMapper;
import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Rollbook;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

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
        mapper.addEmp(employee);
    }

    public int updateEmp(Employee employee) {
        int n = mapper.updateEmp(employee);
        return n;
    }

	public int RollBookCountItemsByName(String name, String ClockSearchType) {
		int amount = 0;
		if(ClockSearchType.equals("name")) { //이름검색
			amount = mapper.RollBookCountItemsByName(name);
        }else if(ClockSearchType.equals("inTime")) { //출근시간검색
        	amount = mapper.RollBookCountItemsByNameInTime(name);
        }else { //퇴근시간검색
        	amount = mapper.RollBookCountItemsByNameoutTime(name);
        }
		return amount;
	}

	@Transactional(readOnly = true)
	public Page searchRollBook(String name, int currentPageNo, int count,String ClockSearchType) {
		Page page = new Page();
		page.setPageCount(3); page.setRows(10);
		HashMap<String,String> map = new HashMap<>();
		map.put("first" , Integer.toString((currentPageNo-1)*page.getRows()+1));
        map.put("last", Integer.toString(currentPageNo*page.getRows()));
        map.put("name",name);
        List rollBook = null;
        if(ClockSearchType.equals("name")) { //이름검색
        	rollBook = mapper.searchRollBook(map);
        }else if(ClockSearchType.equals("inTime")) { //출근시간검색
        	rollBook = mapper.searchRollBookInTime(map);
        }else { //퇴근시간검색
        	rollBook = mapper.searchRollBookoutTime(map);
        }
        page.process(
                page.getRows(),
                currentPageNo,
                count,
                rollBook  
        );
        
		return page;
	}

	public HashMap<String, String> searchId(String clockId) {
		Employee emp = mapper.searchId(clockId);
		
		HashMap<String,String> map = new HashMap<>();
		   map.put("id", Integer.toString(emp.getId()));
		   map.put("name",emp.getName());
		
		return map;
	}

	public HashMap<String, String> addInTime(String id, String name) {
		HashMap<String, String> map = new HashMap<String, String>();
		
	   		//현재 년 월 시 가져와서 String 으로 inTime(출근시간)에 저장
		 		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
		 		Date currentTime = new Date ( );
		 		String clockin = formatter.format ( currentTime );
		 		
		 		map.put("name", name);
				map.put("id", id);
				map.put("clockin",clockin);
				
				mapper.addInTime(map);	
		
		return map;
	}

	public int checkintime(String id) {
		int n = mapper.checkintime(id);
        return n;
	}

	public HashMap<String, String> updateOutTime(String id, String name) {
		//현재 년 월 시 가져와서 String 으로 outTime(퇴근시간)에 저장
 		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
 		Date currentTime = new Date ( );
 		String clockout = formatter.format ( currentTime );
 		
 		//yyyy.MM.dd HH:mm 형식의 퇴근시간을 split으로 잘라서 outHour(시), outMinute(분) 으로 저장
		String[] splitOutTime = clockout.split(" ",2);
		System.out.println(splitOutTime[1] );
		
		String[] outClock = splitOutTime[1].split(":");
		int outHour =Integer.parseInt(outClock[0]);
		int outMinute =Integer.parseInt(outClock[1]);
		
		//db의 출근시간을 가져옴
		String inTime = mapper.searchInTime(id);
		
		//yyyy.MM.dd HH:mm 형식의 출근시간을 split으로 잘라서 inHour(시), inMinute(분) 으로 저장
		String[] splitinTime = inTime.split(" ",2);
		System.out.println(splitinTime[1] );
		
		String[] inClock = splitinTime[1].split(":");
		int inHour =Integer.parseInt(inClock[0]);
		int inMinute =Integer.parseInt(inClock[1]);
		
		//근무시간 계산을위해 퇴근시 - 출근시 : 퇴근분 - 출근분 형식으로 overTime에 저장
		String workTime = Integer.toString(((outHour-inHour)*60) + (outMinute-inMinute));
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name",name);
		map.put("id",id);
		map.put("workTime",workTime);
		map.put("clockout",clockout);
		
		mapper.updateOutTime(map);
		
		return map;
	}

	public void updateWorkTime(HashMap<String, String> map) {
		
		int sumWorkTime = mapper.searchSumWorkTime(map);
		String work_time = (sumWorkTime/60)+"시간"+(sumWorkTime%60)+"분";
		
		map.put("work_time",work_time);
		
		mapper.updateWorkTime(map);		
	}

	public int idCheck(String id) {
		int n = mapper.idCheck(id);
		return n;
	}



    
    
}

package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.RollBookMapper;
import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.model.Rollbook;

import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Transactional
@Service
public class RollbookService {

	@Autowired
	RollBookMapper mapper;

	public int RollBookCountItemsByName(String name, String ClockSearchType) {
		int amount = 0;
		if (ClockSearchType.equals("name")) { // 이름검색
			amount = mapper.RollBookCountItemsByName(name);
		} else if (ClockSearchType.equals("inTime")) { // 출근시간검색
			amount = mapper.RollBookCountItemsByNameInTime(name);
		} else { // 퇴근시간검색
			amount = mapper.RollBookCountItemsByNameoutTime(name);
		}
		return amount;
	}

	@Transactional(readOnly = true)
	public Page searchRollBook(String name, int currentPageNo, int count, String ClockSearchType) {
		Page page = new Page();
		page.setPageCount(3);
		page.setRows(10);
		HashMap<String, String> map = new HashMap<>();
		map.put("first", Integer.toString((currentPageNo - 1) * page.getRows() + 1));
		map.put("last", Integer.toString(currentPageNo * page.getRows()));
		map.put("name", name);
		List rollBook = null;
		if (ClockSearchType.equals("name")) { // 이름검색
			rollBook = mapper.searchRollBook(map);
		} else if (ClockSearchType.equals("inTime")) { // 출근시간검색
			rollBook = mapper.searchRollBookInTime(map);
		} else { // 퇴근시간검색
			rollBook = mapper.searchRollBookoutTime(map);
		}
		page.process(page.getRows(), currentPageNo, count, rollBook);

		return page;
	}

	public HashMap<String, String> searchId(String clockId) {
		Employee emp = mapper.searchId(clockId);

		HashMap<String, String> map = new HashMap<>();
		map.put("id", Integer.toString(emp.getId()));
		map.put("name", emp.getName());

		System.out.println("서비스에서 보내는 맵" + map);
		return map;
	}

	public HashMap<String, String> addInTime(String id, String name) {
		HashMap<String, String> map = new HashMap<String, String>();

		// 현재 년 월 시 가져와서 String 으로 inTime(출근시간)에 저장
		String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

		map.put("name", name);
		map.put("id", id);
		map.put("clockin", currentTime);

		mapper.addInTime(map);

		return map;
	}

	public int checkintime(String id) {
		int n = mapper.checkintime(id);
		return n;
	}

	public HashMap<String, String> updateOutTime(String id, String name) {
		// 현재 년 월 시 가져와서 String 으로 outTime(퇴근시간)에 저장
		String stringCurrentDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
		
		// 현재 년 월 시를 계산을위해 Date타입으로 변환
		LocalDateTime dateCurrentDateTime = LocalDateTime.parse(stringCurrentDateTime, DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));
		
		//출근시간을 불러온 후 계산을 위해 LocalTime으로 변환
		String stringInTime = mapper.searchInTime(id);	
		LocalDateTime dateInTime = LocalDateTime.parse(stringInTime, DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm"));

		//출퇴근시간의 시간차, 분차 계산
		long hours = ChronoUnit.HOURS.between(dateInTime, dateCurrentDateTime);
		long minutes = ChronoUnit.MINUTES.between(dateInTime, dateCurrentDateTime);
		
		//근무시간 분으로 환산
		String workTime = Long.toString((hours * 60) + minutes);
		
		HashMap<String, String> map = new HashMap<String, String>();
		map.put("name", name);
		map.put("id", id);
		map.put("workTime", workTime);
		map.put("clockout", stringCurrentDateTime);
		
		//퇴근시간과 근무시간 update
		mapper.updateOutTime(map);

		return map;
	}

	public void updateWorkTime(HashMap<String, String> map) {

		int sumWorkTime = mapper.searchSumWorkTime(map);
		String work_time =(sumWorkTime/60)+":"+(sumWorkTime%60);
		map.put("work_time", work_time);
		mapper.updateWorkTime(map);
	}

	public int idCheck(String id) {
		int n = mapper.idCheck(id);
		return n;
	}

}

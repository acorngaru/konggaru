package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.RollbookService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import javax.servlet.http.HttpSession;

@Slf4j
@Controller
@RequestMapping("/rollbook")
public class RollBookController {

	@Autowired
	RollbookService service;

	@GetMapping
	public String showRollBookHome() {
		return "employee/rollbookList";
	}

	// 데이터 출력 부분
	@ResponseBody
	@PostMapping(value = "/ClockInOut")
	public Page showClockHome(@RequestParam("ClockselectName") String name,
			@RequestParam("ClockSearchType") String ClockSearchType, // 검색기능
			@RequestParam("currentPageNo") int currentPageNo) throws IOException {
		System.out.println("employee/clockList에 들어옴");

		int count = service.RollBookCountItemsByName(name, ClockSearchType);
		Page page = service.searchRollBook(name, currentPageNo, count, ClockSearchType);

		System.out.println("ClockInOut 부분 count,page 확인====" + count + "\t" + page);
		return page;
	}

	// 사번 입력 했을 때
	@ResponseBody
	@PostMapping(value = "/idCheck")
	public HashMap<String, String> idCheck(@RequestParam("clockId") String clockId) throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		// employee db접속 후 입력한 사번 확인.
		int checkid = service.idCheck(clockId);
		if (checkid == 0) {
			map.put("mesg", "일치하는 사번이 없습니다.");
		} else {
			map = service.searchId(clockId);
		}
		return map;
	}

	// 출근버튼 눌렀을 때
	@ResponseBody
	@PostMapping(value = "/clockIn")
	public HashMap<String, String> clockIn(@RequestParam("id") String id, @RequestParam("name") String name)
			throws IOException {

		int checkintime = service.checkintime(id);// 출근여부 확인

		HashMap<String, String> map = new HashMap<String, String>();

		if (checkintime == 0) {// 출근 데이터 없음
			map = service.addInTime(id, name);
		} else {// 출근 데이터 있음
			map.put("mesg", "이미 출근 하셨습니다.");
			map.put("name", name);
		}
		return map;
	}

	// 퇴근버튼 눌렀을 때
	@ResponseBody
	@PostMapping(value = "/clockOut")
	public HashMap<String, String> clockOut(@RequestParam("id") String id, @RequestParam("name") String name)
			throws IOException {
		HashMap<String, String> map = new HashMap<String, String>();
		int checkintime = service.checkintime(id);
		if (checkintime != 0) {
			map = service.updateOutTime(id, name);
			service.updateWorkTime(map);
		} else {
			map.put("mesg", "출근 전입니다.");
			map.put("name", name);
		}

		return map;
	}

}

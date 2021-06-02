package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Employee;
import com.acorngaru.konggaru.model.Page;
import com.acorngaru.konggaru.service.EmployeeService;
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
@RequestMapping("/RollBook")
public class RollBookController {

    @Autowired
    EmployeeService service;
    
    @GetMapping
    public String showRollBookHome(){ return "employee/ClockInOut"; }
    
    @ResponseBody
    @PostMapping(value = "/ClockInOut")
    public Page showClockHome(
            @RequestParam("ClockselectName") String name,
            @RequestParam("ClockSearchType") String ClockSearchType,
            @RequestParam("currentPageNo") int currentPageNo) throws IOException {
        System.out.println("employee/clockList에 들어옴");
        Page page = new Page();
        page.setPageCount(3); page.setRows(10);
        page.setCurrentPageNo(currentPageNo);
        HashMap<String,String> map = new HashMap<>();

        map.put("first" , Integer.toString((page.getCurrentPageNo()-1)*page.getRows()+1));
        map.put("last", Integer.toString(page.getCurrentPageNo()*page.getRows()));
        map.put("name",name);
       if(ClockSearchType.equals("inTime")) {page.process(page.getRows(), page.getCurrentPageNo(), service.RollBookCountItemsByNameInTime(name), service.searchRollBookInTime(map));}
       else if(ClockSearchType.equals("outTime")) {page.process(page.getRows(), page.getCurrentPageNo(), service.RollBookCountItemsByNameoutTime(name), service.searchRollBookoutTime(map));}
       else { page.process(page.getRows(), page.getCurrentPageNo(), service.RollBookCountItemsByName(name), service.searchRollBook(map));}

        System.out.println(map);
        System.out.println(page);

        return page;
    }
    
   @ResponseBody
   @PostMapping(value="/idCheck")
   public HashMap<String, String> idCheck(
		   @RequestParam("ClockId") String ClockId) throws IOException{
	   System.out.println("idCheck 들어옴");
	   Employee emp = service.searchId(ClockId);
	   String id = Integer.toString(emp.getId());
	   HashMap<String,String> map = new HashMap<>();
	   map.put("id",id);
	   map.put("name",emp.getName());
	   
	   return map;
   }
   
   @ResponseBody
   @PostMapping(value="/ClockIn")
   public HashMap<String, String> ClockIn(
		   @RequestParam("id") String id,
		   @RequestParam("name") String name) throws IOException{
	   		
	   		int checkintime = service.checkintime(id);
	   		HashMap<String, String> map = new HashMap<String, String>();
	   		
	   		if(checkintime==0) {
	   		//현재 년 월 시 가져와서 String 으로 inTime(출근시간)에 저장
		 		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
		 		Date currentTime = new Date ( );
		 		String clockin = formatter.format ( currentTime );
		 		System.out.println ( clockin );
		   
		 		//HashMap에 cName(이름),cId(id),inTime(출근시간) 저장
				
				map.put("name", name);
				map.put("id", id);
				map.put("clockin",clockin);
				
				service.addInTime(map);	   
	   		}else {
	   			 map.put("mesg", "이미 출근 하셨습니다.");
	   			 map.put("name", name);
	   			 return map;
	   		}
	   		
	   		return map;
   }
   
   @ResponseBody
   @PostMapping(value="/ClockOut")
   public HashMap<String, String> ClockOut(
		   @RequestParam("id") String id,
		   @RequestParam("name") String name) throws IOException{
	   
	   		HashMap<String, String> map = new HashMap<String, String>();
	   		
	   		int checkintime = service.checkintime(id);
	   		
	   		if(checkintime!=0) {
	 //현재 년 월 시 가져와서 String 으로 outTime(퇴근시간)에 저장
	 		SimpleDateFormat formatter = new SimpleDateFormat ( "yyyy.MM.dd HH:mm", Locale.KOREA );
	 		Date currentTime = new Date ( );
	 		String clockout = formatter.format ( currentTime );
	 		System.out.println ( clockout );
	   
			
			//yyyy.MM.dd HH:mm 형식의 퇴근시간을 split으로 잘라서 outHour(시), outMinute(분) 으로 저장
			String[] splitOutTime = clockout.split(" ",2);
			System.out.println(splitOutTime[1] );
			
			String[] outClock = splitOutTime[1].split(":");
			int outHour =Integer.parseInt(outClock[0]);
			int outMinute =Integer.parseInt(outClock[1]);
			
			System.out.println("퇴근시간"+outHour+":"+outMinute);
			
			//db의 출근시간을 가져옴
			String inTime = service.searchInTime(id);
			
			//yyyy.MM.dd HH:mm 형식의 출근시간을 split으로 잘라서 inHour(시), inMinute(분) 으로 저장
			String[] splitinTime = inTime.split(" ",2);
			System.out.println(splitinTime[1] );
			
			String[] inClock = splitinTime[1].split(":");
			int inHour =Integer.parseInt(inClock[0]);
			int inMinute =Integer.parseInt(inClock[1]);
			
			//근무시간 계산을위해 퇴근시 - 출근시 : 퇴근분 - 출근분 형식으로 overTime에 저장
			String workTime = Integer.toString(((outHour-inHour)*60) + (outMinute-inMinute));
						
			map.put("name",name);
			map.put("id",id);
			map.put("workTime",workTime);
			map.put("clockout",clockout);
			
			service.updateOutTime(map);
			
			int sumWorkTime = service.searchSumWorkTime(id);
			String empWorkTime = (sumWorkTime/60)+"시간"+(sumWorkTime%60)+"분";
			map.put("work_time",empWorkTime);
			service.updateWorkTime(map);
	   		}else {
	   			map.put("mesg", "출근 전입니다.");
	   			map.put("name", name);
	   			 return map;
	   		}
			
	   return map;
   }
   
}

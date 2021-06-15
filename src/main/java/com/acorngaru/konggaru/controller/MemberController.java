package com.acorngaru.konggaru.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acorngaru.konggaru.model.Cart;
import com.acorngaru.konggaru.model.Member;
import com.acorngaru.konggaru.service.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	
//	private final ObjectMapper objectMapper;
	private final MemberService memberService;

	@GetMapping("/mypage")
    public String showMyPage() {
    	return "member/mypage";
    }

	@PostMapping("/show")
	@ResponseBody
	public Member showMyPageById(@RequestBody int id) throws Exception {
		return memberService.showMyPageById(id);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public void updateMyPage(@ModelAttribute Member member) throws Exception {
		memberService.updateMyPage(member);
	}
    
}

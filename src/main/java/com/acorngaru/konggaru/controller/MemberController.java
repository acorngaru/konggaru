package com.acorngaru.konggaru.controller;

import com.acorngaru.konggaru.model.Member;
import com.acorngaru.konggaru.model.Response;
import com.acorngaru.konggaru.security.MemberDetails;
import com.acorngaru.konggaru.service.MemberService;
import com.acorngaru.konggaru.service.OrderService;
import com.acorngaru.konggaru.util.CurrentUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class MemberController {
	private final MemberService memberService;
	private final OrderService orderService;
	private final ObjectMapper objectMapper;

	@GetMapping("/mypage")
    public String showMyPage(Model model, @CurrentUser MemberDetails memberDetails) throws Exception {
    	model.addAttribute("member", objectMapper.writeValueAsString(memberDetails.member));
    	model.addAttribute("orders", objectMapper.writeValueAsString(orderService.findOrdersByMemberId(memberDetails.member.getMemberId())));

		return "member/mypage";
    }

	@PostMapping("/show")
	@ResponseBody
	public Member showMyPageById(@RequestBody int id) throws Exception {
		return memberService.showMyPageById(id);
	}

	@GetMapping("/current")
	@ResponseBody
	public Response<Member> findCurrentMember(@CurrentUser MemberDetails memberDetails) {
		return memberDetails.member == null ? Response.ERROR() : Response.OK(memberDetails.member);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public void updateMyPage(@ModelAttribute Member member) throws Exception {
		memberService.updateMyPage(member);
	}
    
}

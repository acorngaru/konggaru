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
	private final ObjectMapper objectMapper;

	@GetMapping("/mypage")
    public String showMyPage(Model model, @CurrentUser MemberDetails memberDetails) throws Exception {
    	model.addAttribute(
    			"member", objectMapper.writeValueAsString(memberService.findMemberById(memberDetails.member.getNickName()))
		);

		return "member/mypage";
    }

	@GetMapping("/current")
	@ResponseBody
	public Response<Member> findCurrentMember(@CurrentUser MemberDetails memberDetails) {
		return memberDetails.member == null ? Response.ERROR() : Response.OK(memberDetails.member);
	}
	
	@PostMapping("/update")
	@ResponseBody
	public Response<?> updateMyPage(@RequestBody Member member) throws Exception {
		memberService.updateMyPage(member);

		return Response.OK();
	}

	@GetMapping("/check")
	@ResponseBody
	public Response<?> check(@RequestParam(value = "email", required = false) String email,
							 @RequestParam(value = "phoneNumber", required = false) String phoneNumber) throws Exception {
		if (email != null) {
			return memberService.isDuplicateEmail(email) ? Response.ERROR() : Response.OK();
		} else {
			return memberService.isDuplicatePhoneNumber(phoneNumber) ? Response.ERROR() : Response.OK();
		}
	}
}

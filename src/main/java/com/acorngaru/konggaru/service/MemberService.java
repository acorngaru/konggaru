package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Member;

public interface MemberService {
	
	Member findMemberById(String nickName) throws Exception;
	void updateMyPage(Member member) throws Exception;
	boolean isDuplicateEmail(String email) throws Exception;
	boolean isDuplicatePhoneNumber(String phoneNumber) throws Exception;
}

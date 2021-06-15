package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Member;

public interface MemberService {
	
	Member showMyPageById(int id) throws Exception;
	int updateMyPage(Member member) throws Exception;
	
}

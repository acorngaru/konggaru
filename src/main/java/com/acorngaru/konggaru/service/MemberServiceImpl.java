package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.MemberMapper;
import com.acorngaru.konggaru.model.Member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
    private final MemberMapper memberMapper;
    
    @Override
    public Member showMyPageById(int id) throws Exception {
    	return memberMapper.showMyPageById(id);
    }

	@Override
	public int updateMyPage(Member member) throws Exception {
		return memberMapper.updateMyPage(member);
	}
    
}

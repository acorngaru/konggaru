package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.mapper.MemberMapper;
import com.acorngaru.konggaru.model.Member;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Service("memberService")
public class MemberServiceImpl implements MemberService {
	
    private final MemberMapper memberMapper;
    
    @Override
    public Member findMemberById(String nickName) throws Exception {
    	return memberMapper.getMemberById(nickName);
    }

    @Transactional(rollbackFor = Exception.class)
	@Override
	public void updateMyPage(Member member) throws Exception {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

		member.setPassword(encoder.encode(member.getPassword()));

		memberMapper.update(member);
	}

	@Override
	public boolean isDuplicateEmail(String email) throws Exception {
		return memberMapper.countEmail(email) > 0;
	}

	@Override
	public boolean isDuplicatePhoneNumber(String phoneNumber) throws Exception {
		return memberMapper.countPhoneNumber(phoneNumber) > 0;
	}
}

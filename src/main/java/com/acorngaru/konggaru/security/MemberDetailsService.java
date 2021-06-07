package com.acorngaru.konggaru.security;

import com.acorngaru.konggaru.mapper.MemberMapper;
import com.acorngaru.konggaru.model.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    MemberMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        System.out.println(s);
        Member member= mapper.getMemberById(s);
        System.out.println(member);
        MemberDetails md = new MemberDetails();
        md.member = member;
        System.out.println(md);
        return md;
    }

    public int signUp(Member member){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPwd(encoder.encode(member.getPwd()));

        return mapper.signUp(member);
    }

    public int idChk(String nickName) {
        int c = mapper.checkNickName(nickName);
        return c;

    }
}

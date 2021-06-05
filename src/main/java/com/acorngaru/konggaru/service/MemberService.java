package com.acorngaru.konggaru.service;

import com.acorngaru.konggaru.model.Member;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

public class MemberService implements UserDetailsService {
    @Transactional
    public void joinMember(Member member){
        BCryptPasswordEncoder passwordEncoder=new BCryptPasswordEncoder();
        member.setPwd(passwordEncoder.encode(member.getPwd()));

    }
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        return null;
    }
}

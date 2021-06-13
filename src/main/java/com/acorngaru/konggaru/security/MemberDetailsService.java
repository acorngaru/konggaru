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
        try {

            Member member= mapper.getMemberById(s);
            MemberDetails md = new MemberDetails();
            md.member = member;
            return md;
        }catch (Exception e){
            return null;
        }

    }

    public int signUp(Member member){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        member.setPassword(encoder.encode(member.getPassword()));
        return mapper.signUp(member);
    }

    public boolean idChk(String nickName) {
        int c = mapper.checkNickName(nickName);
        if (c>0)
            return true;
        else
            return false;

    }
}

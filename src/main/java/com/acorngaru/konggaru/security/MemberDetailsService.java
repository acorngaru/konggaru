package com.acorngaru.konggaru.security;

import com.acorngaru.konggaru.mapper.IngredientPageMapper;
import com.acorngaru.konggaru.mapper.MemberMapper;
import com.acorngaru.konggaru.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MemberDetailsService implements UserDetailsService {
    @Autowired
    MemberMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Member member= mapper.getMemberById(s);
        MemberDetails md = new MemberDetails();
        md.member = member;
        return md;
    }
}

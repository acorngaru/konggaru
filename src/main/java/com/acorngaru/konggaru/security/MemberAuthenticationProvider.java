package com.acorngaru.konggaru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

public class MemberAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MemberDetailsService memberDetailsService;
    @Autowired
    private EmpDetailsService empDetailsService;
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = (String)authentication.getPrincipal();

        MemberDetails md = (MemberDetails) memberDetailsService.loadUserByUsername(id);
        if (memberDetailsService.idChk(id)){
            List<GrantedAuthority> roles = (List<GrantedAuthority>) md.getAuthorities();
            return new UsernamePasswordAuthenticationToken(id,md.member.getPassword(),roles);
        }else{
            EmpDetails ed = (EmpDetails) empDetailsService.loadUserByUsername(id);
            List<GrantedAuthority> roles = (List<GrantedAuthority>) ed.getAuthorities();
            return new UsernamePasswordAuthenticationToken(id,ed.emp.getPasswd(),roles);

        }
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchPassword(String pw , String pwd){
        return pw.equals(pwd);
    }
}

package com.acorngaru.konggaru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class MemberAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MemberDetailsService memberDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = (String)authentication.getPrincipal();
        String pwd= (String)authentication.getCredentials();
        MemberDetails md = (MemberDetails) memberDetailsService.loadUserByUsername(id);
        if(!matchPassword(pwd,md.member.getPwd())){
            throw new BadCredentialsException(id);
        }

        return new UsernamePasswordAuthenticationToken(id,pwd,md.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchPassword(String pw , String pwd){
        return pw.equals(pwd);
    }
}

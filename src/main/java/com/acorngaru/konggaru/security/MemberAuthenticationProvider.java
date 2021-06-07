package com.acorngaru.konggaru.security;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

public class MemberAuthenticationProvider implements AuthenticationProvider {
    @Autowired
    private MemberDetailsService memberDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String id = (String)authentication.getPrincipal();
        String pwd= authentication.getCredentials().toString();
        MemberDetails md = (MemberDetails) memberDetailsService.loadUserByUsername(id);
        System.out.println(">>>>>>>>>>> get pwd" + pwd);
        System.out.println(md.member.getPwd());
        List<GrantedAuthority> roles = (List<GrantedAuthority>) md.getAuthorities();
        System.out.println(roles);
        return new UsernamePasswordAuthenticationToken(id,md.member.getPwd(),roles);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchPassword(String pw , String pwd){
        return pw.equals(pwd);
    }
}

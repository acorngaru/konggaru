package com.acorngaru.konggaru.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

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

            return login(md);
        }else{
            EmpDetails ed = (EmpDetails) empDetailsService.loadUserByUsername(id);
            List<GrantedAuthority> roles = (List<GrantedAuthority>) ed.getAuthorities();
            return new UsernamePasswordAuthenticationToken(id,ed.emp.getPasswd(),roles);

        }
    }
    public Authentication login(MemberDetails mds) {
        UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(
                new AccountAdapter(mds),
                mds.member.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_MEMBER")));
        SecurityContextHolder.getContext().setAuthentication(token);
        return token;
    }
    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
    private boolean matchPassword(String pw , String pwd){
        return pw.equals(pwd);
    }
}

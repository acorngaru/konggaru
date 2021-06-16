package com.acorngaru.konggaru.security;

import com.acorngaru.konggaru.model.Employee;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class EmpDetails implements UserDetails {
    Employee emp;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        ArrayList<GrantedAuthority> authList = new ArrayList<GrantedAuthority>();
        authList.add(()->("ROLE_ADMIN"));
        return authList;
    }

    @Override
    public String getPassword() {
        return "{noop}"+emp.getPhone();
    }

    @Override
    public String getUsername() {
        return emp.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}

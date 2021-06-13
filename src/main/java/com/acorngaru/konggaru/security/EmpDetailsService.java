package com.acorngaru.konggaru.security;

import com.acorngaru.konggaru.mapper.EmployeeMapper;
import com.acorngaru.konggaru.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmpDetailsService implements UserDetailsService {

    @Autowired
    EmployeeMapper mapper;
    @Override
    public UserDetails loadUserByUsername(String s)throws UsernameNotFoundException{
        try{
            Employee emp = mapper.getEmpById(s);
            EmpDetails ed =new EmpDetails();
            ed.emp= emp;


            return ed;
        }catch (Exception e){
            return null;
        }
    }

    public int idChk(String name){
        int check = mapper.checkEmpName(name);
        if (check >0)
            check = 1;
        else
            check=0;
        return check;
    }

}
package com.acorngaru.konggaru.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Member")
public class Member   {
    String pwd;
    String nickName;
    String email;
    int phoneNumber;
    int jumin;
    int mileage;
    int memberId;
    String auth;


}

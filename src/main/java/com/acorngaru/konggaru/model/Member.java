package com.acorngaru.konggaru.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Member")
public class Member {
    String name;
    String password;
    String nickName;
    String email;
    int phoneNumber;
    int point;
    int memberId;
    String auth;
}

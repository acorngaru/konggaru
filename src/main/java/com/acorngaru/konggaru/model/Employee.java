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
@Alias("Employee")
public class Employee {

    //고유 번호
    int id;

    //이름
    String name;

    //비밀번호
    String passwd;

    //권한
    String auth;

    //전화번호
    String phone;

    //직책
    String role;

    //봉급
    int salary;

    //고용일
    String hiredate;

    //퇴사일
    String resignation;

    //근무 시간
    String work_time;
}

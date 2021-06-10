package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Member")
public class Member {
    // 고유번호
    private int id;

    // 이름
    private String name;

    // 닉네임
    private String nickName;

    // 이메일
    private String email;

    // 전화번호
    private String phoneNumber;

    // 비밀번호
    private String password;

    // 포인트 점수
    private int point;

    // 권한
    private String auth;

    // 멤버 주문
    private List<Order> orders;

    // 멤버 장바구니
    private List<Cart> carts;
}

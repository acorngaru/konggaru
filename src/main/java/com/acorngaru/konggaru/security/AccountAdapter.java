package com.acorngaru.konggaru.security;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.acorngaru.konggaru.util.CurrentUser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Slf4j
@Getter
public class AccountAdapter extends User {
    private MemberDetails member;

    public AccountAdapter(MemberDetails memberDetails) {

        super(memberDetails.member.getName(), memberDetails.member.getPassword(), memberDetails.getAuthorities());

        this.member = memberDetails;
    }

    public MemberDetails getAccount(@CurrentUser MemberDetails memberDetails) {
        return memberDetails;
    }

}

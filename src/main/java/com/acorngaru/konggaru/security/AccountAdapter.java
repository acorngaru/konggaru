package com.acorngaru.konggaru.security;

import com.acorngaru.konggaru.util.CurrentUser;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;

@Slf4j
@Getter
public class AccountAdapter extends User {
    private MemberDetails memberDetails;

    public AccountAdapter(MemberDetails memberDetails) {

        super(memberDetails.member.getName(), memberDetails.member.getPassword(), memberDetails.getAuthorities());

        this.memberDetails = memberDetails;
    }

    public MemberDetails getAccount(@CurrentUser MemberDetails memberDetails) {
        return memberDetails;
    }

}

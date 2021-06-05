package com.acorngaru.konggaru.mapper;


import com.acorngaru.konggaru.model.Member;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
@Mapper
@Repository
public interface MemberMapper {

    @Insert("INSERT INTO member(pwd, nick_name, email, phone_number, jumin, mileage, member_id, auth)" +
            "VALUES(#{pwd}, #{nickName}, #{email}, #{phoneNumber}, #{jumin}, #{mileage},#{memberId},'MEMBER');")
    public int signIn(Member member);

    @Select(
         "Select * from member where nickName = #{id}"
    )
    Member getMemberById(String id);
}

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
            "VALUES(#{pwd}, #{nickName}, #{email}, #{phoneNumber}, #{jumin}, #{mileage}, #{memberId},#{auth})")
    public int signUp(Member member);

    @Select(
         "Select * from member where nick_name = #{id}"
    )
    Member getMemberById(String id);
    @Select(
            "Select count(*) from member where nick_name = #{nickName}"
    )
    int checkNickName(String nickName);
}

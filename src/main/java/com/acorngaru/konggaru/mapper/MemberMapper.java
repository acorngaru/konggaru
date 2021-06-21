package com.acorngaru.konggaru.mapper;

import com.acorngaru.konggaru.model.Member;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface MemberMapper {

    @Insert("INSERT INTO member(password,name ,nick_name, email, phone_number, point, id, auth)" +
            "VALUES(#{password},#{name},#{nickName}, #{email}, #{phoneNumber},  #{point}, #{memberId},#{auth})")
    int signUp(Member member);

    @Select(
            "Select * from member where nick_name = #{id}"
    )
    @Results({
            @Result(property = "memberId", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "nickName", column = "nick_name"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "password", column = "password"),
            @Result(property = "point", column = "point"),
            @Result(property = "auth", column = "auth")
    })
    Member getMemberById(String id);
    @Select(
            "Select count(*) from member where nick_name = #{nickName}"
    )
    int checkNickName(String nickName);

    @Update("update member set " +
            "name = #{name}, " +
            "password = #{password}, " +
            "email = #{email}, " +
            "phone_number = #{phoneNumber} " +
            "where id = #{memberId}")
    int update(Member member);

    @Select("select count(*) from member where email = #{email}")
    int countEmail(@Param("email") String email);

    @Select("select count(*) from member where phone_number = #{phoneNumber}")
    int countPhoneNumber(@Param("email") String phoneNumber);
}

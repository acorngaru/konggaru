package com.acorngaru.konggaru.mapper;


import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import com.acorngaru.konggaru.model.Member;

@Mapper
@Repository
public interface MemberMapper {
	
	@Select("select id, name, email, phone_number, nick_name, auth, point from member where id = #{id}")
	//@Select("select * from member where id = #{id}")
	Member showMyPageById(int id);

	@Update("update member set password = #{password}, nick_name = #{nickName}, email = #{email}, phone_number = #{phoneNumber} where id = #{id}")
	int updateMyPage(Member member) throws Exception;
	
}

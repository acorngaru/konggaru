package com.acorngaru.konggaru.mapper;


import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.acorngaru.konggaru.model.Order;
import com.acorngaru.konggaru.model.OrderDetail;

@Mapper
@Repository
public interface OrderMapper {
	
	@Select("select * from orders where member_id = #{memberId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "memberId", column = "member_id"),
            @Result(property = "totalPrice", column = "total_price"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "pickupTime", column = "pickup_time"),
            @Result(property = "orderDetails", column = "id",
                    javaType = List.class,
                    many = @Many(select = "com.acorngaru.konggaru.mapper.OrderDetailMapper.findOrderDetailsByOrderId",
                                 fetchType = FetchType.EAGER))
    })
    List<Order> findOrdersByMemberId(@Param("memberId") int memberId);

}

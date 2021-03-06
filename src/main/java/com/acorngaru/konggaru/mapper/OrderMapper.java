package com.acorngaru.konggaru.mapper;


import com.acorngaru.konggaru.model.Order;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderMapper {

    @Select("select * from orders")
    List<Order> findAllOrders();
    @Select("select * from orders where member_id = #{memberId} order by id desc")
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
    @Insert("insert into orders (id, member_id, total_price, created_at, pickup_time) " +
            "values (#{id}, #{memberId}, #{totalPrice}, #{createdAt}, #{pickupTime})")
    @SelectKey(statement = "select orders_seq.currval from dual", resultType = int.class, keyProperty = "id", before = false)
    int insert(Order order);
}

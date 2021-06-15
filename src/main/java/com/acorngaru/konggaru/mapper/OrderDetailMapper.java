package com.acorngaru.konggaru.mapper;


import java.util.List;

import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import com.acorngaru.konggaru.model.Order;
import com.acorngaru.konggaru.model.OrderDetail;
import com.acorngaru.konggaru.model.Product;

@Mapper
@Repository
public interface OrderDetailMapper {
	
	@Select("select * from order_detail where order_id = #{orderId}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "productQuantity", column = "product_quantity"),
            @Result(property = "orderId", column = "order_id"),
            @Result(property = "productId", column = "product_id"),
            @Result(property = "product", column = "product_id",
                    javaType = Product.class,
                    one = @One(select = "com.acorngaru.konggaru.mapper.ProductMapper.findProductById",
                               fetchType = FetchType.EAGER))
    })
    List<OrderDetail> findOrderDetailsByOrderId(@Param("orderId") int orderId);

}

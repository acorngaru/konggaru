package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("OrderDetail")
public class OrderDetail {
    // 고유번호
    private int id;

    // 상품 수량
    private int productQuantity;

    // 주문 고유번호
    private int orderId;

    // 상품 고유번호
    private int productId;

    // 상품
    private Product product;
}

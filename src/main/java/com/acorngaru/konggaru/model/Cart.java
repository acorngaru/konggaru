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
@Alias("Cart")
public class Cart {
    // 고유번호
    private int id;

    // 상품 고유번호
    private int productId;

    // 멤버 고유번호
    private int memberId;

    // 상품 수량
    private int productQuantity;

    // 상품
    private Product product;

    // 주문 상세 객체로 변환
    private OrderDetail toOrderDetail() {
        return OrderDetail.builder()
                .productId(productId)
                .product(product)
                .productQuantity(productQuantity)
                .build();
    }

    // 주문 고유번호를 받아 주문 상세 객체로 변환
    private OrderDetail toOrderDetail(int orderId) {
        return OrderDetail.builder()
                .orderId(orderId)
                .productId(productId)
                .product(product)
                .productQuantity(productQuantity)
                .build();
    }
}

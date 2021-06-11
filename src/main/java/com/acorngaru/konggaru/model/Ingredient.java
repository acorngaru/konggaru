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
@Alias("Ingredient")
public class Ingredient {
    // 고유번호
    private int id;

    // 이름
    private String name;

    // 수량
    private int quantity;

    // 가격
    private int price;

    // 수량 단위
    private String unit;

    // 파트너
    private String partner;
}

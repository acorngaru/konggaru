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
@Alias("IngredinetOrder")
public class IngredinetOrder {
    // 고유번호
    private int ingredient_id;

    // 이름
    private String ingredient_name;

    // 수량
    private int ingredient_quantity;

    // 요청날짜
    private String created_at;

    // 승인날짜
    private String addmission_at;
}

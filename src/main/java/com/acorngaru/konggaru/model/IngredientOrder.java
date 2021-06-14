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
public class IngredientOrder {
    // 고유번호
    private int ingredientId;

    // 이름
    private String ingredientName;

    // 수량
    private int ingredientQuantity;

    // 요청날짜
    private String createdAt;

    // 승인날짜
    private String addmissionAt;
}

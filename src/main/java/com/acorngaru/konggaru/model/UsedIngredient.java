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
@Alias("UsedIngredient")
public class UsedIngredient {
    // 고유번호
    private int id;

    // 제품 고유번호
    private int productId;

    // 재료 고유번호
    private int ingredientId;

    // 수량
    private double usage;

    // 재료
    Ingredient ingredient;
}

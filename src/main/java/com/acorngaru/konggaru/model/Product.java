package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Product")
public class Product {
    // 고유번호
    private int id;

    // 이름
    private String name;

    // 설명
    private String description;

    // 가격
    private int price;

    // 카테고리 고유번호
    private int categoryId;

    // 카테고리 이름
    private String categoryName;

    // 이미지 S3 경로
    private String imageUrl;

    // 레시피
    List<UsedIngredient> recipe;
}

package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Alias("Product")
public class Product {

    // 고유번호
    @NotNull(message = "ID는 필수값입니다.")
    private int id;

    // 이름
    @NotBlank(message = "상품명은 필수값입니다.")
    private String name;

    // 설명
    private String description;

    // 가격
    @PositiveOrZero(message = "가격은 0보다 작을 수 없습니다.")
    private int price;

    // 카테고리 고유번호
    @NotNull(message = "카테고리는 필수값입니다.")
    private int categoryId;

    // 카테고리 이름
    private String categoryName;

    // 이미지 S3 경로
    private String imageUrl;

    // 레시피
    List<UsedIngredient> recipe;
}
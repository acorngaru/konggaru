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
@Alias("Category")
public class Category {
    // 고유번호
    private int id;

    // 이름
    private String name;
}

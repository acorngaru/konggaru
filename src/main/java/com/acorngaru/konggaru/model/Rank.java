package com.acorngaru.konggaru.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Alias("Rank")
public class Rank {
    private int quan;
    private String name;
    private String image_url;
}

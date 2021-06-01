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
@Alias("Rollbook")
public class Rollbook {
    //고유 번호
    private String Id;

    //이름
    private String Name;

    //출근 시간
    private String clockIn;

    //퇴근시간
    private String clockOut;

    //근무시간
    private String workTime;
}

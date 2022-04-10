package com.leonardo.animepoll.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ChartItemDTO {
    
    private Integer rank;
    private String title;
    private Float malScore;
    private Long malScoredBy;
    private Long votes;
    private String cover;
    private String japaneseTitle;

    private String trailer;
    private String url;

}

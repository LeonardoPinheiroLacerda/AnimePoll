package com.leonardo.animepoll.dtos;

import java.time.LocalDate;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class ChartItem {
    
    private Integer rank;
    private String title;
    private Float malScore;
    private Long malScoredBy;
    private Long votes;
    private String cover;
    private String japaneseTitle;

}

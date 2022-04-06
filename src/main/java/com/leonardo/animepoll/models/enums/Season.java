package com.leonardo.animepoll.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Season {
    
    SUMMER ("Summer"),
    SPRING ("Spring"),
    FALL   ("Fall"),
    WINTER ("Winter");

    private final String season;

}

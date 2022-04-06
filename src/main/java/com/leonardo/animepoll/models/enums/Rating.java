package com.leonardo.animepoll.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Rating {
    
    G       ("G - All Ages"),
    PG      ("PG - Children"),
    PG_13   ("PG-13 - Teens 13 or older"),
    R_17    ("R - 17+ (violence & profanity)"),
    R_PLUS  ("R+ - Mild Nudity"),
    RX      ("Rx - Hentai");

    private final String rating;

}

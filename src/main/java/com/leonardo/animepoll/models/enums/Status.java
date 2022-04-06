package com.leonardo.animepoll.models.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter

public enum Status {
    FINISHED_AIRING     ("Finished Airing"),
    CURRENTLY_AIRING    ("Currently Airing"),
    NOT_YET_AIRED       ("Not Yet Aired");

    private final String status;
}

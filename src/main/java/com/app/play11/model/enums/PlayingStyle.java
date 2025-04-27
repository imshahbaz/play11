package com.app.play11.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum PlayingStyle {

    BATSMAN("bat"),
    BOWLER("bowl");

    @Getter
    private final String style;
}

package ru.reidj.sagiridiscordbot.level;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RoleLevels {
    SECOND(2, "799726588169945198"),
    TENTH(10, "799726585414287401"),
    TWENTY_FIFTH(25, "799726582544859207"),
    FORTIETH(40, "799726579302662145"),
    FIFTY_FIFTH(55, "800726571932581888")
    ;


    private final int level;
    private final String role;
}

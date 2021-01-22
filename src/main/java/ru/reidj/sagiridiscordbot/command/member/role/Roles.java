package ru.reidj.sagiridiscordbot.command.member.role;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Roles {
    BROKEN_HEART("800646936154996776", "10", 10000),
    YOURS_DREAM("801440219705901057", "9", 15000),
    SOUL_IS_DEAD("800434858248896544", "8", 20000),
    AMATERASU("800436027629502554", "7", 20000),
    PEDOPHILE_DREAM("799728979564691526", "6", 30000),
    SHADOW_MOON("801442553449807943", "5", 40000),
    GHOUL("800439247211200562", "4", 45000),
    DEAD_INSIDE("799722708400472115", "3", 55000),
    STAR("799723327886589964", "2", 65000);

    private final String role;
    private final String id;
    private final int money;
}

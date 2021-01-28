package ru.reidj.sagiridiscordbot.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private int level;
    private int money;
    private int numberOfMessage;
}

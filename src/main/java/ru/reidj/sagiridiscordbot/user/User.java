package ru.reidj.sagiridiscordbot.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class User {
    private int level;
    private int money;
    private int numberOfMessage;
}

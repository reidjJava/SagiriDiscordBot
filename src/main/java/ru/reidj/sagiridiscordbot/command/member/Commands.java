package ru.reidj.sagiridiscordbot.command.member;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.File;

@AllArgsConstructor
@Getter
public enum Commands {
    ANGRY_COMMAND("!angry", " разозлился(-ась) на ", new File("src/main/resources/angry.gif"), true),
    CRY_COMMAND("!cry", " расплакался(-ась)", new File("src/main/resources/cry.gif"), false),
    SHYNESS_COMMAND("!shyness", " засмущался(-ась)", new File("src/main/resources/didder.gif"), false),
    FACE_PAWN_COMMAND("!facepawn", "разочаровался(-ась)", new File("src/main/resources/facepawn.gif"), false),
    GOODBYE_COMMAND("!goodbye", "  попрощался(-ась) с ", new File("src/main/resources/goodbye.gif"),true),
    HELLO_COMMAND("!hello", " поздоровался(-ась) с ", new File("src/main/resources/hello.gif"), true),
    HIGH_FIVE_COMMAND("!highfive", " дал(-а) пять ", new File("src/main/resources/highfive.gif"),true),
    HIT_COMMAND("!hit", " ударил(-а) ", new File("src/main/resources/hit.gif"), true),
    HUG_COMMAND("!hug", " обнял(-а) ", new File("src/main/resources/hug.gif"), true),
    KISS_COMMAND("!kiss", " поцеловал(-а) ", new File("src/main/resources/kiss.gif"), true),
    LAUGH_COMMAND("!laugh", " посмеялся(-ась)", new File("src/main/resources/laugh.gif"),false),
    PAT_COMMAND("!pat", " погладил(-а) ", new File("src/main/resources/pat.gif"),true),
    SLEEP_COMMAND("!sleep", " захотел(-а) спать", new File("src/main/resources/sleep.gif"), false),
    SORRY_COMMAND("!sorry", " извинился(-ась) перед ", new File("src/main/resources/sorry.gif"), true),
    THREATEN_COMMAND("!threaten", " пригрозил(-а) ", new File(""), true),
    WOW_COMMAND("!wow", " удивился(-ась)", new File("src/main/resources/wow.gif"), false)
    ;

    private final String command;
    private final String message;
    private final File path;
    private final boolean isMention;
}

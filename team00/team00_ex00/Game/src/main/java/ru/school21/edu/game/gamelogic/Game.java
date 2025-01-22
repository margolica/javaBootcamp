package ru.school21.edu.game.gamelogic;

import com.beust.jcommander.JCommander;
import ru.school21.edu.game.exception.IllegalParametersException;

import static java.lang.System.*;
import static java.lang.System.exit;

public class Game {
    public static void main(String[] args) {
        try {
            Args argsParser = new Args();
            parserArgs(args, argsParser);
            Menu menu = new Menu(argsParser.getSizeFiled(), argsParser.getEnemiesCount(), argsParser.getWallsCount(), argsParser.getProfile());
            menu.start();
        } catch (Exception e) {
            out.println(e.getMessage());
            exit(1);
        }
    }

    private static void parserArgs(String[] args, Args argsParser) {
        try {
            JCommander.newBuilder().addObject(argsParser).build().parse(args);
        } catch (Exception e) {
            throw new IllegalParametersException(e.getMessage());
        }
    }
}

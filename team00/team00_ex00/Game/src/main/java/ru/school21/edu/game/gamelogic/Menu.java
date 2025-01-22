package ru.school21.edu.game.gamelogic;
import ru.school21.edu.chaselogic.gamefield.FieldService;
import ru.school21.edu.chaselogic.propertyparser.PropertyFileParser;
import ru.school21.edu.chaselogic.statusgame.StatusGame;
import ru.school21.edu.game.exception.IllegalParametersException;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class Menu {
    Logger logger = Logger.getLogger(getClass().getName());
    private static final String PROPERTIES_FILE = "resources/application-production.properties";
    private static final String DEV_PROPERTIES_FILE = "resources/application-dev.properties";
    private final FieldService fieldService;
    private final StatusGame statusGame = new StatusGame();
    private final boolean profileDevelop;
    Scanner in = new Scanner(System.in);

    public Menu(int sizeFiled, int enemiesCount, int wallsCount, String profile) throws IOException {
        this.profileDevelop = profile.equals("develop");
        String propertyFile = this.profileDevelop ? DEV_PROPERTIES_FILE : PROPERTIES_FILE;
        PropertyFileParser propertyFileParser = new PropertyFileParser(propertyFile);
        try {
            fieldService = new FieldService(sizeFiled, enemiesCount, wallsCount, profileDevelop, statusGame, in, propertyFileParser);
        } catch (RuntimeException e) {
            throw new IllegalParametersException("Wrong game parameters! Check your parameters or use this one: --enemiesCount=3 --wallsCount=4 --size=10 --profile=production");
        }
    }
    public void start() {
        startGame();
        printLastMassage(statusGame);
        in.close();
    }
    private void printLastMassage(StatusGame statusGame) {
        if (statusGame.isWin())
            logger.info("Вы выиграли!");
        if (statusGame.isLose())
            logger.info("Вы проиграли..");
        if (statusGame.isDeadlock()) {
            logger.info("Вы завершили игру");
        }
    }
    public void startGame() {
        while (statusGame.isGo()) {
            clearConsole();
            fieldService.draw();
            fieldService.startMovePlayer(in);
            if (statusGame.isGo()) {
                clearConsole();
                fieldService.draw();
                timeShift();
                fieldService.startMoveEnemy();
            }

        }
    }
    private void timeShift() {
        try {
            TimeUnit.MILLISECONDS.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void clearConsole() {
        if (!profileDevelop) {
            try {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                } else {
                    new ProcessBuilder("/bin/bash", "-c", "clear").inheritIO().start().waitFor();
                }
            } catch (IOException | InterruptedException ex) {
                logger.warning("Console cleaning doesn't work! Please delete the clearConsole method..");
                Thread.currentThread().interrupt();
            }
        }
    }
}

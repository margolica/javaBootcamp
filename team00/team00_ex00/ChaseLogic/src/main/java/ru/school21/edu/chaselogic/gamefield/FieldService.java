package ru.school21.edu.chaselogic.gamefield;

import ru.school21.edu.chaselogic.characters.Enemy;
import ru.school21.edu.chaselogic.statusgame.StatusGame;
import ru.school21.edu.chaselogic.propertyparser.PropertyFileParser;

import java.util.Scanner;

public class FieldService {
    private Field field;
    private StatusGame statusGame;
    private boolean profileDevelop;
    Scanner in;


    public FieldService(int sizeFiled, int enemiesCount, int wallsCount,
                        boolean profileDevelop, StatusGame statusGame, Scanner in, PropertyFileParser propertyFileParser) {
        field = new Field(sizeFiled, enemiesCount, wallsCount, propertyFileParser);
        this.statusGame = statusGame;
        this.profileDevelop = profileDevelop;
        this.in = in;
    }

    public void draw() {
        field.drawField();
    }

    public void startMovePlayer(Scanner in) {
        String charIO = in.nextLine();
        if (!charIO.isEmpty()) {
            statusGame.checkDeadlock(charIO.charAt(0));
            if (statusGame.isGo() && field.checkMovePlayer(field.getPlayer().getDy(), field.getPlayer().getDx(), charIO)) {
                field.getCellWithPlayer().getPlayer().move(charIO.charAt(0));
                statusGame.checkWin(field.getPlayer(), field.getGoal());
                statusGame.checkLose(field.getPlayer(), field.getCellsWithEnemy());
                if (statusGame.isGo()) {
                    field.updateUserPosition();
                }
            }
        }
    }

    public void startMoveEnemy() {
        field.nextCoordinateEnemy();
        developPartEnemy();
        statusGame.checkLose(field.getPlayer(), field.getCellsWithEnemy());
    }

    private void developPartEnemy() {
        if (this.profileDevelop) {
            int count = 1;
            for (Cell i : field.getCellsWithEnemy()) {

                System.out.println("Нажмите кнопку 8 для подтверждения хода врага");
                printCoordinate(i.getEnemy(), "Новые координаты");
                while (true)
                    if (in.nextLine().equals("8"))
                        break;
            }
        }
    }

    private static void printCoordinate(Enemy enemy, String moment) {
        System.out.println(moment + " Y = " + enemy.getDy() + " X = " + enemy.getDx());
    }


}

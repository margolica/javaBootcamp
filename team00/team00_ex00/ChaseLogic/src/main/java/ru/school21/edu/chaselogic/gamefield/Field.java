package ru.school21.edu.chaselogic.gamefield;

import ru.school21.edu.chaselogic.characters.Enemy;
import ru.school21.edu.chaselogic.characters.Player;
import ru.school21.edu.chaselogic.propertyparser.PropertyFileParser;

import java.util.ArrayList;
import java.util.Collections;

import static com.diogonunes.jcolor.Ansi.colorize;
import static java.lang.System.*;

public class Field {
    private final Cell[][] defaultField;
    private final int sizeFiled;
    private final int enemiesCount;
    private final int wallsCount;
    private Cell goal;
    private Cell cellWithPlayer;
    private Cell[] cellsWithEnemy;
    private Cell[] cellsWithWall;
    private final PropertyFileParser propertyFileParser;

    public Field(int sizeFiled, int enemiesCount, int wallsCount, PropertyFileParser propertyFileParser) {
        defaultField = new Cell[sizeFiled][sizeFiled];
        this.sizeFiled = sizeFiled;
        this.enemiesCount = enemiesCount;
        this.wallsCount = wallsCount;
        cellsWithEnemy = new Cell[enemiesCount];
        cellsWithWall = new Cell[wallsCount];
        this.propertyFileParser = propertyFileParser;
        parameterizationField();
    }

    public void updateUserPosition() {
        Cell bufferCell = cellWithPlayer;
        Cell cell = defaultField[cellWithPlayer.getPlayer().getDy()][cellWithPlayer.getPlayer().getDx()];

        cell.setPlayer(cellWithPlayer.getPlayer());
        cell.setSymbolDisplay(propertyFileParser.getPlayerChar());
        cell.setColor(propertyFileParser.getPlayerColor());

        cellWithPlayer = defaultField[cellWithPlayer.getPlayer().getDy()][cellWithPlayer.getPlayer().getDx()];
        bufferCell.setPlayer(null);
        bufferCell.setSymbolDisplay(propertyFileParser.getEmptyChar());
        bufferCell.setColor(propertyFileParser.getEmptyColor());
    }

    public void updateEnemyPosition(Cell i, int count) {
        if (!(i.getEnemy().getDy() == i.getDy() && i.getEnemy().getDx() == i.getDx())) {
            Cell nextCell = defaultField[i.getEnemy().getDy()][i.getEnemy().getDx()];

            nextCell.setSymbolDisplay(propertyFileParser.getEnemyChar());
            nextCell.setEnemy(i.getEnemy());
            nextCell.setColor(propertyFileParser.getEnemyColor());

            cellsWithEnemy[count] = nextCell;

            i.setSymbolDisplay(propertyFileParser.getEmptyChar());
            i.setEnemy(null);
            i.setColor(propertyFileParser.getEmptyColor());
        }
    }

    public Cell getCellWithPlayer() {
        return cellWithPlayer;
    }

    public Cell[] getCellsWithEnemy() {
        return cellsWithEnemy;
    }

    public Cell getGoal() {
        return goal;
    }

    public Player getPlayer() {
        return cellWithPlayer.getPlayer();
    }

    private void parameterizationField() {
        ArrayList<int[]> objects = new ArrayList<>();
        for (int y = 0; y < sizeFiled; ++y) {
            for (int x = 0; x < sizeFiled; ++x) {
                int[] value = new int[]{y, x};
                objects.add(value);
            }
        }
        Collections.shuffle(objects);
        drawWalls(objects);
        drawEnemies(objects);
        drawPlayer(objects);
        drawTarget(objects);
        drawEmptyCells();
    }

    private void drawWalls(ArrayList<int[]> objects) {
        for (int i = 0; i < wallsCount; ++i) {
            int[] coordinate = objects.get(0);
            defaultField[coordinate[0]][coordinate[1]] = new Cell.Builder(coordinate[0], coordinate[1])
                    .setWallSymbol(propertyFileParser.getWallChar())
                    .setSymbol(propertyFileParser.getWallChar())
                    .setColor(propertyFileParser.getWallColor())
                    .build();
            cellsWithWall[i] = defaultField[coordinate[0]][coordinate[1]];
            objects.remove(0);
        }
    }

    private void drawEnemies(ArrayList<int[]> objects) {
        for (int i = 0; i < enemiesCount; ++i) {
            int[] coordinate = objects.get(0);
            defaultField[coordinate[0]][coordinate[1]] = new Cell.Builder(coordinate[0], coordinate[1])
                    .setSymbol(propertyFileParser.getEnemyChar())
                    .setEnemy(new Enemy(coordinate[0], coordinate[1]))
                    .setColor(propertyFileParser.getEnemyColor())
                    .build();
            cellsWithEnemy[i] = defaultField[coordinate[0]][coordinate[1]];
            objects.remove(0);
        }
    }

    private void drawPlayer(ArrayList<int[]> objects) {
        int[] coordinate = objects.get(0);
        defaultField[coordinate[0]][coordinate[1]] = new Cell.Builder(coordinate[0], coordinate[1])
                .setSymbol(propertyFileParser.getPlayerChar())
                .setPlayer(new Player(coordinate[0], coordinate[1]))
                .setColor(propertyFileParser.getPlayerColor())
                .build();
        cellWithPlayer = defaultField[coordinate[0]][coordinate[1]];
        objects.remove(0);
    }

    private void drawTarget(ArrayList<int[]> objects) {
        int[] coordinate = objects.get(0);
        defaultField[coordinate[0]][coordinate[1]] = new Cell.Builder(coordinate[0], coordinate[1])
                .setGoalSymbol(propertyFileParser.getGoalChar())
                .setSymbol(propertyFileParser.getGoalChar())
                .setColor(propertyFileParser.getGoalColor())
                .build();
        goal = defaultField[coordinate[0]][coordinate[1]];
        objects.remove(0);
    }

    private void drawEmptyCells() {
        for (int y = 0; y < sizeFiled; ++y) {
            for (int x = 0; x < sizeFiled; ++x) {
                if (defaultField[y][x] == null) {
                    defaultField[y][x] = new Cell.Builder(y, x)
                            .setSymbol(propertyFileParser.getEmptyChar())
                            .setColor(propertyFileParser.getEmptyColor())
                            .build();
                }
            }
        }
        for (int y = 0; y < sizeFiled; y++) {
            for (int x = 0; x < sizeFiled; x++) {
                if (!defaultField[y][x].isWall()) {
                    calculationLogicalValue(y, x);
                } else {
                    defaultField[y][x].setLogicalValue(0);
                }
            }
        }
        goal.setLogicalValue(-1);
    }


    public void drawField() {
        for (int y = 0; y < sizeFiled; y++) {
            for (int x = 0; x < sizeFiled; x++) {
                out.print(colorize(String.format(" %s ", defaultField[y][x].getSymbolDisplay()), defaultField[y][x].getColor()));
            }
            out.println();
        }
        out.println();
    }

    private void calculationLogicalValue(int y, int x) {
        if (x == 0)
            defaultField[y][x].addLogicalValue(1);
        if (y == 0)
            defaultField[y][x].addLogicalValue(2);
        if (x == sizeFiled - 1)
            defaultField[y][x].addLogicalValue(4);
        if (y == sizeFiled - 1)
            defaultField[y][x].addLogicalValue(8);
        defaultField[y][x].addLogicalValue(searchWall(y, x));
    }

    private int searchWall(int y, int x) {
        int result = 0;
        result += checkLeftWall(y, x);
        result += checkTopWall(y, x);
        result += checkRightWall(y, x);
        result += checkBottomWall(y, x);

        return result;
    }

    private int checkLeftWall(int y, int x) {
        if (x > 0 && defaultField[y][x - 1].isWall()) {
            return 1;
        }
        return 0;
    }

    private int checkTopWall(int y, int x) {
        if (y > 0 && defaultField[y - 1][x].isWall()) {
            return 2;
        }
        return 0;
    }

    private int checkRightWall(int y, int x) {
        if (x < sizeFiled - 1 && defaultField[y][x + 1].isWall()) {
            return 4;
        }
        return 0;
    }

    private int checkBottomWall(int y, int x) {
        if (y < sizeFiled - 1 && defaultField[y + 1][x].isWall()) {
            return 8;
        }
        return 0;
    }


    public boolean checkMoveEnemy(int dy, int dx, String charIO) {
        if (charIO.equals("w") && canMoveUp(defaultField[dy][dx].getLogicalValue()) && defaultField[dy - 1][dx].getEnemy() == null)
            return true;
        else if (charIO.equals("s") && canMoveDown(defaultField[dy][dx].getLogicalValue()) && defaultField[dy + 1][dx].getEnemy() == null)
            return true;
        else if (charIO.equals("d") && canMoveRight(defaultField[dy][dx].getLogicalValue()) && defaultField[dy][dx + 1].getEnemy() == null)
            return true;
        else if (charIO.equals("a") && canMoveLeft(defaultField[dy][dx].getLogicalValue()) && defaultField[dy][dx - 1].getEnemy() == null)
            return true;
        else if (charIO.contains("wsda"))
            out.println("Враг не может сходить ");
        return false;
    }

    public boolean checkMovePlayer(int dy, int dx, String charIO) {
        if (charIO.equals("w") && (isGoal(dy - 1, dx) || canMoveUp(defaultField[dy][dx].getLogicalValue())))
            return true;
        else if (charIO.equals("s") && (isGoal(dy + 1, dx) || canMoveDown(defaultField[dy][dx].getLogicalValue())))
            return true;
        else if (charIO.equals("d") && (isGoal(dy, dx + 1) || canMoveRight(defaultField[dy][dx].getLogicalValue())))
            return true;
        else if (charIO.equals("a") && (isGoal(dy, dx - 1) || canMoveLeft(defaultField[dy][dx].getLogicalValue())))
            return true;
        else {
            try {
                throw new IllegalArgumentException("Введен неверный аргумент для перемещения.");
            } catch (IllegalArgumentException e) {
                out.println(e + "Введите новый аргумент для продолжения.");
            }
            return false;
        }
    }

    private static boolean canMoveUp(int logicValue) {
        return logicValue == 16 || logicValue == 17 || logicValue == 20 || logicValue == 24
                || logicValue == 21 || logicValue == 25 || logicValue == 28 || logicValue == 29;
    }

    private static boolean canMoveDown(int logicValue) {
        return logicValue == 16 || logicValue == 17 || logicValue == 18 || logicValue == 20
                || logicValue == 19 || logicValue == 21 || logicValue == 22 || logicValue == 23;
    }

    private static boolean canMoveRight(int logicValue) {
        return logicValue == 16 || logicValue == 17 || logicValue == 18 || logicValue == 24
                || logicValue == 19 || logicValue == 25 || logicValue == 26 || logicValue == 27;
    }

    private static boolean canMoveLeft(int logicValue) {
        return logicValue == 16 || logicValue == 18 || logicValue == 20 || logicValue == 24
                || logicValue == 22 || logicValue == 26 || logicValue == 28 || logicValue == 30;
    }

    private boolean isGoal(int dy, int dx) {
        return dy <= sizeFiled - 1 && dy >= 0 && dx <= sizeFiled - 1 && dx >= 0 && defaultField[dy][dx] == goal;
    }

    public void nextCoordinateEnemy() {
        int count = 0;
        for (Cell enemyCell : cellsWithEnemy) {
            moveEnemyTowardsPlayer(enemyCell);
            updateEnemyPosition(enemyCell, count);
            count++;
        }
    }

    private void moveEnemyTowardsPlayer(Cell enemyCell) {
        int deltaY = getPlayer().getDy() - enemyCell.getEnemy().getDy();
        int deltaX = getPlayer().getDx() - enemyCell.getEnemy().getDx();

        if (Math.abs(deltaX) >= Math.abs(deltaY)) {
            moveEnemyHorizontally(enemyCell, deltaX);
        } else {
            moveEnemyVertically(enemyCell, deltaY);
        }
    }

    private void moveEnemyHorizontally(Cell enemyCell, int deltaX) {
        if (deltaX > 0 && checkMoveEnemy(enemyCell.getDy(), enemyCell.getDx(), "d")) {
            enemyCell.getEnemy().setDx(enemyCell.getEnemy().getDx() + 1);
        } else if (deltaX < 0 && checkMoveEnemy(enemyCell.getDy(), enemyCell.getDx(), "a")) {
            enemyCell.getEnemy().setDx(enemyCell.getEnemy().getDx() - 1);
        }
    }

    private void moveEnemyVertically(Cell enemyCell, int deltaY) {
        if (deltaY > 0 && checkMoveEnemy(enemyCell.getDy(), enemyCell.getDx(), "s")) {
            enemyCell.getEnemy().setDy(enemyCell.getEnemy().getDy() + 1);
        } else if (deltaY < 0 && checkMoveEnemy(enemyCell.getDy(), enemyCell.getDx(), "w")) {
            enemyCell.getEnemy().setDy(enemyCell.getEnemy().getDy() - 1);
        }
    }


}


package ru.school21.edu.chaselogic.gamefield;

import com.diogonunes.jcolor.Attribute;
import ru.school21.edu.chaselogic.characters.Enemy;
import ru.school21.edu.chaselogic.characters.Player;

import java.util.Objects;

public class Cell {
    private Player player;
    private Enemy enemy;
    private final char wallSymbol;
    private final char goalSymbol;
    private char symbolDisplay;
    private Attribute color;
    private int logicalValue = 16;
    private final int dx;
    private final int dy;

    public static class Builder {
        private char wallSymbol;
        private char goalSymbol;
        private char symbolDisplay = ' ';
        private Player player = null;
        private Enemy enemy = null;
        private final int dY;
        private final int dX;
        private Attribute color = Attribute.YELLOW_BACK();
        public Builder(int dY, int dX) {
            this.dY = dY;
            this.dX = dX;
        }

        public Builder setSymbol(char symbolDisplay) {
            this.symbolDisplay = symbolDisplay;
            return this;
        }

        public Builder setPlayer(Player player) {
            this.player = player;
            return this;
        }

        public Builder setEnemy(Enemy enemy) {
            this.enemy = enemy;
            return this;
        }

        public Builder setColor(Attribute color) {
            this.color = color;
            return this;
        }

        public Cell build() {
            return new Cell(this);
        }

        public Builder setWallSymbol(char wallSymbol) {
            this.wallSymbol = wallSymbol;
            return this;
        }

        public Builder setGoalSymbol(char goalSymbol) {
            this.goalSymbol = goalSymbol;
            return this;
        }

    }
    private Cell(Builder builder) {
        this.symbolDisplay = builder.symbolDisplay;
        this.player = builder.player;
        this.enemy = builder.enemy;
        this.dx = builder.dX;
        this.dy = builder.dY;
        this.color = builder.color;
        this.wallSymbol = builder.wallSymbol;
        this.goalSymbol = builder.goalSymbol;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Enemy getEnemy() {
        return enemy;
    }

    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    public char getSymbolDisplay() {
        return symbolDisplay;
    }

    public void setSymbolDisplay(char symbolDisplay) {
        this.symbolDisplay = symbolDisplay;
    }

    public Attribute getColor() {
        return color;
    }

    public void setColor(Attribute color) {
        this.color = color;
    }

    public int getLogicalValue() {
        return logicalValue;
    }

    public void setLogicalValue(int logicalValue) {
        this.logicalValue = logicalValue;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public boolean isWall() {
        return Objects.equals(symbolDisplay, wallSymbol) || Objects.equals(symbolDisplay, goalSymbol);
    }

    public void addLogicalValue(int value) {
        logicalValue += value;
    }

}

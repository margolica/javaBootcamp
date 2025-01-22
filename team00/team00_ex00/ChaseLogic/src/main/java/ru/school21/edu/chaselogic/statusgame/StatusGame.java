package ru.school21.edu.chaselogic.statusgame;

import ru.school21.edu.chaselogic.characters.Player;
import ru.school21.edu.chaselogic.gamefield.Cell;

public class StatusGame {

    private Status status = Status.GO;

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        WIN,
        LOSE,
        DEADLOCK,
        GO
    }

    public boolean isWin() {
        return status == Status.WIN;
    }

    public boolean isLose() {
        return status == Status.LOSE;
    }

    public boolean isDeadlock() {
        return status == Status.DEADLOCK;
    }

    public boolean isGo() {
        return status == Status.GO;
    }

    public void checkWin(Player player, Cell goal) {
        if (player.getDy() == goal.getDy() && player.getDx() == goal.getDx())
            setStatus(Status.WIN);
    }

    public void checkLose(Player player, Cell[] cellsWithEnemy) {
        for (Cell i : cellsWithEnemy)
            if (i.getEnemy().getDx() == player.getDx() && i.getEnemy().getDy() == player.getDy())
                setStatus(Status.LOSE);
    }

    public void checkDeadlock(char charIO) {
        if (charIO == '9')
            setStatus(Status.DEADLOCK);
    }
}

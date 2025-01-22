package ru.school21.edu.chaselogic.characters;

public class Characters {
    protected int vectorMoveX = 0;
    protected int vectorMoveY = 0;
    protected int dx;
    protected int dy;

    public Characters(int dyIO, int dxIO) {
        dy = dyIO;
        dx = dxIO;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public void move(char symbolMovement) {
        setVectorMove(symbolMovement);
        moveCoordinate();
    }

    private void setVectorMove(char symbolMovement) {
        vectorMoveX = 0;
        vectorMoveY = 0;

        if (symbolMovement == 'w')
            vectorMoveY = -1;
        else if (symbolMovement == 's')
            vectorMoveY = 1;
        else if (symbolMovement == 'd')
            vectorMoveX = 1;
        else if (symbolMovement == 'a')
            vectorMoveX = -1;
    }

    protected void moveCoordinate() {
        dx += vectorMoveX;
        dy += vectorMoveY;
    }
}

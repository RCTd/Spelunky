package GamePakage.Tiles;

import GamePakage.GameEntity;

import java.awt.*;

public class Rope implements GameEntity {
    private int x,y;

    public Rope(int x,int y)
    {
        this.x=x;
        this.y=y;
    }


    @Override
    public void Update() {

    }

    @Override
    public void Draw(Graphics g) {
        g.drawRect(x,y,16,16);
    }
}

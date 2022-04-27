package GamePakage.Entitys;

import GamePakage.Entitys.GameEntity;

import java.awt.*;

public class Whip implements GameEntity {
    public int x;
    public int y;

    public Whip(int x,int y)
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

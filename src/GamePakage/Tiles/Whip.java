package GamePakage.Tiles;

import GamePakage.GameEntity;

import java.awt.*;

import static GamePakage.Game.size;

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
        g.drawRect(x*size,y*size,16*size,16*size);
    }
}

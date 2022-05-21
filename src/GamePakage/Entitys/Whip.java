package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.Tiles.PlayerToolsTiles.WhipTile;

import java.awt.*;

public class Whip implements GameEntity{
    private float x;
    private float y;
    private final Game game;
    public WhipTile whipTile=new WhipTile(0);

    public Whip(float x, float y, Game game) {
        this.x = x;
        this.y = y;
        this.game = game;
    }
    @Override
    public void Update() {
        whipTile.direction=game.getPlayer().getDirection();
        if(whipTile.state==0)
            x=game.getPlayer().getX()- whipTile.direction*16;
        else
            x=game.getPlayer().getX()+ whipTile.direction*16;
        y=game.getPlayer().getY();
    }

    @Override
    public void Draw(Graphics g) {
        whipTile.Draw(g,(int)x,(int)y);
    }

    @Override
    public void Collide() {    }

    @Override
    public int getX() {
        return (int)x;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}

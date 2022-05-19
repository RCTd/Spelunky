package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.Tiles.SnakeTile;

import java.awt.*;

import static GamePakage.Game.YAccel;

public class Snake implements GameEntity{
    private float x, y,oldX,oldY,yVel=0;
    private int direction=1;
    private final Flags triflag=new Flags();
    private final Game game;
    private final SnakeTile snakeTile=new SnakeTile(0);

    public Snake( float x, float y,Game game){
        this.game=game;
        this.x=x;
        this.y=y;
        oldX=x;
        oldY=y;
    }

    @Override
    public void Update() {
        oldX=x;
        oldY=y;
        float snakespeed=80;
        double deltaTime=game.getDeltaTime();
        x+=direction*snakespeed*game.getDeltaTime();

        if(!triflag.IsOnGround) {
            y += (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel += YAccel * deltaTime;
        }else
            yVel=0;
    }

    @Override
    public void Draw(Graphics g) {
        snakeTile.Draw(g,(int)x,(int)y);
    }

    @Override
    public void Collide() {
        triflag.Collide((int)x,(int)y,16,16,game);
        if(triflag.wallLeft||triflag.wallRight||(triflag.OnEdgeLeft^triflag.OnEdgeRight)){
            x += oldX-x;
            direction=direction*-1;
            snakeTile.setDirection(direction);
        }
    }
}

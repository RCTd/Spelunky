package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.Tiles.SnakeTile;

import java.awt.*;

import static GamePakage.Game.YAccel;

public class Snake implements GameEntity{
    private float x, y,oldX,yVel=0;
    private int direction=1;
    private final Flags triflag=new Flags();
    private final Game game;
    private final SnakeTile snakeTile=new SnakeTile(0);

    public Snake( float x, float y,Game game){
        this.game=game;
        this.x=x;
        this.y=y;
        oldX=x;
    }

    @Override
    public void Update() {
        oldX=x;
        float snakespeed=80;
        double deltaTime=game.getDeltaTime();
        x+=direction*snakespeed*game.getDeltaTime();

        if(!triflag.IsOnGround) {
            y += (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel += YAccel * deltaTime;
        }else
            yVel=0;
        if(game.player.getX()>x-13 && game.player.getX()<x+13 && game.player.getY()<y+16){
            if(game.player.getY()>y-13 )
                game.player.TakeDamage(1);
            else
            if(game.player.getY()>y-16) {
                game.removeList.add(this);
                game.player.Jump();
            }
        }
    }

    @Override
    public void Draw(Graphics g) {
        snakeTile.Draw(g,(int)x,(int)y);
    }

    @Override
    public void Collide() {
        triflag.Collide((int)x,(int)y, 16, 16, 0,2, game);
        if(triflag.wallLeft||triflag.wallRight||(triflag.OnEdgeLeft^triflag.OnEdgeRight)){
            x += oldX-x;
            direction=direction*-1;
            snakeTile.setDirection(direction);
        }
    }

    @Override
    public int getX() {
        return (int)x;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}

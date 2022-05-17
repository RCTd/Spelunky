package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.GameTimer;
import GamePakage.Tiles.PlayerToolsTiles.BombTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.abs;
import static java.lang.Math.signum;

public class Bomb implements GameEntity {
    private final BombTile tile;
    private float xVel;
    private float yVel;
    private float x;
    private float y;
    private final float p0;
    private final Game game;
    private final Flags trigFlags=new Flags();
    private final long StartMoment;


    public Bomb(float x, float y, float xVel, float yVel, Game game)
    {
        tile=new BombTile(0);
        this.x=x;
        this.y=y;
        p0=y;
        this.xVel=xVel;
        this.yVel=yVel;
        //this.yVel += -100 / 0.1F;
        this.game=game;
        StartMoment=System.nanoTime();
        trigFlags.LongJump=true;
        trigFlags.IsOnGround=false;
    }

    @Override
    public void Update() {
        if(System.nanoTime()-StartMoment>1_000_000_000)
        {
            game.addList.add(new Explosion((int)x,(int)y,game));
            game.removeList.add(this);
        }
        float deltaTime=GameTimer.getInstance().getDeltaTime();
        MoveLogic(deltaTime);
        FloatLogic(deltaTime);
    }

    private void MoveLogic(float deltaTime)
    {
        if((xVel >0&&trigFlags.wallRight)||(xVel <0&&trigFlags.wallLeft)) {
            xVel = abs(xVel)<1F?0:-xVel*0.66F;
        }
        x+=xVel*deltaTime;
        xVel -= signum(xVel) * XAccel * deltaTime;
    }

    private void FloatLogic(float deltaTime)
    {
        if(!trigFlags.IsOnGround)
        {
            if(trigFlags.HeadHit && trigFlags.LongJump)
            {
                yVel =-(p0-y)/(0.1F);
                yVel += YAccel *(((float)System.nanoTime()- StartMoment)/1_000_000_000);
                trigFlags.LongJump =false;
                trigFlags.HeadHit =false;
            }
            float yAccel = 100 / (0.1F * 0.1F);
            y+= (yAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel+= yAccel *deltaTime;
        }else
        {
            if(abs(yVel)>=200)
            {
                yVel = -yVel / 2;
                y -= 1;
                trigFlags.IsOnGround = false;
            }else
                yVel=0;
        }
    }

    @Override
    public void Draw(Graphics g) {
        tile.Draw(g,(int)x, (int)y);
    }

    @Override
    public void Collide() {
        trigFlags.Collide((int)x,(int)y,8,8,game);
    }
}

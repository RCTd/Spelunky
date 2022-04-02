package GamePakage;

import GamePakage.Tiles.Tile;
import GamePakage.Tiles.PlayerTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.*;

public class Player {

    //private static float XDccel=-MaxXSpeed/AccelTimeX;
    public Tile PlayerTile = new PlayerTile(0);
    public boolean IsOnGround=true;
    private boolean LongJump;
    private float vx=0;
    private float yVel;
    private float y,p0;
    private float x;
    private long JumpStart,Time=0;

    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int) (y);
    }

    public Player()
    {
        x=32;
        y=67;
    }
   /* private boolean IsOnGround()
    {
        return IsOnGround;
    }*/
    public void Update(boolean[] flag)
    {
        float deltaTime=((float) (System.nanoTime() - Time) / 1_000_000_000);
        MoveLogic(flag,deltaTime);
        if (flag[0]&&IsOnGround) {
            p0=y;
            yVel = -MaxJumpHeight *2/ TimeToMaxH;
            LongJump =true;
            IsOnGround=false;
            JumpStart =System.nanoTime();
        }
        if (flag[2]) {
            y+=0.3;
        }

        JumpLogic(flag,deltaTime);
        if(x<0)
            x=0;
        if(x>(Game.WIDTH() - PlayerTile.TILE_WIDTH))
            x=(Game.WIDTH()- PlayerTile.TILE_WIDTH);
        Time=System.nanoTime();
        System.out.println("y= "+y);
        if(y>BottomLine)
            y=BottomLine;
    }

    public void Draw(Graphics g)
    {
        PlayerTile.Draw(g,getX(), getY());
    }

    private void JumpLogic(boolean[] flag,float deltaTime)
    {
        if (!IsOnGround) {
            if(y<0)
                y=0;
            //float deltaTime = (float) (System.nanoTime() - Time) / 1_000_000_000;
            if(!flag[0]&& LongJump)
            {
                yVel =-2*(p0-y)/(TimeToMaxH);
                yVel += YAccel *(((float)System.nanoTime()- JumpStart)/1_000_000_000);
                LongJump =false;
            }
            y+= (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel += YAccel * deltaTime;
        } else {
            y=Math.round(y);
            yVel=0;
        }
        /*IsOnGround();*/
    }

    private void MoveLogic(boolean[] flag,float deltaTime)
    {
        int result = 0;
        if(flag[1])
            result +=1;
        if(flag[3])
            result -=1;

        if(result ==0) {
            if (vx != 0)
                vx -= signum(vx) * XAccel * deltaTime;
        }else
            vx+= result *XAccel*deltaTime;
        if(vx>MaxXSpeed||vx<-MaxXSpeed)
            vx= result *MaxXSpeed;
        if(flag[4]) {//Slow down on shift
            MaxXSpeed=100F;
        }else {
            MaxXSpeed=200F;
        }
        XAccel=MaxXSpeed/AccelTimeX;
        //XDccel=-MaxXSpeed/AccelTimeX;
        x += vx*deltaTime;
    }
}
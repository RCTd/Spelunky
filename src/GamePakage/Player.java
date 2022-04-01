package GamePakage;

import GamePakage.Tiles.Tile;
import GamePakage.Tiles.PlayerTile;

import java.awt.*;

public class Player {
    private static final int MaxJumpHeight =100;
    private static final float TimeToMaxH = 0.4F;
    public Tile PlayerTile = new PlayerTile(0);
    private int x;
    private boolean LongJump;
    private float vx, yVel, DeltaTime, GAccel,y;
    private long Time0, JumpStart;

    public int getX() {
        return x/100;
    }
    public int getY() {
        return (int) (Game.HEIGHT()-60-y);
    }

    public Player()
    {
        x=100;
        y=0;
    }
    private boolean IsOnGround()
    {
        return y<=0;
    }
    public void Update(boolean[] flag)
    {
        //System.out.println(x+" "+y);
        vx = 0;
        //vy = 0;
        if (flag[1]) {
            if(flag[3])
                vx=0;
            else
                vx = 4;
        } else if (flag[3]) {
            vx = -4;
        }
        if (flag[0]&&IsOnGround()) {
            LongJump =true;
            GAccel =-MaxJumpHeight *2/(TimeToMaxH * TimeToMaxH);
            yVel = MaxJumpHeight *2/ TimeToMaxH;
            y+=0.0001;
            JumpStart =System.nanoTime();
            Time0 =System.nanoTime();
        }
        if (flag[2]) {
            //Down
        }
        x += vx;

        if (!IsOnGround()) {
            if(y>(Game.HEIGHT()-60))
                y=(Game.HEIGHT()-60);
            DeltaTime =(float)(System.nanoTime()- Time0)/ 1_000_000_000;
            if(!flag[0]&& LongJump)
            {
                yVel =2*y/(TimeToMaxH);
                yVel += GAccel *(((float)System.nanoTime()- JumpStart)/1_000_000_000);
                LongJump =false;
            }
            y+= (GAccel * DeltaTime * DeltaTime + yVel * DeltaTime);
            yVel += GAccel * DeltaTime;

            Time0 =System.nanoTime();
        } else {
                y = 0;
        }
        if(x<0)
            x=0;
        if(x>(Game.WIDTH() - PlayerTile.TILE_WIDTH)*100)
            x=(Game.WIDTH()- PlayerTile.TILE_WIDTH)*100;
    }

    public void Draw(Graphics g)
    {
        PlayerTile.Draw(g,getX(), getY());
    }
}

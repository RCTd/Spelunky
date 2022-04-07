package GamePakage;

import GamePakage.Tiles.PlayerTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.*;

public class Player {
    //private static float XDccel=-MaxXSpeed/AccelTimeX;
    public PlayerTile PlayerTile = new PlayerTile(0);
    public boolean IsOnGround=true, HeadHit =false,wallRight=false,wallLeft=false;
    public boolean Relesed=true;
    private boolean LongJump;
    private int direction =-1,frame=0;
    private float vx=0;
    private float yVel;
    private float y,p0;
    private float x;
    private long JumpStart,Time=0;

    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }

    public Player()
    {
        x=32;
        y=64;
    }
   /* private boolean IsOnGround()
    {
        return IsOnGround;
    }*/

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y=y;
    }

    public void Update(boolean[] flag)
    {
        float deltaTime=((float) (System.nanoTime() - Time) / 1_000_000_000);

        /*if(deltaTime)
            frame++;
        if(numberOfFramesForState[PlayerTile.state]<=frame)
            frame=0;*/

        PlayerTile.state=0;//stand
        MoveLogic(flag,deltaTime);
        if (flag[0]) {
            if(Relesed)
                if(IsOnGround ||(float) (System.nanoTime() - timer) / 1_000_000_000<0.1F){
                    p0 = y;
                    yVel = -MaxJumpHeight * 2 / TimeToMaxH;
                    LongJump = true;
                    Relesed=false;
                    IsOnGround = false;
                    JumpStart = System.nanoTime();
                }
        }else
            Relesed=true;

        if (flag[2]) {
            PlayerTile.state=3;//Duck
        }

        JumpLogic(flag,deltaTime);
        if(x<0)
            x=0;
        if(x>(Game.WIDTH() - PlayerTile.TILE_WIDTH))
            x=(Game.WIDTH()- PlayerTile.TILE_WIDTH);
        Time=System.nanoTime();
        if(y>BottomLine)
            y=BottomLine;
    }

    public void Draw(Graphics g) {
        PlayerTile.frame=frame;
        PlayerTile.direction= direction;
        PlayerTile.Draw(g,getX(), getY());
    }

    private void JumpLogic(boolean[] flag,float deltaTime)
    {
        if (!IsOnGround) {
            PlayerTile.state=1;//Jump
            if(yVel==0) {p0=y;}
            if((HeadHit ||!flag[0])&& LongJump)
            {
                yVel =-2*(p0-y)/(TimeToMaxH);
                yVel += YAccel *(((float)System.nanoTime()- JumpStart)/1_000_000_000);
                LongJump =false;
                HeadHit =false;
            }
            y+= (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel+=YAccel*deltaTime;
        } else {
            /*if(abs(y-p0)>9*16) {
                p0=y;
                System.out.println("mult");
            }*/
            LongJump=false;
            y=(float)(((int) y)/16 )*16;
            yVel = 0;
        }
    }

    private void MoveLogic(boolean[] flag,float deltaTime)
    {
        int result = 0;
        if(flag[1]) {
            if (!wallRight)
                result += 1;
            else{
                vx = 0;
            }
        }
        if(flag[3]) {
            if (!wallLeft)
                result -= 1;
            else{
                vx = 0;
            }
        }
        if((vx>0&&wallRight)||(vx<0&&wallLeft)) {
            vx = 0;
        }
        if(result ==0) {
            //stop
            if (vx != 0)
                vx -= signum(vx) * XAccel * deltaTime;
        }else {    //move
            PlayerTile.state=2;//run
            direction =result;
            vx += result * XAccel * deltaTime;
        }
        //speed cap
        if(vx>MaxXSpeed||vx<-MaxXSpeed)
            vx= result *MaxXSpeed;
        //max speed variation
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
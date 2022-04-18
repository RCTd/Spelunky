package GamePakage;

import GamePakage.Tiles.PlayerTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.*;

public class Player {
    //private static float XDccel=-MaxXSpeed/AccelTimeX;
    public PlayerTile PlayerTile = new PlayerTile(0);
    public boolean IsOnGround=true, HeadHit =false,wallRight=false,wallLeft=false;
    public boolean Duck=false, LookUp=false,Moves=false;
    public boolean Relesed=true,LongJump;
    //private boolean LongJump;
    private int direction =-1;
    private float frame=0, xVel =0,yVel,y,p0,x;
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

        frame= frame + 16 * timer.getDeltaTime();
        if(numberOfFramesForState[PlayerTile.state]<=frame)
            frame=0;
        Moves=false;
        MoveLogic(flag,deltaTime);
        if (flag[0]) {
            if(Relesed)
                if(IsOnGround ||(float) (System.nanoTime() - coyotetimer) / 1_000_000_000<0.1F){
                    p0 = y;
                    yVel = -MaxJumpHeight * 2 / TimeToMaxH;
                    LongJump = true;
                    Relesed=false;
                    IsOnGround = false;
                    JumpStart = System.nanoTime();
                }
        }else
            Relesed=true;

        PlayerTile.state=0;//Stand
        if (flag[2]) {
            Duck=true;
        }else
            Duck=false;
        if(flag[5])
            LookUp=true;
        else
            LookUp=false;

        if(Moves) {
            PlayerTile.state=2;//run
            if (Duck)
                PlayerTile.state = 7;//Crawl
            else
            if(LookUp)
                PlayerTile.state=5;//RunLook
        }else {

            if (Duck)
                PlayerTile.state=3;//Duck
            if (LookUp)
                PlayerTile.state=4;//Lookup
        }
       /* if(flag[6])
        {
            PlayerTile.state=6; //Attack
        }*/

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
        PlayerTile.frame=(int) frame;
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
                xVel = 0;
            }
        }
        if(flag[3]) {
            if (!wallLeft)
                result -= 1;
            else{
                xVel = 0;
            }
        }
        if((xVel >0&&wallRight)||(xVel <0&&wallLeft)) {
            xVel = 0;
        }
        if(result ==0) {
            //stop
            if (xVel != 0)
                xVel -= signum(xVel) * XAccel * deltaTime;
        }else {    //move
            //PlayerTile.state=2;//run
            Moves=true;
            direction =result;
            xVel += result * XAccel * deltaTime;
        }
        //speed cap
        if(xVel >MaxXSpeed|| xVel <-MaxXSpeed)
            xVel = result *MaxXSpeed;
        //max speed variation
        if(flag[4]) {//Slow down on shift
            MaxXSpeed=100F;
        }else {
            MaxXSpeed=200F;
        }
        XAccel=MaxXSpeed/AccelTimeX;
        //XDccel=-MaxXSpeed/AccelTimeX;
        x += xVel *deltaTime;
    }
}
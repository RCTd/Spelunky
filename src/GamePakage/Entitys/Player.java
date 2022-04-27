package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.Tiles.PlayerTile;
import GamePakage.Tiles.WhipTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.*;

public class Player implements GameEntity {
    //private static float XDccel=-MaxXSpeed/AccelTimeX;
    Game game;
    public PlayerTile PlayerTile = new PlayerTile(0);
    public WhipTile WhipTile=new WhipTile(0);
    public boolean IsOnGround=true, HeadHit =false,wallRight=false,wallLeft=false;
    public boolean Duck=false, LookUp=false,Moves=false,TooHigh=false,HasRope=true;
    public boolean OnEdgeLeft=false,OnEdgeRight=false,Hang=false;
    public boolean CanHangLeft =false, CanHangRight =false;
    public boolean Relesed=true,LongJump,Attack;
    //private boolean LongJump;
    private int direction =-1;
    private int newState, oldState;
    private float xVel =0,yVel,y,p0,x;
    private long JumpStart,Time=0;

    public int getX() {
        return (int)x;
    }
    public int getY() {
        return (int)y;
    }

    public Player(Game game)
    {
        this.game=game;
        x=32;
        y=64;
    }

    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y=y;
    }

    public void Update()
    {
        boolean[] flag=game.keys.flag;
        float deltaTime=((float) (System.nanoTime() - Time) / 1_000_000_000);
        Moves=false;
        oldState= PlayerTile.AnimationState.state;
        if(oldState!=11) {
            MoveLogic(flag, deltaTime);
            StartJump(flag);
            trowRope(flag);
        }

        Attack=flag[6];
        Duck= flag[2];
        LookUp= flag[5];
        PlayerTile.AnimationState= PlayerTile.AnimationState.Handle(Moves,  Duck,IsOnGround, LookUp, Attack, OnEdgeLeft,OnEdgeRight, Hang,TooHigh );
        newState=PlayerTile.AnimationState.state;
        Hang= newState== 8 || newState == 9;
        TooHigh=false;

        SetHang();
        JumpLogic(flag,deltaTime);
        Time=System.nanoTime();
        /*if(x<0)
            x=0;
        if(x>(Game.WIDTH() - PlayerTile.TILE_WIDTH))
            x=(Game.WIDTH()- PlayerTile.TILE_WIDTH);

        if(y>BottomLine)
            y=BottomLine;*/

    }

    public void Draw(Graphics g) {
        PlayerTile.direction= direction;
        PlayerTile.Draw(g,getX(), getY());
        if(newState==5&&PlayerTile.AnimationState.frame>2)
        {
            WhipTile.direction=direction;
            WhipTile.state= PlayerTile.AnimationState.frame<5?0:1;
            if(WhipTile.state==0)
                WhipTile.Draw(g,getX()-direction*16,getY());
            else
                WhipTile.Draw(g,getX()+direction*16,getY());
        }
    }

    private void JumpLogic(boolean[] flag,float deltaTime)
    {
        if (!IsOnGround) {
            //if((int) yVel==0) {p0=y;}
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
            if(abs(y-p0)>9*16) {
                TooHigh=true;
                //System.out.println("too High");
            }
            p0=y;
            LongJump=false;
            y=(int)( y/16 )*16;
            yVel = 0;
        }
    }
    private void MoveLogic(boolean[] flag,float deltaTime)
    {
        int result = 0;
        if(flag[1]) {
            if (!wallRight&&!Hang)
                result += 1;
            else{
                xVel = 0;
            }
        }
        if(flag[3]) {
            if (!wallLeft&&!Hang)
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
    public void StartJump(boolean[] flag)
    {
        if (flag[0]) {
            if(Relesed)
                if(IsOnGround ||(float) (System.nanoTime() - coyotetimer) / 1_000_000_000<0.1F){
                    p0 = y;
                    yVel = -MaxJumpHeight * 2 / TimeToMaxH;
                    LongJump = true;
                    Relesed=false;
                    IsOnGround = false;
                    JumpStart = System.nanoTime();
                    if(flag[2]&&Hang) {
                        yVel=0;
                    }
                }
        }else
            Relesed=true;
    }
    public void SetHang()
    {
        if(newState==9&&newState!=oldState&&direction<0) {
            x=(int)(x/16)*16;
        }
        if(oldState==9 && newState==8) {
            x=direction>0?x+direction*16:x;
            x=(int)(x/16)*16;
            y+=16;
            direction*=-1;
        }
        if((CanHangRight &&direction>0|| CanHangLeft &&direction<0)&&Relesed)
        {
            IsOnGround=true;
            y=(int)( (y+16)/16 )*16;
            Hang=true;
        }
    }
    public void trowRope(boolean[] flag)
    {
        if(flag[7])
        {
            if(HasRope) {
                HasRope = false;
                game.entityList.add(new Rope(getX(), getY(),game));
            }
        }
        else
            HasRope=true;
    }
}
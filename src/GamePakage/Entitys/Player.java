package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.Tiles.PlayerToolsTiles.PlayerTile;
import GamePakage.Tiles.PlayerToolsTiles.WhipTile;

import java.awt.*;

import static GamePakage.Game.*;
import static java.lang.Math.*;

public class Player implements GameEntity {
    //private static float XDccel=-MaxXSpeed/AccelTimeX;
    Game game;
    public PlayerTile PlayerTile = new PlayerTile(0);
    private final WhipTile WhipTile=new WhipTile(0);
    public Flags trigFlags=new Flags();
    private int direction =-1;
    public int newState, oldState;
    private float xVel =0,yVel,y,x;
    public float p0;
    private long JumpStart,Time=0;
    private Whip whip;

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
        oldState= PlayerTile.AnimationState.state;
        PlayerTile.AnimationState= PlayerTile.AnimationState.Handle(trigFlags);
        newState=PlayerTile.AnimationState.state;

        trigFlags.Update(flag,newState);
        if(trigFlags.Climbing) {
            trigFlags.IsOnGround=true;
            x = (int)(( x + 8)/16)*16;
        }
        float deltaTime=((float) (System.nanoTime() - Time) / 1_000_000_000);

        if(newState!=11) {
            MoveLogic(flag, deltaTime);
            StartJump(flag);
            trowRope(flag);
            trowBomb(flag);
        }

        if(trigFlags.Climbing&&!trigFlags.Hang)
        {
            if(trigFlags.LookUp)
                y-=MaxXSpeed/2*deltaTime;
            if(trigFlags.Duck)
                y+=MaxXSpeed/2*deltaTime;
        }
        SetHang();
        JumpLogic(flag,deltaTime);

        Time=System.nanoTime();
        /*if(y>BottomLine)
            y=BottomLine;*/
    }

    public void Draw(Graphics g) {
        PlayerTile.direction = direction;
        PlayerTile.Draw(g, getX(), getY());
        if (newState == 5 && PlayerTile.AnimationState.frame > 2) {
            if(whip==null) {
                whip = new Whip(getX(), getY(), game);
                game.toolList.add(whip);
            }if(PlayerTile.AnimationState.frame>=5)
                whip.whipTile.state=1;
            /*WhipTile.direction = direction;
            WhipTile.state = PlayerTile.AnimationState.frame < 5 ? 0 : 1;
            if (WhipTile.state == 0)
                WhipTile.Draw(g, getX() - direction * 16, getY());
            else
                WhipTile.Draw(g, getX() + direction * 16, getY());*/
        }else
        {
            game.removeList.add(whip);
            whip=null;
        }
    }
    public void Collide()
    {
        int x= getX();
        int y= getY();
        int h=PlayerTile.TILE_HEIGHT;
        int w=PlayerTile.TILE_WIDTH;
        trigFlags.Collide(x,y, w, h, game);
    }

    private void JumpLogic(boolean[] flag,float deltaTime)
    {
        if (!trigFlags.IsOnGround) {
            if((trigFlags.HeadHit ||!flag[0])&& trigFlags.LongJump)
            {
                yVel =-2*(p0-y)/(TimeToMaxH);
                yVel += YAccel *(((float)System.nanoTime()- JumpStart)/1_000_000_000);
                trigFlags.LongJump =false;
                trigFlags.HeadHit =false;
            }
            y+= (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel+=YAccel*deltaTime;
        } else {
            if(abs(y-p0)>9*16) {
                trigFlags.TooHigh = true;
                game.hud.Lives--;
            }
            p0=y;
            trigFlags.LongJump=false;
            if(!trigFlags.Climbing)
                y=(int)( y/16 )*16;
            yVel = 0;
        }
    }
    private void MoveLogic(boolean[] flag,float deltaTime)
    {
        int dir=0;
        int result = 0;
        if(flag[1]&&!trigFlags.Hang) {
            if (!trigFlags.wallRight)
                result += 1;
            else {
                xVel = 0;
            }
            dir++;
        }
        if(flag[3]&&!trigFlags.Hang) {
            if (!trigFlags.wallLeft)
                result -= 1;
            else {
                xVel = 0;
            }
            dir--;
        }
        if((xVel >0&&trigFlags.wallRight)||(xVel <0&&trigFlags.wallLeft)) {
            xVel = 0;
        }
        if(result ==0) {
            //stop
            direction=dir!=0?dir:direction;
            if (xVel != 0)
                if(abs(xVel)<1F|| trigFlags.Climbing) {
                    xVel = 0;
                }else
                    xVel -= signum(xVel) * XAccel * deltaTime;
        }else {    //move
            trigFlags.Moves=true;
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
    private void StartJump(boolean[] flag)
    {
        if (flag[0]) {
            if(trigFlags.Relesed)
                if(trigFlags.IsOnGround ||(float) (System.nanoTime() - coyotetimer) / 1_000_000_000<0.1F){
                    p0 = y;
                    yVel = -MaxJumpHeight * 2 / TimeToMaxH;
                    trigFlags.LongJump = true;
                    trigFlags.Relesed=false;
                    trigFlags.IsOnGround = false;
                    trigFlags.Climbing=false;
                    JumpStart = System.nanoTime();
                    if(flag[2]&&trigFlags.Hang) {
                        trigFlags.Hang=false;
                        yVel=0;
                    }
                }
        }else
            trigFlags.Relesed=true;
    }
    private void SetHang()
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
        if((trigFlags.CanHangRight &&direction>0|| trigFlags.CanHangLeft &&direction<0)&&trigFlags.Relesed&&!trigFlags.Climbing)
        {
            trigFlags.IsOnGround=true;
            y=(int)( (y+16)/16 )*16;
            x = (int)(( x + 8)/16)*16;
            trigFlags.Hang=true;
        }
    }
    private void trowRope(boolean[] flag)
    {
        if(flag[7]&& game.hud.Rope>0&&trigFlags.HasRope) {
            if (!flag[2]) {
                game.toolList.add(new Rope(getX(), getY(), game));
            } else
                game.toolList.add(new Rope(getX(), getY(), game, true));
            game.hud.Rope--;
        }
        trigFlags.HasRope=!flag[7];
    }

    private void trowBomb(boolean[] flag)
    {
        if(flag[8]&& game.hud.Bomb>0&&trigFlags.HasBomb) {
            if(flag[5])
                game.toolList.add(new Bomb(getX()+8, getY(), xVel+direction*800, -abs(yVel)-(100 / 0.1F), game));
            else
                if(flag[2])
                    game.toolList.add(new Bomb(getX()+8, getY(), xVel, 0, game));
                else
                    game.toolList.add(new Bomb(getX()+8, getY(), xVel+direction*900,-abs(yVel)-(40 / 0.1F) , game));
            game.hud.Bomb--;
        }
        trigFlags.HasBomb=!flag[8];
    }

    public void TakeDamage(int d)
    {
        if(!trigFlags.TooHigh) {
            game.hud.Lives -= d;
            trigFlags.TooHigh = true;
        }
    }

    public void Jump() {
        p0 = y;
        yVel = -MaxJumpHeight * 2 / TimeToMaxH;
        trigFlags.LongJump = true;
        trigFlags.Relesed=false;
        trigFlags.IsOnGround = false;
        trigFlags.Climbing=false;
        JumpStart = System.nanoTime();
    }

    public int getDirection() {
        return direction;
    }
}
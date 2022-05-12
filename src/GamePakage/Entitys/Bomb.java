package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.GameTimer;
import GamePakage.Tiles.BombTile;
import GamePakage.Tiles.Tile;

import java.awt.*;

import static GamePakage.Game.XAccel;
import static java.lang.Math.signum;

public class Bomb implements GameEntity {
    private final BombTile tile;
    private float xVel,yVel,x,y,p0,yAccel=100/(0.1F*0.1F);
    private Game game;
    private Flags trigFlags=new Flags();
    private long StartMoment;


    public Bomb(float x, float y, float xVel, float yVel,int direction, Game game)
    {
        tile=new BombTile(0);
        this.x=x;
        this.y=y;
        this.xVel=xVel+direction*800;
        this.yVel=yVel;
        this.yVel += -100 / 0.1F;
        this.game=game;
        StartMoment=System.nanoTime();
    }

    @Override
    public void Update() {
        if(System.nanoTime()-StartMoment>2_000_000_000)
        {
            for (int i = -16; i <=16; i+=16) {
                for (int j = -16; j <=16; j+=16) {
                    game.map.tileMap[((int) y + i) / 16][((int) x+j) / 16].Destroy();
                }
            }/*
            game.map.tileMap[((int) y + 8) / 16][((int) x + 3) / 16].Destroy();
            game.map.tileMap[((int) y ) / 16][((int) x + 3) / 16].Destroy();
            game.map.tileMap[((int) y -8) / 16][((int) x + 3) / 16].Destroy();
            game.map.tileMap[((int) y + 8) / 16][((int) x + 3) / 16].Destroy();*/
            game.removeList.add(this);
        }
        float deltaTime=GameTimer.getInstance().getDeltaTime();
        x+=xVel*deltaTime;
        xVel -= signum(xVel) * XAccel * deltaTime;
        FloatLogic(deltaTime);
    }

    private void FloatLogic(float deltaTime)
    {
        if(!trigFlags.IsOnGround)
        {
            y+= (yAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel+=yAccel*deltaTime;
        }
    }

    @Override
    public void Draw(Graphics g) {
        tile.Draw(g,(int)x, (int)y);
    }

    @Override
    public void Collide() {
        int x=(int)this.x;
        int y=(int)this.y;
        //down left
        trigFlags.OnEdgeLeft=game.map.tileMap[(y+8)/16][(x+3)/16].IsSolid();
        //down right
        trigFlags.OnEdgeRight=game.map.tileMap[(y+8)/16][(x+8-3)/16].IsSolid();
        trigFlags.IsOnGround=(trigFlags.OnEdgeLeft)||(trigFlags.OnEdgeRight);
    }
}

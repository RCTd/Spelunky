package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.GameTimer;
import GamePakage.Tiles.BombTile;

import java.awt.*;

import static GamePakage.Game.*;

public class Bomb implements GameEntity {
    private final BombTile tile;
    private float xVel,yVel,x,y,p0;
    private Game game;
    private Flags trigFlags=new Flags();
    private long StartMoment;

    public Bomb(float x, float y, float xVel, float yVel, Game game)
    {
        tile=new BombTile(0);
        this.x=x;
        this.y=y;
        this.xVel=xVel;
        this.yVel=yVel;
        this.yVel = -MaxJumpHeight * 2 / TimeToMaxH;
        this.game=game;
        StartMoment=System.nanoTime();
    }

    @Override
    public void Update() {
        FloatLogic(GameTimer.getInstance().getDeltaTime());
    }

    private void FloatLogic(float deltaTime)
    {
        if(!trigFlags.IsOnGround)
        {
            y+= (YAccel * deltaTime * deltaTime + yVel * deltaTime);
            yVel+=YAccel*deltaTime;
        }else
        {
            game.removeList.add(this);
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

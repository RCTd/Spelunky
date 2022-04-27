package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.GameTimer;
import GamePakage.Tiles.RopeTile;
import GamePakage.Tiles.Tile;

import java.awt.*;


public class Rope implements GameEntity {
    private Game game;
    private float y,p0;
    private int x,state;
    private RopeTile tile;
    public boolean HeadHit=false;

    public Rope(int x,int y,Game game)
    {
        this.game=game;
        tile=new RopeTile(0);
        this.x=(x+8)/16*16;
        this.y=y;
        p0=y;
        state=0;
    }


    @Override
    public void Update() {

        if(state==0&& y>p0-8*16&&!HeadHit)
            y-= (8*16/0.2F)  * GameTimer.getInstance().getDeltaTime();
        else
        {
            state++;
            if(state<2) {
                tile.state = 1;
            }else
                tile.state=2;
            if(y<p0) {
                game.map.tileMap[(int) y / 16][x / 16] = new Tile(tile.GetBufferedImage(),0,tile.TILE_WIDTH,tile.TILE_HEIGHT);
                tile.state=0;
                y += (8 * 16 / 0.5F) * GameTimer.getInstance().getDeltaTime();
            }
            //else
                //delete rope
            state=1;
        }
    }

    @Override
    public void Draw(Graphics g) {
        tile.Draw(g,x,(int) y);
    }
}

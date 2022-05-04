package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.GameTimer;
import GamePakage.Tiles.RopeTile;
import GamePakage.Tiles.Tile;

import java.awt.*;


public class Rope implements GameEntity {
    private Game game;
    private float y,p0,p1;
    private int x,state;
    private RopeTile tile;
    public boolean HeadHit=false,FloorHit=false;

    public Rope(int x,int y,Game game)
    {
        this.game=game;
        tile=new RopeTile(0);
        this.x=(x+8)/16*16;
        this.y=y;
        p0=y;
        state=0;
    }
    public Rope(int x,int y,Game game,boolean yes)
    {
        this.game=game;
        tile=new RopeTile(0);
        this.x=(x+8)/16*16;
        this.y=y;
        p0=y+8*16;
        state=0;
    }

    @Override
    public void Update() {

        if(state==0&& y>=p0-8*16&&!HeadHit) {
            y -= (8 * 16 / 0.2F) * GameTimer.getInstance().getDeltaTime();
            p1 = y;
        }else
        {
            state++;
            tile.state=state;
            if(y<p1+8*16&&!FloorHit) {
                if(game.map.tileMap[(int) y / 16][x / 16].GetId()==0)
                    game.map.tileMap[(int) y / 16][x / 16] = new Tile(tile.GetBufferedImage(),6,tile.TILE_WIDTH,tile.TILE_HEIGHT);
                tile.state=0;
                y += (8 * 16 / 0.5F) * GameTimer.getInstance().getDeltaTime();
            }else
                game.removeList.add(this);
            state=1;
        }
    }

    @Override
    public void Draw(Graphics g) {
        tile.Draw(g,x,(int) y);
    }

    public void Collide()
    {
        HeadHit =(game.map.tileMap[((int)y-2)/16][(x+3)/16].IsSolid());//||(game.map.tileMap[((int)y-2)/16][(x+16-3)/16].IsSolid());
        FloorHit =(game.map.tileMap[((int)y+14)/16][(x+3)/16].IsSolid());//||(game.map.tileMap[((int)y+16)/16][(x+16-3)/16].IsSolid());
    }
}

package GamePakage.Tiles;

import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.size;

public class WhipTile extends Tile{
    public int direction=-1;
    public int state=0;
    public WhipTile(int id){
        super(Assets.whipSprite,id,16,16);
    }
    @Override
    public void Draw(Graphics g, int x, int y) {
        BufferedImage crntimg = img.getSubimage(0, state * 16, TILE_WIDTH, TILE_WIDTH);
        //g.drawRect(x*size,y*size,TILE_WIDTH*size,TILE_HEIGHT*size);
        g.drawImage(crntimg,
                x*size,y*size,(x+TILE_WIDTH)*size,(y+TILE_HEIGHT)*size,
                (1+direction)*TILE_WIDTH/2,0,(1-direction)*TILE_HEIGHT/2,TILE_HEIGHT, null);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}

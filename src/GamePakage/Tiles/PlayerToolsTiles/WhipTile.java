package GamePakage.Tiles.PlayerToolsTiles;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WhipTile extends Tile {
    public int direction=-1;
    public int state=0;
    public WhipTile(int id){
        super(Assets.toolsSprite,id,16,16);
    }
    @Override
    public void Draw(Graphics g, int x, int y) {
        BufferedImage crntimg = img.getSubimage(state * 16,0 , TILE_WIDTH, TILE_WIDTH);
        g.drawImage(crntimg,
                x,y,(x+TILE_WIDTH),(y+TILE_HEIGHT),
                (1+direction)*TILE_WIDTH/2,0,(1-direction)*TILE_HEIGHT/2,TILE_HEIGHT, null);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}

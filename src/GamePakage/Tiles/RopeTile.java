package GamePakage.Tiles;

import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

public class RopeTile extends Tile{
    public int state=0;
    public RopeTile(int id){
        super(Assets.toolsSprite,id,16,16);
    }
    @Override
    public void Draw(Graphics g, int x, int y) {
        BufferedImage crntimg = img.getSubimage(state * 16,16 , TILE_WIDTH, TILE_WIDTH);
        //g.drawRect(x*size,y*size,TILE_WIDTH*size,TILE_HEIGHT*size);
        g.drawImage(crntimg, x,y, null);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }

    public BufferedImage GetBufferedImage()
    {
        return img.getSubimage(state * 16,16 , TILE_WIDTH, TILE_WIDTH);
    }
}

package GamePakage.Tiles.PlayerToolsTiles;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BombTile extends Tile {
    int state=0;

    public BombTile(int id) {
        super(Assets.toolsSprite,id,8,8);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        BufferedImage crntimg = img.getSubimage(2*16+state * 8,0 , TILE_WIDTH, TILE_WIDTH);
        g.drawImage(crntimg, x,y, null);
    }

}

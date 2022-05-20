package GamePakage.Tiles.PlayerToolsTiles;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public class BombTile extends Tile {
    float state=0;

    public BombTile(int id) {
        super(Assets.toolsSprite,id,8,8);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        state+=16*timer.getDeltaTime();
        if(state>=2)state=0;
        BufferedImage crntimg = img.getSubimage(2*16+(int) state *16,0 , TILE_WIDTH, TILE_WIDTH);
        g.drawImage(crntimg, x,y, null);
    }

}

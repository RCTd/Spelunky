package GamePakage.Tiles;

import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public class BatTile extends Tile{
    private int state;
    private float frame;
    public BatTile(int idd) {
        super(Assets.EnemySprite, idd, 16, 16);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        frame=(frame>=2)?0:(frame+16 * timer.getDeltaTime());
        BufferedImage crntimg=img.getSubimage((int) frame,16*state,16,16);
        g.drawImage(crntimg, x, y, 16, 16, null);
    }

    public void setState(int state) {
        this.state = state;
    }

}

package GamePakage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public class BatTile extends Tile{
    private int state=0;
    private float frame=0;
    public BatTile(int idd) {
        super(Assets.EnemySprite, idd, 16, 16);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        frame=frame+16 * timer.getDeltaTime();
        if(frame>=Math.round(state/2F+1)) frame=0;
        BufferedImage crntimg=img.getSubimage(((int) frame)*16,16*state,16,16);
        g.drawImage(crntimg, x, y, 16, 16, null);
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getState() {
        return state;
    }
}

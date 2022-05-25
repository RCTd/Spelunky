package GamePakage;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public class SnakeTile extends Tile {
/*    private int state=0;*/
    private float frame=0;
    private int direction=1;
    public SnakeTile(int idd) {
        super(Assets.EnemySprite, idd, 16, 16);
    }

    @Override
    public void Draw(Graphics g, int x, int y ) {
        frame=frame+16 * timer.getDeltaTime();
        if(frame>=3) frame=0;
        BufferedImage crntimg=img.getSubimage(((int) frame)*16,16*3,16,16);
        g.drawImage(crntimg,
                x,y,(x+16),(y+16),
                (1+direction)*16/2,0,(1-direction)*16/2,16, null);
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }
}

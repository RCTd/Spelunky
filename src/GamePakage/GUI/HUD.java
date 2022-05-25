package GamePakage.GUI;

import GamePakage.Graphics.Assets;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class HUD {
    public int Lives=4;
    public int Level=0;
    public int Rope=4;
    public int Bomb=4;
    public int Score=0;

    public HUD(){
    }

    public void Draw(Graphics g){
        Font fnt;
        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, new File("rsc/Fonts/8-bit-hud.TTF")).deriveFont(10F);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        g.setFont(fnt);
        g.drawString(Integer.toString(Level),3*16,26+16);
        g.drawString(Integer.toString(Lives),3*16,10+16);
        g.drawString(Integer.toString(Rope),5*16,10+16);
        g.drawString(Integer.toString(Bomb),7*16,10+16);
        g.drawString(Integer.toString(Score),9*16,10+16);
        g.drawImage(Assets.LvlIcon,2*16,10+16,null);
        g.drawImage(Assets.HeartIcon,2*16,10,null);
        g.drawImage(Assets.RopeIcon,4*16,10,null);
        g.drawImage(Assets.BombIcon,6*16,10,null);
        g.drawImage(Assets.DollarIcon,8*16,10,null);
    }

    public void reset(){
        Lives=4;
        Level=-1;
        Rope=4;
        Bomb=4;
        Score=0;
    }
}

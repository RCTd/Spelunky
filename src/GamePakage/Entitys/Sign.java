package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Sign implements GameEntity {
    private final int x,y;
    private final String text;
    private final BufferedImage image;
    private boolean isActive=false;
    private final Game game;

    public Sign(int x, int y,Game game, String text){
        this.x = x;
        this.y = y;
        this.text = text;
        this.game = game;
        this.image = Assets.MsgSign;
    }

    @Override
    public void Update() {
        int px = game.getPlayer().getX();
        int py = game.getPlayer().getY();

        float distance = (float) Math.sqrt(Math.pow(px-x,2)+Math.pow(py-y,2));
        isActive = distance<=20;
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(image,x,y,null);
        if(isActive){
            Font fnt;
            try {
                fnt = Font.createFont(Font.TRUETYPE_FONT, new File("rsc/8-bit-hud.TTF")).deriveFont(5F);
            } catch (IOException | FontFormatException e) {
                throw new RuntimeException(e);
            }
            g.setFont(fnt);
            g.setColor(Color.WHITE);
            g.drawString(text,x-g.getFontMetrics().stringWidth(text)/2,y-g.getFontMetrics().getHeight()/2);
        }
    }

    @Override
    public void Collide() {

    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}

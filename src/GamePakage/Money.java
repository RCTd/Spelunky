package GamePakage;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Money {
    private int value;
    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final BufferedImage image;

    public Money(int x, int y) {
        this.x = x;
        this.y = y;
        value=(int) (Math.random()*1600);
        if(value<=50){
            image=Assets.TinyGold;
            value=50;
        }
        else if(value<=100){
            image=Assets.SmallGold;
            value=100;
        }
        else if(value<=500){
            image=Assets.Gold;
            value=500;
        }
        else if(value<=700){
            image=Assets.Ruby;
            value=700;
        }
        else if(value<=800){
            image=Assets.Sapphire;
            value=800;
        }
        else if(value<=1200){
            image=Assets.RubyBig;
        }
        else if(value<=1500){
            image=Assets.BigGold;
            value=1500;
        }
        else if(value<=1600){
            image=Assets.SapphireBig;
            value=1600;
        }
        else
        {
            image=Assets.Gold;
        }
        width=image.getWidth();
        height=image.getHeight();
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void Draw(Graphics g){
            g.drawImage(image, x+8-width/2, y+16-height, width, height, null);
    }
}

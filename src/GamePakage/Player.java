package GamePakage;

import Tiles.Tile;
import Tiles.PlayerTile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {
    public Tile PlayerTile = new PlayerTile(0);
    private int x,y,dx,h;
    private float vx,vy,vc,dtf,g,p0,tf;
    private long start;

    public int getX() {
        return x/100;
    }
    public int getY() {
        return (Game.HEIGHT()-60-y);
    }

    public Player()
    {
        x=100;
        y=0;
        dx=0;
        vc=0;
    }
    private boolean IsOnGround()
    {
        return y<=0;
    }
    public void Update(boolean[] flag)
    {
        //System.out.println(x+" "+y);
        vx = 0;
        vy = 0;
        if (flag[1]) {
            if(flag[3])
                vx=0;
            else
                vx = 4;
        } else if (flag[3]) {
            vx = -4;
        }
        if (flag[0]&&IsOnGround()) {
            h=80;
            tf= 0.7F;
            y++;
            start=System.nanoTime();
        }
        if (flag[2]) {
            //Down
        }
        x += vx;

        if (!IsOnGround()) {
            if(y>(Game.HEIGHT()-60))
                y=(Game.HEIGHT()-60);
            dtf=(float)(System.nanoTime()-start)/ 1_000_000_000;
            dx= (int)(-(h*4/(tf*tf))*dtf*dtf+tf*(h*4/(tf*tf))*dtf);
            y= dx==0?1:dx;
            /*System.out.print(dtf+"\t");
            System.out.print((g*dtf*dtf/2+vy*dtf+p0)+"\t");
            System.out.println(y);*/
        } else {
                y = 0;
        }
        if(x<0)
            x=0;
        if(x>(Game.WIDTH() - PlayerTile.TILE_WIDTH)*100)
            x=(Game.WIDTH()- PlayerTile.TILE_WIDTH)*100;
    }

    public void Draw(Graphics g)
    {
        PlayerTile.Draw(g,getX(), getY());
    }
}

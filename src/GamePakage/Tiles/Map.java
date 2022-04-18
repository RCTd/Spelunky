package GamePakage.Tiles;

import GamePakage.Game;
import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.size;

public class Map {
    public BufferedImage BackGround;
    public Tile[][] tileMap;
    public int width,height;
    public int[][] matrix;
    public Map()
    {
        width=42;height=20;
        tileMap=new Tile[height][width];
    }
    public Map(int width,int height)
    {
        this.width=width;
        this.height=height;
        tileMap=new Tile[height][width];
    }

    public void CreateBgImage()
    {
        BackGround = new BufferedImage(
                Game.WIDTH()*size, Game.HEIGHT()*size, //work these out
                BufferedImage.TYPE_INT_RGB);

        Graphics g = BackGround.getGraphics();
        for (int i = 0; i <= Game.WIDTH()/64; i++) {
            for (int j = 0; j <= Game.HEIGHT()/64; j++) {
                g.drawImage(Assets.bgWall,i*64*size,j*64*size,64*size,64*size,null);
            }
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallRock,(int)(Math.random()*Game.WIDTH()*size),(int)(Math.random()*Game.HEIGHT()*size),50*size ,50*size,null );
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallHoll,(int)(Math.random()*Game.WIDTH()*size),(int)(Math.random()*Game.HEIGHT()*size),50*size ,50*size,null );
        }
    }

    public void LoadTutorial()
    {
         matrix=new int[][] {{184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,0,0,0,184,184,184,0,0,0,0,0,0,0,0,0,0,0,0,0,321,1329,1525,1538,621,0,0,0,0,1037,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,184,184,184,184,184,1038,184},
                {184,626,1329,0,0,0,0,0,0,0,0,0,0,0,0,1329,0,0,0,1681,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1037,184},
                {184,184,184,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,184,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1037,184},
                {184,184,184,0,0,1329,0,0,184,0,0,1329,0,0,184,184,184,184,184,184,184,184,184,0,1329,0,889,889,889,889,889,889,886,0,0,0,0,0,0,0,1329,184},
                {184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,1329,0,0,0,0,184,184,184},
                {184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,1329,0,0,0,184,184},
                {184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,0,0,184,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,0,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,184,184,184,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1329,0,0,103,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,0,0,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,0,0,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,368,1329,0,1329,0,0,0,0,0,0,0,1329,0,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,0,0,0,0,0,0,0,0,0,184,184,184,184,184,0,0,0,0,184,184,184,184,0,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,0,626,0,0,0,0,0,0,0,184,184,184,184,184,184,0,0,0,0,0,0,0,0,0,184,184,184,184,0,0,0,0,0,0,0,0,0,0,0,184},
                {184,0,184,184,184,0,1329,0,0,0,184,184,184,184,184,184,184,0,0,0,0,0,0,0,0,184,184,184,184,184,0,1329,0,0,0,0,1329,0,1329,0,1504,184},
                {184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184},
                {184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184,184}};
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j]==184) {
                    double rnd=Math.random();
                    if (rnd < 0.01)
                        tileMap[i][j] = new Tile(Assets.soilBigGold, 185, 16, 16);
                    else
                        if(rnd<0.05)
                            tileMap[i][j]=new Tile(Assets.soilGold,186,16,16);
                        else
                        if(rnd<0.1)
                            tileMap[i][j]=new Tile(Assets.soilRock,186,16,16);
                        else
                            if(i+1<height&&matrix[i+1][j]==184)
                                tileMap[i][j] = new WallTile(184);
                            else
                                tileMap[i][j]=new Tile(Assets.soil,186,16,16);
                }
            }
        }
    }

    public void Draw(Graphics g)
    {
        g.drawImage(BackGround,0,0,null);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if(matrix[i][j]==184) {
                    if(i>1&&matrix[i-1][j]!=184)
                        g.drawImage(Assets.upWall,j*16*size,(i*16-16)*size,16*size,16*size,null);
                    tileMap[i][j].Draw(g, j * 16, i * 16);
                }
            }
        }
    }
}

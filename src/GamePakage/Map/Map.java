package GamePakage.Map;

import GamePakage.Game;
import GamePakage.Graphics.Assets;
import GamePakage.Tiles.*;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Map {
    public BufferedImage BackGround;
    public Tile[][] tileMap;
    public int width, height;
    public int x=20,y=20;
    public int[][] matrix;

    public Map() {
        width = 42;
        height = 20;
        tileMap = new Tile[height][width];
    }

    public Map(int width, int height) {
        this.width = width;
        this.height = height;
        tileMap = new Tile[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tileMap[j][i] = new Tile(null, 0, 0, 0);
            }
        }
    }

    public void CreateBgImage() {
        BackGround = new BufferedImage(
                width*16, height*16, //work these out
                BufferedImage.TYPE_INT_RGB);

        Graphics g = BackGround.getGraphics();
        for (int i = 0; i <= width*16 / 64; i++) {
            for (int j = 0; j <= height*16 / 64; j++) {
                g.drawImage(Assets.bgWall, i * 64, j * 64, 64, 64, null);
            }
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallRock, (int) (Math.random() * width*16), (int) (Math.random() * height*16), 50, 50, null);
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallHoll, (int) (Math.random() * width*16), (int) (Math.random() * height*16), 50, 50, null);
        }
    }

    public void LoadTutorial() {
        width=42;height=20;
        tileMap = new Tile[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tileMap[j][i] = new Tile(null, 0, 0, 0);
            }
        }
        CreateBgImage();
        matrix = new int[][]{{184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 321, 1329, 1525, 1538, 621, 0, 0, 0, 0, 1037, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 184, 184, 184, 184, 1038, 184},
                {184, 626, 1329, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1329, 0, 0, 0, 1681, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1037, 184},
                {184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1037, 184},
                {184, 184, 184, 0, 0, 1329, 0, 0, 184, 0, 0, 1329, 0, 0, 184, 184, 184, 184, 184, 184, 184, 184, 184, 0, 1329, 0, 889, 889, 889, 889, 889, 889, 886, 0, 0, 0, 0, 0, 0, 0, 1329, 184},
                {184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 1329, 0, 0, 0, 0, 184, 184, 184},
                {184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 1329, 0, 0, 0, 184, 184},
                {184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 0, 0, 184, 184},
                {184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1329, 0, 0, 103, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 368, 1329, 0, 1329, 0, 0, 0, 0, 0, 0, 0, 1329, 0, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 0, 0, 0, 0, 184, 184, 184, 184, 0, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 0, 626, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 184},
                {184, 0, 184, 184, 184, 0, 1329, 0, 0, 0, 184, 184, 184, 184, 184, 184, 184, 0, 0, 0, 0, 0, 0, 0, 0, 184, 184, 184, 184, 184, 0, 1329, 0, 0, 0, 0, 1329, 0, 1329, 0, 1504, 184},
                {184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184},
                {184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184, 184}};
        for (int i = 0; i < width; i++) {
            tileMap[0][i] = tileMap[height-1][i] = new EdgeTile(3);
        }
        for (int j = 0; j < height; j++) {
            tileMap[j][0] = tileMap[j][width-1] = new EdgeTile( 3);
        }

        for (int i = 1; i < height; i++) {
            for (int j = 1; j < width; j++) {
                if (matrix[i][j] == 184)
                    if (tileMap[i-1][j].GetId() == 0) {
                        tileMap[i][j] = new WallTile(Assets.BrickUp, 2);
                        tileMap[i-1][j]= new WallTopTile(1);
                    } else
                        tileMap[i][j] = new WallTile(1);
                else {
                    tileMap[i][j] = new Tile(null, 0, 16, 16);
                    if (tileMap[i - 1][j].GetId() == 1)
                        tileMap[i - 1][j] = new WallTile(Assets.BrickDown, 1);
                    if (tileMap[i - 1][j].GetId() == 2)
                        tileMap[i - 1][j] = new WallTile(Assets.BrickUp2, 1);
                }
            }
        }
    }

    public void Draw(Graphics g) {
        g.drawImage(BackGround, 0, 0, null);
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                tileMap[i][j].Draw(g, j * 16, i * 16);
            }
        }
    }

    public void Level() {
        width=42;
        height=4*8+2;
        CreateBgImage();
        tileMap = new Tile[height][width];
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                tileMap[j][i] = new Tile(null, 0, 0, 0);
            }
        }
        Path path = new Path();
        for (int i = 0; i < width; i++) {
            tileMap[0][i] = tileMap[height-1][i] = new EdgeTile(3);
        }
        for (int j = 0; j < height; j++) {
            tileMap[j][0] = tileMap[j][width-1] = new EdgeTile( 3);
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                SetTileMap(tileMap, i*10+1,j*8+1,path);
            }
        }
    }

    public void SetTileMap(Tile[][] tileMap, int i, int j,Path path) {
        String strTemp=RoomGetTemplate(path,i/10,j/8);
        int m=0;
        for (int k = 0; k < 8; k++) {
            for (int l = 0; l < 10; l++) {
                if(strTemp.charAt(m)=='0') {
                    tileMap[j + k][i + l] = new Tile(null, 0, 16, 16);
                    if(tileMap[j+k-1][i+l].GetId()==1)
                        tileMap[j + k-1][i + l]=new WallTile(Assets.BrickDown,1);
                    if(tileMap[j+k-1][i+l].GetId()==2)
                        tileMap[j + k-1][i + l]=new WallTile(Assets.BrickUp2,1);
                }else
                if(strTemp.charAt(m)=='1'||(strTemp.charAt(m)=='2'&&Math.random()<0.5)) {
                    if(tileMap[j+k-1][i+l].GetId()==0) {
                        tileMap[j+k][i+l]=new WallTile(Assets.BrickUp,2);
                        tileMap[j + k-1][i + l] = new WallTopTile(1);
                    }else
                        tileMap[j + k][i + l] = new WallTile(1);
                }else
                if(strTemp.charAt(m)=='L')
                    tileMap[j + k][i + l] = new Tile(Assets.Ladder,6,16,16);
                else
                if(strTemp.charAt(m)=='P')
                    tileMap[j + k][i + l] = new Tile(Assets.LadderTop,6,16,16);
                else
                if(strTemp.charAt(m)=='7'){
                    if(path.rand(1,3)==3)
                        tileMap[j + k][i + l] = new WallTopTile(Assets.Spikes,3);
                }else
                if(strTemp.charAt(m)=='9') {
                    if (j / 10 == 0) {
                        x=(i+l)*16;y=(j+k)*16;
                        tileMap[j + k][i + l] = new WallTopTile(Assets.Entrance, 4);
                    }else
                        tileMap[j + k][i + l] = new WallTopTile(Assets.Exit, 10);
                }
                m++;
            }
        }
    }

    public String RoomGetTemplate(Path path,int i,int j)
    {
        if(path.path[j][i]==0)
            path.path[j][i]= 1;
        int n;
        String strTemp="00000000000000000000000000000000000000000000000000000000000000000000000000000000";
        if (j==0&&i == path.start){
            n=path.rand(1,8);
            if(path.path[j][i]==2)
                n= path.rand(5,8);
            switch (n) {
                case 1: {strTemp = "60000600000000000000000000000000000000000008000000000000000000000000001111111111";break;}
                case 2: {strTemp = "11111111112222222222000000000000000000000008000000000000000000000000001111111111";break;}
                case 3: {strTemp = "00000000000008000000000000000000L000000000P111111000L111111000L00111111111111111";break;}
                case 4: {strTemp = "0000000000008000000000000000000000000L000111111P000111111L001111100L001111111111";break;}
                        // hole*/
                case 5: {strTemp = "60000600000000000000000000000000000000000008000000000000000000000000002021111120";break;}
                case 6: {strTemp = "11111111112222222222000000000000000000000008000000000000000000000000002021111120";break;}
                case 7: {strTemp = "00000000000008000000000000000000L000000000P111111000L111111000L00011111111101111";break;}
                case 8: {strTemp = "0000000000008000000000000000000000000L000111111P000111111L001111000L001111011111";break;}
            }
        }else
        if(j==3&&i==path.end)
        {
            n= path.rand(1,6);
            if(path.path[j-1][i]==2)
                n= path.rand(1,4);
            switch (n){
                case 1: { strTemp = "00000000006000060000000000000000000000000008000000000000000000000000001111111111"; break; }
                case 2: { strTemp = "00000000000000000000000000000000000000000008000000000000000000000000001111111111"; break; }
                case 3: { strTemp = "00000000000010021110001001111000110111129012000000111111111021111111201111111111"; break; }
                case 4: { strTemp = "00000000000111200100011110010021111011000000002109011111111102111111121111111111"; break; }
                // no drop
                case 5: { strTemp = "60000600000000000000000000000000000000000008000000000000000000000000001111111111"; break; }
                case 6: { strTemp = "11111111112222222222000000000000000000000008000000000000000000000000001111111111"; break; }
            }
        }else
        if(path.path[j][i]==1)
        {
            n= path.rand(1,11);
            switch (n){
                // basic rooms
                case 1: { strTemp = "60000600000000000000000000000000000000000050000000000000000000000000001111111111"; break; }
                case 2: { strTemp = "60000600000000000000000000000000000000005000050000000000000000000000001111111111"; break; }
                case 3: { strTemp = "60000600000000000000000000000000050000000000000000000000000011111111111111111111"; break; }
                case 4: { strTemp = "60000600000000000000000600000000000000000000000000000222220000111111001111111111"; break; }
                case 5: { strTemp = "11111111112222222222000000000000000000000050000000000000000000000000001111111111"; break; }
                case 6: { strTemp = "11111111112111111112022222222000000000000050000000000000000000000000001111111111"; break; }
                // low ceiling
                case 7: { strTemp = "11111111112111111112211111111221111111120111111110022222222000000000001111111111"; break; }
                // ladders
                case 8: {
                    if (path.rand(1,2) == 1)
                        strTemp = "1111111111000000000L111111111P000000000L5000050000000000000000000000001111111111";
                    else  strTemp = "1111111111L000000000P111111111L0000000005000050000000000000000000000001111111111"; break;
                }
                case 9: { strTemp = "000000000000L0000L0000P1111P0000L0000L0000P1111P0000L1111L0000L1111L001111111111"; break; }
                // upper plats
                case 10: { strTemp = "00000000000111111110001111110000000000005000050000000000000000000000001111111111"; break; }
                case 11: { strTemp = "00000000000000000000000000000000000000000021111200021111112021111111121111111111"; break; }
            }
        }else
        if(path.path[j][i]==3){
            n= path.rand(1,7);
            switch (n){
                // basic rooms
                case 1: { strTemp = "00000000000000000000000000000000000000000050000000000000000000000000001111111111"; break; }
                case 2: { strTemp = "00000000000000000000000000000000000000005000050000000000000000000000001111111111"; break; }
                case 3: { strTemp = "00000000000000000000000000000050000500000000000000000000000011111111111111111111"; break; }
                case 4: { strTemp = "00000000000000000000000600000000000000000000000000000111110000111111001111111111"; break; }
                // upper plats
                case 5: { strTemp = "00000000000111111110001111110000000000005000050000000000000000000000001111111111"; break; }
                case 6: { strTemp = "00000000000000000000000000000000000000000021111200021111112021111111121111111111"; break; }
                case 7: { strTemp = "10000000011112002111111200211100000000000022222000111111111111111111111111111111"; break; }
            }
        }else
        if(path.path[j][i]==2)
        {
            n= path.rand(1,8);
            switch (n){
                case 1: { strTemp = "00000000006000060000000000000000000000006000060000000000000000000000000000000000"; break; }
                case 2: { strTemp = "00000000006000060000000000000000000000000000050000000000000000000000001202111111"; break; }
                case 3: { strTemp = "00000000006000060000000000000000000000050000000000000000000000000000001111112021"; break; }
                case 4: { strTemp = "00000000006000060000000000000000000000000000000000000000000002200002201112002111"; break; }
                case 5: { strTemp = "00000000000000220000000000000000200002000112002110011100111012000000211111001111"; break; }
                case 6: { strTemp = "00000000000060000000000000000000000000000000000000001112220002100000001110111111"; break; }
                case 7: { strTemp = "00000000000060000000000000000000000000000000000000002221110000000001201111110111"; break; }
                case 8: { strTemp = "00000000000060000000000000000000000000000000000000002022020000100001001111001111"; break; }
                /*if up!=2*/
                case 9: { strTemp = "11111111112222222222000000000000000000000000000000000000000000000000001120000211"; break; }
                case 10: { strTemp = "11111111112222111111000002211100000002110000000000200000000000000000211120000211"; break; }
                case 11: { strTemp = "11111111111111112222111220000011200000000000000000000000000012000000001120000211"; break; }
                case 12: { strTemp = "11111111112111111112021111112000211112000002112000000022000002200002201111001111"; break; }
            }
        }

        String strObs1 = "00000";
        String strObs2 = "00000";
        String strObs3 = "00000";
        int pos=0,tmp;
        pos=strTemp.indexOf("8");
        while (pos>=0)
        {
            switch(path.rand(1,8))
            {
                case 1: { strObs1 = "00900"; strObs2 = "01110"; strObs3 = "11111"; break; }
                case 2: { strObs1 = "00900"; strObs2 = "02120"; strObs3 = "02120"; break; }
                case 3: { strObs1 = "00000"; strObs2 = "00000"; strObs3 = "92222"; break; }
                case 4: { strObs1 = "00000"; strObs2 = "00000"; strObs3 = "22229"; break; }
                case 5: { strObs1 = "00000"; strObs2 = "11001"; strObs3 = "19001"; break; }
                case 6: { strObs1 = "00000"; strObs2 = "10011"; strObs3 = "10091"; break; }
                case 7: { strObs1 = "11111"; strObs2 = "10001"; strObs3 = "40094"; break; }
                case 8: { strObs1 = "00000"; strObs2 = "12021"; strObs3 = "12921"; break; }
            }
            strTemp=strTemp.substring(0,pos)+strObs1+strTemp.substring(pos+5);
            strTemp=strTemp.substring(0,pos+10)+strObs2+strTemp.substring(pos+15);
            strTemp=strTemp.substring(0,pos+20)+strObs3+strTemp.substring(pos+25);
            tmp=strTemp.substring(pos).indexOf("8");
            pos=tmp>0?pos+tmp:tmp;
        }
        pos=strTemp.indexOf("5");
        while (pos>=0)
        {
            switch(path.rand(1,16))
            {
                case 1: { strObs1 = "11111"; strObs2 = "00000"; strObs3 = "00000"; break; }
                case 2: { strObs1 = "00000"; strObs2 = "11110"; strObs3 = "00000"; break; }
                case 3: { strObs1 = "00000"; strObs2 = "01111"; strObs3 = "00000"; break; }
                case 4: { strObs1 = "00000"; strObs2 = "00000"; strObs3 = "11111"; break; }
                case 5: { strObs1 = "00000"; strObs2 = "20200"; strObs3 = "17177"; break; }
                case 6: { strObs1 = "00000"; strObs2 = "02020"; strObs3 = "71717"; break; }
                case 7: { strObs1 = "00000"; strObs2 = "00202"; strObs3 = "77171"; break; }
                case 8: { strObs1 = "00000"; strObs2 = "22200"; strObs3 = "11100"; break; }
                case 9: { strObs1 = "00000"; strObs2 = "02220"; strObs3 = "01110"; break; }
                case 10: { strObs1 = "00000"; strObs2 = "00222"; strObs3 = "00111"; break; }
                case 11: { strObs1 = "11100"; strObs2 = "22200"; strObs3 = "00000"; break; }
                case 12: { strObs1 = "01110"; strObs2 = "02220"; strObs3 = "00000"; break; }
                case 13: { strObs1 = "00111"; strObs2 = "00222"; strObs3 = "00000"; break; }
                case 14: { strObs1 = "00000"; strObs2 = "02220"; strObs3 = "21112"; break; }
                case 15: { strObs1 = "00000"; strObs2 = "20100"; strObs3 = "77117"; break; }
                case 16: { strObs1 = "00000"; strObs2 = "00102"; strObs3 = "71177"; break; }
            }
            strTemp=strTemp.substring(0,pos)+strObs1+strTemp.substring(pos+5);
            strTemp=strTemp.substring(0,pos+10)+strObs2+strTemp.substring(pos+15);
            strTemp=strTemp.substring(0,pos+20)+strObs3+strTemp.substring(pos+25);
            tmp=strTemp.substring(pos).indexOf("5");
            pos=tmp>0?pos+tmp:tmp;
        }
        pos=strTemp.indexOf("6");
        while (pos>=0)
        {
            switch(path.rand(1,10))
            {
                case 1: { strObs1 = "11111"; strObs2 = "00000"; strObs3 = "00000"; break; }
                case 2: { strObs1 = "22222"; strObs2 = "00000"; strObs3 = "00000"; break; }
                case 3: { strObs1 = "11100"; strObs2 = "22200"; strObs3 = "00000"; break; }
                case 4: { strObs1 = "01110"; strObs2 = "02220"; strObs3 = "00000"; break; }
                case 5: { strObs1 = "00111"; strObs2 = "00222"; strObs3 = "00000"; break; }
                case 6: { strObs1 = "00000"; strObs2 = "01110"; strObs3 = "00000"; break; }
                case 7: { strObs1 = "00000"; strObs2 = "01110"; strObs3 = "02220"; break; }
                case 8: { strObs1 = "00000"; strObs2 = "02220"; strObs3 = "01110"; break; }
                case 9: { strObs1 = "00000"; strObs2 = "00220"; strObs3 = "01111"; break; }
                case 10: { strObs1 = "00000"; strObs2 = "22200"; strObs3 = "11100"; break; }
            }
            strTemp=strTemp.substring(0,pos)+strObs1+strTemp.substring(pos+5);
            strTemp=strTemp.substring(0,pos+10)+strObs2+strTemp.substring(pos+15);
            strTemp=strTemp.substring(0,pos+20)+strObs3+strTemp.substring(pos+25);
            tmp=strTemp.substring(pos).indexOf("6");
            pos=tmp>0?pos+tmp:tmp;
        }
        return strTemp;
    }
}


package GamePakage.Tiles;
import GamePakage.Graphics.Assets;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.size;

/*! \class public class WaterTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */public class PlayerTile extends Tile {
    public int state=0,frame=0,direction=-1;
    private BufferedImage crntimg;
    /*! \fn public WaterTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public PlayerTile(int id) {
        super(Assets.playerSprite, id,16,16);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        crntimg=img.getSubimage(frame*16,state*16,TILE_WIDTH,TILE_WIDTH);
                                                                                                        //0,0,16,16
                                                                                                        //16,0,0,16
        g.drawImage(crntimg,x*size,y*size,(x+TILE_WIDTH)*size,(y+TILE_HEIGHT)*size,(1+direction)*8,0,(1-direction)*8,16, null);
    }

    /*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
    */
}

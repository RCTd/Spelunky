package GamePakage.Tiles;
import GamePakage.Graphics.Assets;

import java.awt.image.BufferedImage;

/*! \class public class WaterTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */public class WallTile extends Tile {
    /*! \fn public WaterTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public WallTile(int id) {
        super(null,id,16,16);
        double rnd = Math.random();
        if (rnd < 0.1)
            img=Assets.BrickGoldBig;
        else if (rnd < 0.2)
            img=Assets.BrickGold;
        else if (rnd < 0.4)
            img=Assets.Block;
        else if (rnd < 0.5)
            img=Assets.Brick2;
        else
            img=Assets.Brick;
    }

    public WallTile(BufferedImage img,int id)
    {
        super(null,id,16,16);
        this.img=img;
    }

    @Override
    public boolean IsSolid() {
        return true;
    }

    @Override
    public boolean IsDistructable() {
        return true;
    }

    /*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
    */
}

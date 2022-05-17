package GamePakage.Tiles.WorldTiles;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.Tile;

import java.awt.image.BufferedImage;

public class WallTopTile extends Tile {
    boolean destructable=true;
    /*! \fn public WaterTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public WallTopTile(int id) {
        super(null,id,16,16);
        double rnd = Math.random();
        if (rnd < 0.04)
            img= Assets.BrickTop2;
        else
            img=Assets.BrickTop;
    }
    public WallTopTile(BufferedImage img,int id)
    {
        super(img,id,16,16);
    }
    public WallTopTile(BufferedImage img,int id,boolean destructable)
    {
        super(img,id,16,16);
        this.destructable=destructable;
    }

    @Override
    public boolean IsDistructable() {
        return destructable;
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
/*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
    */
}

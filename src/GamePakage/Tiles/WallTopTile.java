package GamePakage.Tiles;

import GamePakage.Graphics.Assets;

public class WallTopTile extends Tile {
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

    @Override
    public boolean IsDistructable() {
        return true;
    }

    @Override
    public boolean IsSolid() {
        return false;
    }
/*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
    */
}

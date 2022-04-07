package GamePakage.Tiles;
import GamePakage.Graphics.Assets;

import java.awt.*;

/*! \class public class WaterTile extends Tile
    \brief Abstractizeaza notiunea de dala de tip apa.
 */public class PlayerTile extends Tile {
    /*! \fn public WaterTile(int id)
       \brief Constructorul de initializare al clasei

       \param id Id-ul dalei util in desenarea hartii.
    */
    public PlayerTile(int id) {
        super(Assets.playerLeft, id,16,16);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        super.Draw(g, x, y);
    }

    /*! \fn public boolean IsSolid()
       \brief Suprascrie metoda IsSolid() din clasa de baza in sensul ca va fi luat in calcul in caz de coliziune.
    */
}

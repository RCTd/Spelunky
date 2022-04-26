package GamePakage.Tiles;


import java.awt.*;
import java.awt.image.BufferedImage;

/*! \class public class Tile
    \brief Retine toate dalele intr-un vector si ofera posibilitatea regasirii dupa un id.
 */
public class Tile {
    public int TILE_WIDTH;
    public int TILE_HEIGHT;
    protected BufferedImage img;                                    /*!< Imaginea aferenta tipului de dala.*/
    protected final int id;                                         /*!< Id-ul unic aferent tipului de dala.*/

/*
    private static final int NO_TILES = 32;
    public static Tile[] tiles = new Tile[NO_TILES];       */
/*!< Vector de referinte de tipuri de dale.*/


    public Tile(BufferedImage image, int idd,int width,int height) {
        img = image;
        id = idd;
        TILE_WIDTH=width;
        TILE_HEIGHT=height;
    }

    /*public void Update() {

    }*/

    /*! \fn public void Draw(Graphics g, int x, int y)
        \brief Deseneaza in fereastra dala.

        \param g Contextul grafic in care sa se realizeze desenarea
        \param x Coordonata x in cadrul ferestrei unde sa fie desenata dala
        \param y Coordonata y in cadrul ferestrei unde sa fie desenata dala
     */
    public void Draw(Graphics g, int x, int y) {
        /// Desenare dala
        g.drawImage(img, x , y, TILE_WIDTH , TILE_HEIGHT , null);
    }

    /*! \fn public boolean IsSolid()
        \brief Returneaza proprietatea de dala solida (supusa coliziunilor) sau nu.
     */
    public boolean IsSolid() {
        return false;
    }

    /*! \fn public int GetId()
        \brief Returneaza id-ul dalei.
     */
    public int GetId() {
        return id;
    }
}

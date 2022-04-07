package GamePakage.Graphics;

import java.awt.image.BufferedImage;
/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage playerLeft;
    public static BufferedImage playerSprite;

    public static BufferedImage soil;
    public static BufferedImage soilRock;
    public static BufferedImage soilGold;
    public static BufferedImage soilBigGold;
    public static BufferedImage Wall;
    public static BufferedImage upWall;

    public static BufferedImage bgWall;
    public static BufferedImage BgWallRock;
    public static BufferedImage BgWallHoll;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        //SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/textures/PaooGameSpriteSheet.png")); res/textures/spritesheet.png
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/spritesheet.png"));

        playerSprite=ImageLoader.LoadImage("/PlayerSprite.png");
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        playerLeft = sheet.crop(2, 42, 16, 16);
        upWall=sheet.crop(58,198,16,16);
        Wall=sheet.crop(798,74,16,16);
        soil = sheet.crop(738, 74, 16, 16);
        soilRock = sheet.crop(718, 74, 16, 16);
        soilGold=sheet.crop(778,74,16,16);
        soilBigGold=sheet.crop(758,74,16,16);

        bgWall=sheet.crop(2,198,32,32);
        BgWallRock=sheet.crop(1162,163,32,32);
        BgWallHoll=sheet.crop(1162,127,32,32);
    }
}

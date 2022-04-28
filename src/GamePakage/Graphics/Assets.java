package GamePakage.Graphics;

import java.awt.image.BufferedImage;
/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage playerSprite;
    public static BufferedImage toolsSprite;

    public static BufferedImage Brick;
    public static BufferedImage Brick2;
    public static BufferedImage BrickDown;
    public static BufferedImage BrickUp;
    public static BufferedImage BrickUp2;
    public static BufferedImage Block;
    public static BufferedImage BrickGold;
    public static BufferedImage BrickGoldBig;
    public static BufferedImage BrickTop;
    public static BufferedImage BrickTop2;

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
        toolsSprite =ImageLoader.LoadImage("/ToolsSprite.png");
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        Brick=toolsSprite.getSubimage(0*16,2*16,16,16);
        Brick2=toolsSprite.getSubimage(1*16,2*16,16,16);
        BrickDown=toolsSprite.getSubimage(2*16,2*16,16,16);
        BrickUp=toolsSprite.getSubimage(3*16,2*16,16,16);
        BrickUp2=toolsSprite.getSubimage(4*16,2*16,16,16);
        BrickGold=toolsSprite.getSubimage(0*16,3*16,16,16);
        BrickGoldBig=toolsSprite.getSubimage(1*16,3*16,16,16);
        Block=toolsSprite.getSubimage(2*16,3*16,16,16);
        BrickTop=toolsSprite.getSubimage(3*16,3*16,16,16);
        BrickTop2=toolsSprite.getSubimage(4*16,3*16,16,16);


        bgWall=sheet.crop(2,198,32,32);
        BgWallRock=sheet.crop(1162,163,32,32);
        BgWallHoll=sheet.crop(1162,127,32,32);
    }
}

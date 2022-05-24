package GamePakage.Graphics;

import sun.security.provider.ConfigFile;

import java.awt.image.BufferedImage;
/*! \class public class Assets
    \brief Clasa incarca fiecare element grafic necesar jocului.

    Game assets include tot ce este folosit intr-un joc: imagini, sunete, harti etc.
 */
public class Assets {
    /// Referinte catre elementele grafice (dale) utilizate in joc.
    public static BufferedImage playerSprite;
    public static BufferedImage toolsSprite;
    public static BufferedImage explosionSprite;
    public static BufferedImage EnemySprite;

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

    public static BufferedImage Ladder;
    public static BufferedImage LadderTop;
    public static BufferedImage Spikes;

    public static BufferedImage Exit;
    public static BufferedImage Entrance;
    public static BufferedImage MsgSign;

    public static BufferedImage bgWall;
    public static BufferedImage BgWallRock;
    public static BufferedImage BgWallHoll;
    public static BufferedImage LvlIcon;
    public static BufferedImage HeartIcon;
    public static BufferedImage BombIcon;
    public static BufferedImage RopeIcon;
    public static BufferedImage DollarIcon;
    public static BufferedImage BigGold;
    public static BufferedImage Gold;
    public static BufferedImage SmallGold;
    public static BufferedImage TinyGold;
    public static BufferedImage Ruby;
    public static BufferedImage RubyBig;
    public static BufferedImage Sapphire;
    public static BufferedImage SapphireBig;


    /*! \fn public static void Init()
        \brief Functia initializaza referintele catre elementele grafice utilizate.

        Aceasta functie poate fi rescrisa astfel incat elementele grafice incarcate/utilizate
        sa fie parametrizate. Din acest motiv referintele nu sunt finale.
     */
    public static void Init() {
        /// Se creaza temporar un obiect SpriteSheet initializat prin intermediul clasei ImageLoader
        SpriteSheet sheet = new SpriteSheet(ImageLoader.LoadImage("/BGSprite.png"));

        playerSprite=ImageLoader.LoadImage("/PlayerSprite.png");
        toolsSprite =ImageLoader.LoadImage("/ToolsSprite.png");
        explosionSprite =ImageLoader.LoadImage("/ExplosionSprite.png");
        EnemySprite =ImageLoader.LoadImage("/EnemySprite.png");
        /// Se obtin subimaginile corespunzatoare elementelor necesare.
        Brick=toolsSprite.getSubimage(0,2*16,16,16);
        Brick2=toolsSprite.getSubimage(16,2*16,16,16);
        BrickDown=toolsSprite.getSubimage(2*16,2*16,16,16);
        BrickUp=toolsSprite.getSubimage(3*16,2*16,16,16);
        BrickUp2=toolsSprite.getSubimage(4*16,2*16,16,16);
        BrickGold=toolsSprite.getSubimage(0,3*16,16,16);
        BrickGoldBig=toolsSprite.getSubimage(16,3*16,16,16);
        Block=toolsSprite.getSubimage(2*16,3*16,16,16);
        BrickTop=toolsSprite.getSubimage(3*16,3*16,16,16);
        BrickTop2=toolsSprite.getSubimage(4*16,3*16,16,16);

        LadderTop=toolsSprite.getSubimage(0,4*16,16,16);
        Ladder=toolsSprite.getSubimage(16,4*16,16,16);

        Exit=toolsSprite.getSubimage(2*16,4*16,16,16);
        Entrance=toolsSprite.getSubimage(3*16,4*16,16,16);

        Spikes =toolsSprite.getSubimage(4*16,4*16,16,16);

        LvlIcon =toolsSprite.getSubimage(0,6*16,16,16);
        HeartIcon =toolsSprite.getSubimage(16,6*16,16,16);
        BombIcon =toolsSprite.getSubimage(4*16,6*16,16,16);
        RopeIcon =toolsSprite.getSubimage(3*16,6*16,16,16);
        DollarIcon =toolsSprite.getSubimage(2*16,6*16,16,16);

        MsgSign =toolsSprite.getSubimage(4*16,1*16,16,16);

        bgWall=sheet.crop(0,0,32,32);
        BgWallRock=sheet.crop(32,0,32,32);
        BgWallHoll=sheet.crop(2*32,0,32,32);

        BigGold=toolsSprite.getSubimage(0,5*16,16,16);
        Gold=toolsSprite.getSubimage(16,5*16,8,8);
        SmallGold=toolsSprite.getSubimage(2*16,5*16,8,8);
        TinyGold=toolsSprite.getSubimage(3*16,5*16,4,4);
        Ruby=toolsSprite.getSubimage(4*16+8,5*16,4,4);
        RubyBig=toolsSprite.getSubimage(4*16,5*16,8,8);
        Sapphire=toolsSprite.getSubimage(4*16+8,5*16+8,4,4);
        SapphireBig=toolsSprite.getSubimage(4*16,5*16+8,8,8);
    }
}

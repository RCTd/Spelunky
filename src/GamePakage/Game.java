package GamePakage;

import GamePakage.Entitys.GameEntity;
import GamePakage.Entitys.Player;
import GamePakage.GameWindow.GameWindow;
import GamePakage.Graphics.Assets;
import GamePakage.Map.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Game extends JPanel implements Runnable {
    public ArrayList<GameEntity> entityList;
    public ArrayList<Money> GoldList;
    public ArrayList<Money> GoldremoveList;
    public ArrayList<GameEntity> toolList;
    public ArrayList<GameEntity> removeList;
    public ArrayList<GameEntity> addList;
    public static final int MaxJumpHeight =16+5;//100
    public static final float TimeToMaxH = 0.2F;//0.2
    public static final float YAccel =MaxJumpHeight *2/(TimeToMaxH * TimeToMaxH);
    //public static final float MaxYVel=500;

    public static final float AccelTimeX=0.1F;
    public static float MaxXSpeed;//0.05  /0.05
    public static float XAccel=MaxXSpeed/AccelTimeX;

    public static long coyotetimer;

    private int camX,camY;
    private float camXf=1,camYf=1;
    private int offsetMaxX,offsetMaxY;
    private int offsetMinX,offsetMinY;
    public static GameTimer timer = GameTimer.getInstance();


    public PlayerKeyListener keys;
    private final int width;
    private final int height;
    private final GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean runState;   /*!< Flag ce starea firului de executie.*/
    private Player player;
    public final Map map;
    public HUD hud=new HUD();
    BufferedImage bimg;

    public Game(String title, int width, int height) {
        this.width=width;this.height=height;
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        //map=new Map(42,20);
        map=new Map(this);
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame() {
        wnd.BuildGameWindow();
        /// Se incarca toate elementele grafice (dale)
        Assets.Init();
        entityList=new ArrayList<>();
        GoldList=new ArrayList<>();
        GoldremoveList =new ArrayList<>();
        removeList=new ArrayList<>();
        toolList=new ArrayList<>();
        addList=new ArrayList<>();
        player=new Player(this);
        camX= player.getX()-width/2;
        camY= player.getY()-height/2;
        keys=new PlayerKeyListener();
        wnd.GetCanvas().addKeyListener(keys);

        //map.LoadTutorial();
        map.Level();
        player.setX(map.x);
        player.setY(map.y);
        offsetMaxX=map.width*16-width;
        offsetMaxY=map.height*16-height;
        offsetMinX=offsetMinY=0;
        bimg=new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        /// Initializeaza obiectul game
        InitGame();
        timer.StartTimer();
        //long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        //long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame =(double) 0.1F / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState) {
            /// Se obtine timpul curent
            timer.update();
            //curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            //if ((curentTime - oldTime) > timeFrame) {
            //System.out.println(timer.getDeltaTime());
            if(timer.getDeltaTime()>timeFrame){
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                timer.StartTimer();
                //oldTime = curentTime;
            }
        }
    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        if (!runState) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            /*!< Referinta catre thread-ul de update si draw al ferestrei*/
            Thread gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    /*public synchronized void StopGame() {
        if (runState) {
            /// Actualizare stare thread
            runState = false;
            /// Metoda join() arunca exceptii motiv pentru care trebuie incadrata intr-un block try - catch.
            try {
                /// Metoda join() pune un thread in asteptare panca cand un altul isi termina executie.
                /// Totusi, in situatia de fata efectul apelului este de oprire a threadului.
                gameThread.join();
            } catch (InterruptedException ex) {
                /// In situatia in care apare o exceptie pe ecran vor fi afisate informatii utile pentru depanare.
                ex.printStackTrace();
            }
        }
    }*/

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() {
        Collide();
        player.Update();
        if(player.trigFlags.Exit)
        {
            entityList.clear();
            GoldList.clear();
            map.Level();
            player.setX(map.x);
            player.setY(map.y);
            player.p0=map.y;
            offsetMaxX=map.width*16-width;
            offsetMaxY=map.height*16-height;
            offsetMinX=offsetMinY=0;
            hud.Level++;
        }
        for(GameEntity obj:entityList) {obj.Update();}
        for(GameEntity obj:toolList) {obj.Update();}
        camX= player.getX()-width/2;
        camY= player.getY()-height/2-player.PlayerTile.TILE_HEIGHT;
        //scamY=keys.flag[5]? camY-4*16:(keys.flag[2]?camY+4*16:camY);
        float dif= camX-camXf;

        float cameraSpeed = 0.05F;
        if(dif!=0) {camXf+=dif* cameraSpeed;}
        dif=camY-camYf;
        if(dif!=0) {camYf+=dif* cameraSpeed;}
        camX=(int)camXf;
        camY=(int)camYf;
        if(camX>offsetMaxX)
            camX= offsetMaxX;
        else
            if (camX<offsetMinX)
                camX=offsetMinX;
        if (camY>offsetMaxY)
            camY=offsetMaxY;
        else
            if (camY<offsetMinY)
                camY=offsetMinY;

        for (GameEntity obj:removeList)
        {
            entityList.remove(obj);
            toolList.remove(obj);
        }
        for (Money obj: GoldremoveList){
           GoldList.remove(obj);
        }
        GoldremoveList.clear();
        removeList.clear();
        entityList.addAll(addList);
        addList.clear();
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw() {
        /// Returnez bufferStrategy pentru canvasul existent
        BufferStrategy bs = wnd.GetCanvas().getBufferStrategy();
        /// Verific daca buffer strategy a fost construit sau nu
        if (bs == null) {
            /// Se executa doar la primul apel al metodei Draw()
            try {
                /// Se construieste tripul buffer
                wnd.GetCanvas().createBufferStrategy(3);
                return;
            } catch (Exception e) {
                /// Afisez informatii despre problema aparuta pentru depanare.
                e.printStackTrace();
            }
        }
        /// Se obtine contextul grafic curent in care se poate desena.
        assert bs != null;
        Graphics f = bs.getDrawGraphics();
        Graphics g=bimg.getGraphics();

        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());
        g.translate(-camX,-camY);
        map.Draw(g);
        for(GameEntity obj:entityList) {obj.Draw(g);}
        for(GameEntity obj:toolList) {obj.Draw(g);}
        for (Money obj:GoldList) {obj.Draw(g);}
        player.Draw(g);
        g.translate(camX,camY);
        hud.Draw(g);
        f.drawImage(bimg,0,0,wnd.GetWndWidth(),wnd.GetWndHeight(),null);
        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    private void Collide()
    {
        player.Collide();
        for(GameEntity obj:entityList) {obj.Collide();}
        for(GameEntity obj:toolList) {obj.Collide();}
    }

    public double getDeltaTime() {
        return GameTimer.getInstance().getDeltaTime();
    }

    public Player getPlayer() {
        return player;
    }
}


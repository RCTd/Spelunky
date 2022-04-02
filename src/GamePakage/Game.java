package GamePakage;

import GamePakage.GameWindow.GameWindow;
import GamePakage.Graphics.Assets;
import GamePakage.Tiles.Map;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

public class Game extends JPanel implements Runnable {
    private boolean[] flag;
    private BufferedImage result;
    private int t,width,height;
    private GameWindow wnd;        /*!< Fereastra in care se va desena tabla jocului*/
    private boolean runState;   /*!< Flag ce starea firului de executie.*/
    private Thread gameThread; /*!< Referinta catre thread-ul de update si draw al ferestrei*/
    private BufferStrategy bs;
    private Graphics g;
    private Player player;
    private Map map;

    public static int HEIGHT() {return 20*16;}

    public static int WIDTH() {
        return 42*16;
    }

    public Game(String title, int width, int height) {
        this.width=width ;this.height=height;
        /// Obiectul GameWindow este creat insa fereastra nu este construita
        /// Acest lucru va fi realizat in metoda init() prin apelul
        /// functiei BuildGameWindow();
        wnd = new GameWindow(title, width, height);
        /// Resetarea flagului runState ce indica starea firului de executie (started/stoped)
        runState = false;
        t = 0;
        flag = new boolean[5];
        map=new Map(42,20);
    }

    /*! \fn private void init()
        \brief  Metoda construieste fereastra jocului, initializeaza aseturile, listenerul de tastatura etc.

        Fereastra jocului va fi construita prin apelul functiei BuildGameWindow();
        Sunt construite elementele grafice (assets): dale, player, elemente active si pasive.

     */
    private void InitGame() {
        wnd = new GameWindow("Schelet Proiect PAOO", width ,height);
        /// Este construita fereastra grafica.
        wnd.BuildGameWindow();
        /// Se incarca toate elementele grafice (dale)
        Assets.Init();
        player=new Player();
        //player=new PlayerTile(1);
        wnd.GetCanvas().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    flag[2] = true;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    flag[3] = true;
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    flag[0] = true;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    flag[1] = true;
                if(e.getKeyCode()==KeyEvent.VK_SHIFT)
                    flag[4]=true;
            }
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                    flag[0] = false;
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                    flag[1] = false;
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                    flag[2] = false;
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                    flag[3] = false;
                if(e.getKeyCode()==KeyEvent.VK_SHIFT)
                    flag[4]=false;
            }
        });

        result = new BufferedImage(
                width, height, //work these out
                BufferedImage.TYPE_INT_RGB);

        Graphics g = result.getGraphics();
        for (int i = 0; i <= width/64; i++) {
            for (int j = 0; j <= height/64; j++) {
                g.drawImage(Assets.bgWall,i*64,j*64,64,64,null);
            }
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallRock,(int)(Math.random()*width),(int)(Math.random()*height),50,50,null );
        }
        for (int i = 0; i < 6; i++) {
            g.drawImage(Assets.BgWallHoll,(int)(Math.random()*width),(int)(Math.random()*height),50,50,null );
        }

        map.LoadTutorial();
    }

    /*! \fn public void run()
        \brief Functia ce va rula in thread-ul creat.

        Aceasta functie va actualiza starea jocului si va redesena tabla de joc (va actualiza fereastra grafica)
     */
    public void run() {
        /// Initializeaza obiectul game
        InitGame();
        long oldTime = System.nanoTime();   /*!< Retine timpul in nanosecunde aferent frame-ului anterior.*/
        long curentTime;                    /*!< Retine timpul curent de executie.*/

        /// Apelul functiilor Update() & Draw() trebuie realizat la fiecare 16.7 ms
        /// sau mai bine spus de 60 ori pe secunda.

        final int framesPerSecond = 60; /*!< Constanta intreaga initializata cu numarul de frame-uri pe secunda.*/
        final double timeFrame = 10000000 / framesPerSecond; /*!< Durata unui frame in nanosecunde.*/

        /// Atat timp timp cat threadul este pornit Update() & Draw()
        while (runState == true) {
            /// Se obtine timpul curent
            curentTime = System.nanoTime();
            /// Daca diferenta de timp dintre curentTime si oldTime mai mare decat 16.6 ms
            if ((curentTime - oldTime) > timeFrame) {
                /// Actualizeaza pozitiile elementelor
                Update();
                /// Deseneaza elementele grafica in fereastra.
                Draw();
                oldTime = curentTime;
            }
        }

    }

    /*! \fn public synchronized void start()
        \brief Creaza si starteaza firul separat de executie (thread).

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StartGame() {
        if (runState == false) {
            /// Se actualizeaza flagul de stare a threadului
            runState = true;
            /// Se construieste threadul avand ca parametru obiectul Game. De retinut faptul ca Game class
            /// implementeaza interfata Runnable. Threadul creat va executa functia run() suprascrisa in clasa Game.
            gameThread = new Thread(this);
            /// Threadul creat este lansat in executie (va executa metoda run())
            gameThread.start();
        } else {
            /// Thread-ul este creat si pornit deja
            return;
        }
    }

    /*! \fn public synchronized void stop()
        \brief Opreste executie thread-ului.

        Metoda trebuie sa fie declarata synchronized pentru ca apelul acesteia sa fie semaforizat.
     */
    public synchronized void StopGame() {
        if (runState == true) {
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
        } else {
            /// Thread-ul este oprit deja.
            return;
        }
    }

    /*! \fn private void Update()
        \brief Actualizeaza starea elementelor din joc.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Update() {
        t += 1;
        Collide();
        player.Update(flag);
    }

    /*! \fn private void Draw()
        \brief Deseneaza elementele grafice in fereastra coresponzator starilor actualizate ale elementelor.

        Metoda este declarata privat deoarece trebuie apelata doar in metoda run()
     */
    private void Draw() {
        /// Returnez bufferStrategy pentru canvasul existent
        bs = wnd.GetCanvas().getBufferStrategy();
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
        g = bs.getDrawGraphics();
        /// Se sterge ce era
        g.clearRect(0, 0, wnd.GetWndWidth(), wnd.GetWndHeight());

        g.drawImage(result,0,0,null);
        map.Draw(g);
        player.Draw(g);

        //g.drawRect(1 * Tile.TILE_WIDTH, 1 * Tile.TILE_HEIGHT, Tile.TILE_WIDTH, Tile.TILE_HEIGHT);
        //g.drawRect(200,Game.HEIGHT()-60-36,300,36);

        // end operatie de desenare
        /// Se afiseaza pe ecran
        bs.show();

        /// Elibereaza resursele de memorie aferente contextului grafic curent (zonele de memorie ocupate de
        /// elementele grafice ce au fost desenate pe canvas).
        g.dispose();
    }

    public void Collide()
    {
        int x= player.getX()/16;
        int y= (player.getY()-3)/16+1;
        System.out.print("y= "+y+"\t");
        //if(y>1)
            player.IsOnGround= map.matrix[y][x] == 184;
    }
}


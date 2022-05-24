package GamePakage;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private final PlayerKeyListener keys;
    private final Game game;
    private int Selection =0;
    private boolean Music = true;
    private ArrayList<String> menu;

    public Menu(PlayerKeyListener keys, Game game) {
        this.keys = keys;
        this.game = game;
    }

    public void Update() {
        if(keys.isUp()){
            Selection--;
            if(Selection<0){
                Selection=menu.size()-1;
            }
        }
        if(keys.isDown()){
            Selection++;
            if(Selection>=menu.size()){
                Selection=0;
            }
        }
        if(keys.isEnter()){
            if(Selection==0){
                Music=!Music;
            }
            /*if(Selection==1){
                //game.setFullscreen(!game.isFullscreen());
            }*/
            if(Selection==2){
                game.setGameOver(true);
                game.menu=false;
            }
            if(Selection==3){
                System.exit(0);
            }
        }
        if(keys.isEscape()){
            game.menu=false;
            GameTimer.getInstance().StartTimer();
        }
    }

    public void Draw(){
        BufferStrategy bs = game.getBufferStrategy();
        Graphics g= bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWndWidth(), game.getWndHeight());
        g.setColor(Color.WHITE);
        Font fnt;
        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, new File("rsc/8-bit-hud.TTF")).deriveFont(20F);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        g.setFont(fnt);
        menu = new ArrayList<>();
        menu.add( "Music <" + (Music ? "On" : "Off")+">");
        //menu.add("Fullscreen <" + (game.isFullscreen() ? "On" : "Off")+">");
        menu.add("Die!");
        menu.add("Quit");
        for(int i=0;i<menu.size();i++){
            if(i==Selection){
                g.setColor(Color.YELLOW);
            }
            else{
                g.setColor(Color.WHITE);
            }
            }
        bs.show();
    }
}

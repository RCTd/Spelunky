package GamePakage.GUI;

import GamePakage.Game;
import GamePakage.GameTools.GameTimer;
import GamePakage.GameTools.PlayerKeyListener;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Menu {
    private final PlayerKeyListener keys;
    private final Game game;
    private int Selection =0;
    private boolean Music = true,releseedup=true,releseeddown=true,relesedEsc=false,relesedEnter=true;
    private ArrayList<String> menu;

    public Menu(PlayerKeyListener keys, Game game) {
        this.keys = keys;
        this.game = game;
    }

    public void Update() {
        if(keys.isUp()&&releseedup){
            Selection--;
            if(Selection<0){
                Selection=menu.size()-1;
            }
        }
        releseedup=!keys.isUp();
        if(keys.isDown()&&releseeddown){
            Selection++;
            if(Selection>=menu.size()){
                Selection=0;
            }
        }
        releseeddown=!keys.isDown();
        if(keys.isEnter()&&relesedEnter){
            if(Selection==0){
                game.menu=false;
                GameTimer.getInstance().StartTimer();
            }
            else if(Selection==1){
                Music=!Music;
                game.db.UpdateSettings(Music);
            }
            else if(Selection==2){
                game.setGameOver(true);
                game.db.UpdateScore(Integer.parseInt(game.getScore()));
                game.menu=false;
            }
            else if(Selection==3){
                System.exit(0);
            }
        }
        relesedEnter=!keys.isEnter();
        if(keys.isEscape()&&relesedEsc){
            game.menu=false;
            GameTimer.getInstance().StartTimer();
        }
        relesedEsc=!keys.isEscape();
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
        menu.add("Resume");
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
        g.drawString(menu.get(i), game.getWndWidth()/2-g.getFontMetrics().stringWidth(menu.get(i))/2, game.getWndHeight()/2-menu.size()*g.getFontMetrics().getHeight()/2+i*g.getFontMetrics().getHeight());
        }
        bs.show();
    }
}

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
    private boolean Music, releasedUp =true, releasedDown =true, releasedEsc =false, releasedEnter =true;
    private ArrayList<String> menu;

    public Menu(PlayerKeyListener keys, Game game) {
        this.keys = keys;
        this.game = game;
        Music=game.db.GetSettings();
        if(Music){
            game.sound.loop();
        }
    }

    public void Update() {
        if(keys.isUp()&& releasedUp){
            Selection--;
            if(Selection<0){
                Selection=menu.size()-1;
            }
        }
        releasedUp =!keys.isUp();
        if(keys.isDown()&& releasedDown){
            Selection++;
            if(Selection>=menu.size()){
                Selection=0;
            }
        }
        releasedDown =!keys.isDown();
        if(keys.isEnter()&& releasedEnter){
            if(Selection==0){
                game.menu=false;
                GameTimer.getInstance().StartTimer();
            }
            else if(Selection==1){
                Music=!Music;
                game.db.UpdateSettings(Music);
                if(Music){
                    game.sound.loop();
                }
                else{
                    game.sound.stop();
                }
            }
            else if(Selection==2){
                game.db.Clear();
            }
            else if(Selection==3){
                game.newLevel(true);
                GameTimer.getInstance().StartTimer();
                game.menu=false;
            }
            else if(Selection==4){
                game.setGameOver(true);
                if(Integer.parseInt(game.getScore())>0)
                    game.db.UpdateScore(Integer.parseInt(game.getScore()));
                game.menu=false;
            }
            else if(Selection==5){
                game.db.Close();
                System.exit(0);
            }
        }
        releasedEnter =!keys.isEnter();
        if(keys.isEscape()&& releasedEsc){
            game.menu=false;
            GameTimer.getInstance().StartTimer();
        }
        releasedEsc =!keys.isEscape();
    }

    public void Draw(){
        BufferStrategy bs = game.getBufferStrategy();
        Graphics g= bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWndWidth(), game.getWndHeight());
        g.setColor(Color.WHITE);
        Font fnt;
        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, new File("rsc/Fonts/8-bit-hud.TTF")).deriveFont(20F);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        g.setFont(fnt);
        menu = new ArrayList<>();
        menu.add("Resume");
        menu.add( "Music <" + (Music ? "On" : "Off")+">");
        menu.add("Reset Highscore");
        menu.add("Tutorial");
        menu.add("Die!");
        menu.add("Quit");
        g.translate(0,-menu.size()*g.getFontMetrics().getHeight());
        g.drawImage(Assets.Title, game.getWndWidth()/2-Assets.Title.getWidth()*3/2, game.getWndHeight()/2-Assets.Title.getHeight()*3,128*3,16*3, null);
        g.translate(0,menu.size()*g.getFontMetrics().getHeight());
        int maxWidth = 0;
        for(String s : menu){
            if(g.getFontMetrics().stringWidth(s)>maxWidth){
                maxWidth = g.getFontMetrics().stringWidth(s);
            }
        }
        g.translate(-maxWidth/2, Assets.Title.getHeight()/2);

        for(int i=0;i<menu.size();i++){
            if(i==Selection){
                g.setColor(Color.YELLOW);
            }
            else{
                g.setColor(Color.WHITE);
            }
            g.drawString(menu.get(i), game.getWndWidth()/2-g.getFontMetrics().stringWidth(menu.get(i))/2,
                                    game.getWndHeight()/2-menu.size()*g.getFontMetrics().getHeight()/2+i*g.getFontMetrics().getHeight());
        }
        g.translate(maxWidth/2, -Assets.Title.getHeight()/2);
        ArrayList<String> scoreBord=game.db.Show();
        for(String s : scoreBord){
            if(g.getFontMetrics().stringWidth(s)>maxWidth){
                maxWidth = g.getFontMetrics().stringWidth(s);
            }
        }
        g.translate(maxWidth/2, Assets.Title.getHeight()/2);
        g.setColor(Color.WHITE);
        g.drawString("Highscore", game.getWndWidth()/2-g.getFontMetrics().stringWidth("Highscore")/2,
                        game.getWndHeight()/2-menu.size()*g.getFontMetrics().getHeight()/2-g.getFontMetrics().getHeight());
        for(int i=0;i<scoreBord.size()&&i<10;i++){
            g.drawString(scoreBord.get(i), game.getWndWidth()/2-g.getFontMetrics().stringWidth(scoreBord.get(i))/2,
                                        game.getWndHeight()/2-menu.size()*g.getFontMetrics().getHeight()/2+i*g.getFontMetrics().getHeight()+g.getFontMetrics().getHeight());
        }
        bs.show();
    }
}

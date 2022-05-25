package GamePakage.GUI;

import GamePakage.Game;
import GamePakage.GameTools.GameTimer;
import GamePakage.GameTools.PlayerKeyListener;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameOver {
    private final Game game;
    private final PlayerKeyListener key;
    private boolean relesed=false;

    public GameOver(PlayerKeyListener key,Game game) {
        this.game = game;
        this.key = key;
        game.db.UpdateScore(Integer.parseInt(game.getScore()));
    }

    public void Update(){
        if(key.isEnter()&&relesed){
            GameTimer.getInstance().StartTimer();
            game.gameOver = false;
            game.hud.reset();
            game.newLevel(false);
        }
        relesed=!key.isEnter();
        if(key.isEscape()){
            game.db.Close();
            System.exit(0);
        }
    }

    public void Draw() {
        BufferStrategy bs = game.getBufferStrategy();
        Graphics g= bs.getDrawGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, game.getWndWidth(), game.getWndHeight());
        g.setColor(Color.YELLOW);
        Font fnt;
        try {
            fnt = Font.createFont(Font.TRUETYPE_FONT, new File("rsc/Fonts/8-bit-hud.TTF")).deriveFont(20F);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
        g.setFont(fnt);
        ArrayList<String> text = new ArrayList<>();
        text.add("Game Over");
        text.add("Score: " + game.getScore());
        text.add("Press Enter to play again");
        text.add("Press Escape to exit");

        for (int i = 0; i < text.size(); i++) {
            g.drawString(text.get(i),game.getWndWidth()/2-g.getFontMetrics().stringWidth(text.get(i))/2,game.getWndHeight()/2-text.size()*g.getFontMetrics().getHeight()/2+i*g.getFontMetrics().getHeight());
        }
        g.dispose();
        bs.show();
    }
}

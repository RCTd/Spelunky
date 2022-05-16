package GamePakage.Entitys;

import GamePakage.Game;
import GamePakage.Graphics.Assets;

import java.awt.*;

import static GamePakage.Game.timer;

public class Explosion implements GameEntity{
    private int x,y;
    private float frame =0;
    private Game game;

    public Explosion(int x,int y,Game game)
    {
        this.x=x-20;
        this.y=y-20;
        this.game=game;
    }

    @Override
    public void Update() {
        if(frame>9)
        {
            for (int i = -16; i <=16; i+=16) {
                for (int j = -16; j <=16; j+=16) {
                    game.map.tileMap[((int) y + i+20) / 16][((int) x+j+20) / 16].Destroy();
                }
            }
           game.removeList.add(this);
        }else {
            frame = frame + 48 * timer.getDeltaTime();
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.drawImage(Assets.explosionSprite.getSubimage((int)frame *48,0,48,48),x,y,48,48,null);
    }

    @Override
    public void Collide() {

    }
}

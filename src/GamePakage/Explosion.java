package GamePakage;

import java.awt.*;

import static GamePakage.Game.timer;

public class Explosion implements GameEntity{
    private final int x;
    private final int y;
    private float frame =0;
    private final Game game;

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
                    game.map.tileMap[( y + i+20) / 16][(x+j+20) / 16].Destroy();
                }
            }
            for (GameEntity object : game.entityList) {
                if(object.getX()>x-32 && object.getX()<x+32 && object.getY()>y-32 && object.getY()<y+32) {
                    game.removeList.add(object);
                }
            }
        }else {
            if(game.getPlayer().getX()>x-16 && game.getPlayer().getX()<x+16  && game.getPlayer().getY()>y-30 && game.getPlayer().getY()<y+30) {
                game.getPlayer().TakeDamage(4);
            }
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

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }
}

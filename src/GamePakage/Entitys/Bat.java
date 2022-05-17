package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;

import java.awt.*;

public class Bat implements GameEntity{
        private float x;
        private float y;
        private float oldX;
        private float oldY;
        private Game game;
        private float batspeed=120;
        private Flags triflag;

    public Bat( float x, float y,Game game){
        this.game = game;
        this.x = (int)x;
        this.y = (int)y;
        triflag = new Flags();
    }

    @Override
    public void Update() {
        Target();
        //collision detection with player
        /*if(game.player.getX()>x && game.player.getX()<x+16 && game.player.getY()>y && game.player.getY()<y+16){
            game.player.setHealth(game.player.getHealth()-1);
        }*/

    }

    private void Target()
    {
        oldX = x;
        oldY = y;
        double deltatime = game.getDeltaTime();
        float targetX = game.player.getX();
        float targetY = game.player.getY();
        float deltaX = targetX - x;
        float deltaY = targetY - y;
        float distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        float speed = (float) (deltatime * batspeed);
        if (distance < speed) {
            x = targetX;
            y = targetY;
        }
        else {
            x += deltaX / distance * speed;
            y += deltaY / distance * speed;
        }
    }

    @Override
    public void Draw(Graphics g) {
        g.setColor(Color.red);
        g.fillRect((int) x, (int) y, 16, 16);
    }

    @Override
    public void Collide() {
        triflag.Collide((int)x, (int) y,16,16,game);
        if(triflag.IsOnGround||triflag.HeadHit) {
            y += oldY-y;
        }
        if(triflag.wallLeft||triflag.wallRight){
            x += oldX-x;
        }
    }
}

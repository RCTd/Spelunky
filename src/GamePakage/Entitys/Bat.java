package GamePakage.Entitys;

import GamePakage.Flags;
import GamePakage.Game;
import GamePakage.Tiles.BatTile;

import java.awt.*;

public class Bat implements GameEntity{
    private float x, y,oldX,oldY,targetX,targetY,deltaX,deltaY,distance;
    private final Game game;
    private final Flags triflag;
    private final BatTile batTile=new BatTile(0);

    public Bat( float x, float y,Game game){
        this.game = game;
        this.x = (int)x;
        this.y = (int)y;
        oldX=this.x;
        oldY=this.y;
        triflag = new Flags();
        triflag.HeadHit=true;
    }

    @Override
    public void Update() {
        targetX = game.player.getX();
        targetY = game.player.getY();
        deltaX = targetX - x;
        deltaY = targetY - y;
        distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if(batTile.getState()==0) {
            if (distance < 100&&deltaY>0||!triflag.HeadHit) {
                batTile.setState(1);
            }
        }else {
            Target();
        }
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
        float batspeed = 120;
        float speed = (float) (deltatime * batspeed);
        if (distance < speed) {
            x = targetX;
            y = targetY;
        }
        else {
            x += deltaX / distance * speed;
            y += deltaY / distance * speed;
        }
        if(oldX-x>0){
            batTile.setState(1);
        }else
            batTile.setState(2);

    }

    @Override
    public void Draw(Graphics g) {
        batTile.Draw(g, (int) x, (int) y);
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

package GamePakage.Entitys;

import GamePakage.GameTools.Flags;
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
        this.y = (int)y-1;
        oldX=this.x;
        oldY=this.y;
        triflag = new Flags();
        triflag.HeadHit=true;
    }

    @Override
    public void Update() {
        targetX = game.getPlayer().getX();
        targetY = game.getPlayer().getY();
        deltaX = targetX - x;
        deltaY = targetY - y;
        distance = (float) Math.sqrt(deltaX * deltaX + deltaY * deltaY);
        if(batTile.getState()==0) {
            if (distance < 100&&deltaY>0||!triflag.HeadHit) {
                batTile.setState(1);
                y+=3;
            }
        }else {
            Target();
        }
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
        triflag.Collide((int)x+1, (int) y, 13, 13,  game);
        if(batTile.getState()!=0) {
            if (triflag.IsOnGround || triflag.HeadHit) {
                y += oldY - y;
            }
            if (triflag.wallLeft || triflag.wallRight) {
                x += oldX - x;
            }
        }
        //collision detection with getPlayer()
        if(game.getPlayer().getX()>x-13 && game.getPlayer().getX()<x+13 && game.getPlayer().getY()<y+16){
            if(game.getPlayer().getY()>y-13 )
                game.getPlayer().TakeDamage(1);
            else
            if(game.getPlayer().getY()>y-16) {
                game.removeList.add(this);
                game.getPlayer().Jump();
            }
        }
        for (GameEntity entity : game.toolList) {
            if(entity.getX()>x-13 && entity.getX()<x+13 && entity.getY()<y+16&&entity.getY()>y-16){
                game.removeList.add(this);
            }
        }
    }

    @Override
    public int getX() {
        return (int)x;
    }

    @Override
    public int getY() {
        return (int)y;
    }
}

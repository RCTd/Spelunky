package GamePakage.Tiles;
import GamePakage.Graphics.Assets;
import GamePakage.Tiles.PlayerState.StandState;

import java.awt.*;
import java.awt.image.BufferedImage;

import static GamePakage.Game.size;

public class PlayerTile extends Tile {
    public State AnimationState=new StandState();
    public int direction=-1;


    public PlayerTile(int id) {
        super(Assets.playerSprite, id,16,16);
    }

    @Override
    public void Draw(Graphics g, int x, int y) {
        //BufferedImage crntimg = img.getSubimage(frame * 16, state * 16, TILE_WIDTH, TILE_WIDTH);
        AnimationState.SetDirection(direction);
        AnimationState.Update();
        //System.out.println(AnimationState.with);
        g.drawRect(x*size,y*size,AnimationState.with*size,AnimationState.height*size);
        g.drawImage(AnimationState.Image,
                        x*size,y*size,(x+AnimationState.with)*size,(y+AnimationState.height)*size,
                        (1+direction)*AnimationState.with/2,0,(1-direction)*AnimationState.height/2,AnimationState.height, null);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}

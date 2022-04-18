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
        AnimationState.Update();                                                                                                //0,0,16,16
                                                                                                        //16,0,0,16
        g.drawImage(AnimationState.Image,
                        x*size,y*size,(x+TILE_WIDTH)*size,(y+TILE_HEIGHT)*size,
                        (1+direction)*TILE_WIDTH/2,0,(1-direction)*TILE_HEIGHT/2,TILE_HEIGHT, null);
    }

    @Override
    public boolean IsSolid() {
        return true;
    }
}

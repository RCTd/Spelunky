package GamePakage.Tiles;

import GamePakage.Flags;
import GamePakage.Graphics.Assets;

import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public abstract class State {
    public int state=0, TotalFrames=0,with=16,height=16,direction=1;
    public float frame=0;
    public BufferedImage Image;
    public void SetDirection(int direction)
    {
        this.direction=direction;
    }
    public void Update(){
        frame=(frame>=TotalFrames)?0:(frame+16 * timer.getDeltaTime());
        Image= Assets.playerSprite.getSubimage((int)frame * with, state * 16, with, height);
    }
    public State Handle(Flags trigFlags)
    {
        return null;
    }
}

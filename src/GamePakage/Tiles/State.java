package GamePakage.Tiles;

import GamePakage.Graphics.Assets;

import java.awt.image.BufferedImage;

import static GamePakage.Game.timer;

public abstract class State {
    public int state=0, TotalFrames ;
    public float frame;
    public BufferedImage Image;
    public void Update(){
        frame=(frame>=TotalFrames)?0:(frame+16 * timer.getDeltaTime());
        Image= Assets.playerSprite.getSubimage((int)frame * 16, state * 16, 16, 16);
    }
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack)
    {
        return null;
    }
}

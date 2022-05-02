package GamePakage.Tiles.PlayerState;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.State;

import static GamePakage.Game.timer;

public class ClimbRope extends State {
    public ClimbRope()
    {
        state=12;
        TotalFrames=6;
        frame=0;
    }

    @Override
    public void Update() {
        frame=(frame>=TotalFrames)?0:(frame+16 * timer.getDeltaTime());
        Image= Assets.playerSprite.getSubimage((int)frame * with, state * 16, with, height);
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang, boolean TooHigh, boolean Climbing) {
        if(!IsOnGround)
            return new FallState();
        return this;
    }
}

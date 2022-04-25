package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class HangState extends State {
    public HangState()
    {
        state=8;
        TotalFrames=0;
        frame=0;
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang) {
        if(!IsOnGround)
            return new FallState();
        return this;
    }
}
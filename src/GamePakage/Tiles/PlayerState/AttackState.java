package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class AttackState extends State {
    public AttackState()
    {
        state=5;
        TotalFrames=11;
        frame=0;
        with=16;
        height=16;
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang, boolean TooHigh) {
        if(TooHigh)
            return new  StunState();
        if((int)frame==TotalFrames)
            return new StandState();
        return this;
    }
}

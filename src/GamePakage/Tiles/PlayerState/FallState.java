package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class FallState extends State {
    public FallState()
    {
        state=1;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang, boolean TooHigh) {
        if(TooHigh)
            return new StunState();
        if(OnAttack)
            return new AttackState();
        if(Hang) {
            return new HangState();
        }
        if(IsOnGround)
            return new StandState();
        return this;
    }
}

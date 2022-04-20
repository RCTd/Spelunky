package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class DuckState extends State {
    public DuckState()
    {
        state=7;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean CanHangLeft, boolean CanHangRight) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves&& Duck)
            return new DuckMoveState();
        if(!Duck)
            return new StandState();
        return this;
    }
}

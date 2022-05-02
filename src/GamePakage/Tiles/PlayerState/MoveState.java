package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class MoveState extends State {
    public MoveState()
    {
        state=2;
        TotalFrames=5;
        frame=0;
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang, boolean TooHigh, boolean Climbing) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves&& Duck)
            return new DuckMoveState();
        if(Moves&&LookUp)
            return new LookUpMoveState();
        if(Climbing)
            return new ClimbRope();
        if(!Moves)
            return new StandState();
        return this;
    }
}

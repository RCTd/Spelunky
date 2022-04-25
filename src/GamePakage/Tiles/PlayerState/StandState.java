package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class StandState extends State {
    public StandState()
    {
        state=0;
        TotalFrames =0;
        frame=0;
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang) {

        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves && Duck)
            return new DuckMoveState();
        if(Moves)
            return new MoveState();
        if(Duck)
            return new DuckState();
        if(LookUp)
            return new LookUpState();
        return this;

    }
}

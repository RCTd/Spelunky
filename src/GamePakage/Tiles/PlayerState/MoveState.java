package GamePakage.Tiles.PlayerState;

import GamePakage.GameTools.Flags;
import GamePakage.Tiles.State;

public class MoveState extends State {
    public MoveState()
    {
        state=2;
        TotalFrames=5;
        frame=0;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if(trigFlags.Moves&& trigFlags.Duck)
            return new DuckMoveState();
        if(trigFlags.Moves&&trigFlags.LookUp)
            return new LookUpMoveState();
        if(trigFlags.Climbing)
            return new ClimbRope();
        if(!trigFlags.Moves)
            return new StandState();
        return this;
    }
}

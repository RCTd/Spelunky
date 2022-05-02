package GamePakage.Tiles.PlayerState;

import GamePakage.Flags;
import GamePakage.Tiles.State;

public class LookUpMoveState extends State {
    public LookUpMoveState()
    {
        state=4;
        TotalFrames=5;
        frame=0;
    }
    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if(!trigFlags.Moves&& trigFlags.LookUp)
            return new LookUpState();
        if(trigFlags.Moves&&!trigFlags.LookUp)
            return new MoveState();
        return this;
    }
}

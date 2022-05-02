package GamePakage.Tiles.PlayerState;

import GamePakage.Flags;
import GamePakage.Tiles.State;

public class FallState extends State {
    public FallState()
    {
        state=1;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.TooHigh)
            return new StunState();
        if(trigFlags.Attack)
            return new AttackState();
        if(trigFlags.Hang) {
            return new HangState();
        }
        if(trigFlags.Climbing)
            return new ClimbRope();
        if(trigFlags.IsOnGround)
            return new StandState();
        return this;
    }
}

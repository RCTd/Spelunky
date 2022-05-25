package GamePakage;

public class StandState extends State {
    public StandState()
    {
        state=0;
        TotalFrames =0;
        frame=0;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.TooHigh)
            return new  StunState();
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if(trigFlags.Moves && trigFlags.Duck)
            return new DuckMoveState();
        if(trigFlags.Moves)
            return new MoveState();
        if(trigFlags.Climbing)
            return new ClimbRope();
        if(trigFlags.Duck)
            return new DuckState();
        if(trigFlags.LookUp)
            return new LookUpState();
        return this;

    }
}

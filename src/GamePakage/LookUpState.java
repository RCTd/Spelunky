package GamePakage;

public class LookUpState extends State {
    public LookUpState()
    {
        state=3;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if(trigFlags.Moves&& trigFlags.LookUp)
            return new LookUpMoveState();
        if(!trigFlags.LookUp)
            return new StandState();
        return this;
    }
}

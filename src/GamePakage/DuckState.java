package GamePakage;

public class DuckState extends State {
    public DuckState()
    {
        state=7;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if(trigFlags.Moves&&trigFlags.Duck)
            return new DuckMoveState();
        if(!trigFlags.Duck)
            return new StandState();
        return this;
    }
}

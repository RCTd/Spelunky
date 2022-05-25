package GamePakage;

public class AttackState extends State {
    public AttackState()
    {
        state=5;
        TotalFrames=11;
        frame=0;
        with=16;
        height=16;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.TooHigh)
            return new  StunState();
        if((int)frame==TotalFrames)
            return new StandState();
        return this;
    }
}

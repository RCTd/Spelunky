package GamePakage;

public class HangState extends State {
    public HangState()
    {
        state=8;
        TotalFrames=0;
        frame=0;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if(!trigFlags.IsOnGround||!trigFlags.Hang)
            return new FallState();
        return this;
    }
}
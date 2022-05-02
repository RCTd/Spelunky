package GamePakage.Tiles.PlayerState;

import GamePakage.Flags;
import GamePakage.Tiles.State;

public class StunState extends State {
    private int second;
    public StunState()
    {
        state=11;
        TotalFrames=5;
        frame=0;
        second=0;
        with=16;
        height=16;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if((int)frame==TotalFrames)
        {
            second++;
            frame=0;
            if(second==2)
                return new StandState();
        }
        return this;
    }
}

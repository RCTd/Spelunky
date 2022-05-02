package GamePakage.Tiles.PlayerState;

import GamePakage.Flags;
import GamePakage.Tiles.State;

public class HangMoveState extends State {
    public HangMoveState()
    {
        state=9;
        TotalFrames=8;
        frame=0;
        with=32;
        height=32;
    }

    @Override
    public State Handle(Flags trigFlags) {
        if(!trigFlags.IsOnGround)
            return new FallState();
        if((int)frame==TotalFrames)
            return new HangState();
        return this;
    }
}

package GamePakage.Tiles.PlayerState;

import GamePakage.Flags;
import GamePakage.Tiles.State;

public class DuckMoveState extends State {
    public DuckMoveState()
    {
        state=7;
        TotalFrames=9;
        frame=0;
        with=20;
    }
    @Override
    public State Handle(Flags trigFlags) {
        if(trigFlags.Attack)
            return new AttackState();
        if(!trigFlags.IsOnGround)
            return new FallState();
        if((trigFlags.OnEdgeLeft ^ trigFlags.OnEdgeRight)&&(trigFlags.OnEdgeLeft&&direction>0||trigFlags.OnEdgeRight&&direction<0))
                return new HangMoveState();
        if(trigFlags.Moves&&!trigFlags.Duck)
            return new MoveState();
        if(!trigFlags.Moves&& trigFlags.Duck)
            return new DuckState();
        return this;
    }
}

package GamePakage.Tiles.PlayerState;

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
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean Hang, boolean TooHigh) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if((OnEdgeLeft ^ OnEdgeRight)&&(OnEdgeLeft&&direction>0||OnEdgeRight&&direction<0))
                return new HangMoveState();
        if(Moves&&!Duck)
            return new MoveState();
        if(!Moves&& Duck)
            return new DuckState();
        return this;
    }
}

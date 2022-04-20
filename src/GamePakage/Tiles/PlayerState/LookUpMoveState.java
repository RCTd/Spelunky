package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class LookUpMoveState extends State {
    public LookUpMoveState()
    {
        state=4;
        TotalFrames=5;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack, boolean OnEdgeLeft, boolean OnEdgeRight, boolean CanHangLeft, boolean CanHangRight) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(!Moves&& LookUp)
            return new LookUpState();
        if(Moves&&!LookUp)
            return new MoveState();
        return this;
    }
}

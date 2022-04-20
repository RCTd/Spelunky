package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class LookUpState extends State {
    public LookUpState()
    {
        state=4;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves&& LookUp)
            return new LookUpMoveState();
        if(!LookUp)
            return new StandState();
        return this;
    }
}
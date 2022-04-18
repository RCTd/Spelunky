package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class DuckMoveState extends State {
    public DuckMoveState()
    {
        state=7;
        TotalFrames=9;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves&&!Duck)
            return new MoveState();
        if(!Moves&& Duck)
            return new DuckState();
        return this;
    }
}

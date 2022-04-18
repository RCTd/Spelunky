package GamePakage.Tiles.PlayerState;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.State;

public class MoveState extends State {
    public MoveState()
    {
        state=2;
        TotalFrames=5;
        frame=0;
    }

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack) {
        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves&& Duck)
            return new DuckMoveState();
        if(!Moves)
            return new StandState();
        return this;
    }
}

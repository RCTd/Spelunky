package GamePakage.Tiles.PlayerState;

import GamePakage.Tiles.State;

public class FallState extends State {
    public FallState()
    {
        state=1;
        TotalFrames=0;
        frame=0;
    }
    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack) {
        if(OnAttack)
            return new AttackState();
        if(IsOnGround)
            return new StandState();
        return this;
    }
}

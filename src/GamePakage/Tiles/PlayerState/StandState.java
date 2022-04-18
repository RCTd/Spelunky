package GamePakage.Tiles.PlayerState;

import GamePakage.Graphics.Assets;
import GamePakage.Tiles.State;

public class StandState extends State {
    public StandState()
    {
        state=0;
        TotalFrames =0;
        frame=0;
    }

    /*@Override
    public void Update() {
        *//*System.out.print(state);
        frame=(frame>=TotalFrames)?0:frame+1;
        Image= Assets.playerSprite.getSubimage(frame * 16, state * 16, 16, 16);*//*
    }*/

    @Override
    public State Handle(boolean Moves, boolean Duck, boolean IsOnGround, boolean LookUp, boolean OnAttack) {

        if(OnAttack)
            return new AttackState();
        if(!IsOnGround)
            return new FallState();
        if(Moves && Duck)
            return new DuckMoveState();
        if(Moves)
            return new MoveState();
        if(Duck)
            return new DuckState();
        return this;

    }
}

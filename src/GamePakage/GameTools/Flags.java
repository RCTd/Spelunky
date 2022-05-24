package GamePakage.GameTools;

import GamePakage.Game;

import static GamePakage.Game.coyotetimer;

public class Flags {
    public boolean IsOnGround=true, HeadHit =false,wallRight=false,wallLeft=false;
    public boolean Duck=false, LookUp=false,Moves=false,TooHigh=false,HasRope=true;
    public boolean OnEdgeLeft=false,OnEdgeRight=false,Hang=false,Exit=false;
    public boolean CanHangLeft =false, CanHangRight =false;
    public boolean Relesed=true,LongJump,Attack, OnRope =false,Climbing=false;
    public boolean HasBomb;

    public void Update(PlayerKeyListener key, int newState)
    {
        Climbing=(OnRope&& key.isUp())||(Climbing&&IsOnGround&&OnRope);
        Moves=false;
        Attack=key.isX();
        Duck= key.isDown();
        LookUp= key.isUp();
        Hang= newState== 8 || newState == 9;
    }
    public void Collide(int x, int y, int w, int h, Game game)
    {
        OnRope =game.map.tileMap[(y+4)/16][(x+2)/16].GetId()==6&&game.map.tileMap[(y+4)/16][(x+14)/16].GetId()==6;

        Exit=game.map.tileMap[(y+8)/16][(x+8)/16].GetId()==10;
        //int wall=184;
        CanHangLeft =!(game.map.tileMap[(y)/16][(x-1)/16].IsSolid()) && (game.map.tileMap[(y+4)/16][(x-1)/16].IsSolid());
        //left up || left down
        wallLeft=(game.map.tileMap[(y+6)/16][(x-1)/16].IsSolid())||(game.map.tileMap[(y+h-2)/16][(x-1)/16].IsSolid());

        CanHangRight =!(game.map.tileMap[(y)/16][(x+w+1)/16].IsSolid())&&(game.map.tileMap[(y+4)/16][(x+w+1)/16].IsSolid());
        //right up || right down
        wallRight=(game.map.tileMap[(y+6)/16][(x+w+1)/16].IsSolid())||(game.map.tileMap[(y+h-2)/16][(x+w+1)/16].IsSolid());

        //up left || up right
        HeadHit =(game.map.tileMap[(y-2)/16][(x+3)/16].IsSolid())||(game.map.tileMap[(y-2)/16][(x+w-3)/16].IsSolid());
        //down left

        OnEdgeLeft=game.map.tileMap[(y+h)/16][(x+3)/16].IsSolid();
        //down right
        OnEdgeRight=game.map.tileMap[(y+h)/16][(x+w-3)/16].IsSolid();

        boolean temp=(OnEdgeLeft)||(OnEdgeRight)|| (OnRope&& Climbing);

        if(IsOnGround!=temp) {
            coyotetimer = System.nanoTime();
        }
        if(!Hang|| !IsOnGround)
            IsOnGround= temp;
    }
}

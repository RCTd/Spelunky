package GamePakage;

public class Flags {
    public boolean IsOnGround=true, HeadHit =false,wallRight=false,wallLeft=false;
    public boolean Duck=false, LookUp=false,Moves=false,TooHigh=false,HasRope=true;
    public boolean OnEdgeLeft=false,OnEdgeRight=false,Hang=false,Exit=false;
    public boolean CanHangLeft =false, CanHangRight =false;
    public boolean Relesed=true,LongJump,Attack, OnRope =false,Climbing=false;

    public void Update(boolean[] flag,int newState)
    {
        Climbing=(OnRope&&flag[5])||(Climbing&&IsOnGround&&OnRope);
        Moves=false;
        Attack=flag[6];
        Duck= flag[2];
        LookUp= flag[5];
        Hang= newState== 8 || newState == 9;
        TooHigh=false;
    }
}

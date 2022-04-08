package GamePakage;

public class GameTimer {
    private static GameTimer single_instance = null;

    public static GameTimer getInstance()
    {
        if (single_instance == null)
            single_instance = new GameTimer();

        return single_instance;
    }

    private long oldTime;
    private float deltaTime;

    public void StartTimer()
    {
        oldTime=System.nanoTime();
    }

    public void update()
    {
        deltaTime=(float) (System.nanoTime()-oldTime)/1_000_000_000;
    }

    public float getDeltaTime()
    {
        return deltaTime;
    }
}

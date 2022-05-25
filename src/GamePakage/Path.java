package GamePakage;

import java.util.Random;

public class Path {
    public int[][]path;
    public int start,end;

    public Path()
    {
        path=new int[4][4];
        start= rand(0,3);
        path[0][start]=1;
        int i=start,j=0;
        int dir;
        while (j<4)
        {
            dir = rand(-2, 2);
            dir = Math.round(0.6F * dir);
            try {
                if (path[j][i + dir] == 0) {
                    i += dir;
                    path[j][i] = 1;
                } /*else {
                    j=down(path,j,i);
                }*/
            } catch (Exception e) {
                j=down(path,j,i);
            }
        }
        end=i;
    }
    private static int down(int[][] path,int j,int i)
    {
        j++;
        try {
            path[j][i] = 3;
            path[j - 1][i] = 2;
        }catch (Exception ignored){}
        return j;
    }

    public int rand(int min,int max)
    {
        return new Random().nextInt(max-min+1)+min;
    }
}

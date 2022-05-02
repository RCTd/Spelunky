package GamePakage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener {
    public boolean[] flag;

    public PlayerKeyListener()
    {
        flag = new boolean[8];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_Z)
            flag[0] = true;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            flag[1] = true;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            flag[2] = true;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            flag[3] = true;
        if (e.getKeyCode() == KeyEvent.VK_SHIFT)
            flag[4] = true;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            flag[5] = true;
        if (e.getKeyCode() == KeyEvent.VK_X)
            flag[6] = true;
        if(e.getKeyCode()==KeyEvent.VK_D)
            flag[7]=true;
    }
    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode()==KeyEvent.VK_Z)
            flag[0] = false;
        if (e.getKeyCode() == KeyEvent.VK_RIGHT)
            flag[1] = false;
        if (e.getKeyCode() == KeyEvent.VK_DOWN)
            flag[2] = false;
        if (e.getKeyCode() == KeyEvent.VK_LEFT)
            flag[3] = false;
        if(e.getKeyCode()==KeyEvent.VK_SHIFT)
            flag[4]=false;
        if (e.getKeyCode() == KeyEvent.VK_UP)
            flag[5] = false;
        if (e.getKeyCode() == KeyEvent.VK_X)
            flag[6] = false;
        if (e.getKeyCode() == KeyEvent.VK_D)
            flag[7] = false;
    }
}

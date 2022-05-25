package GamePakage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerKeyListener implements KeyListener {
    public boolean[] flag;
    KeyEvent e;

    public PlayerKeyListener()
    {
        flag = new boolean[11];
    }
    @Override
    public void keyTyped(KeyEvent e) {
    }
    @Override
    public void keyPressed(KeyEvent e) {
        this.e = e;
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
        if(e.getKeyCode()==KeyEvent.VK_C)
            flag[8]=true;
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            flag[9]=true;
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            flag[10] = true;
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
        if(e.getKeyCode()==KeyEvent.VK_C)
            flag[8]=false;
        if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
            flag[9]=false;
        if (e.getKeyCode() == KeyEvent.VK_ENTER)
            flag[10] = false;
    }

    public boolean isPressed(int keyCode){
        return e.getKeyCode() == keyCode;
    }

    public boolean isUp() {
        return flag[5];
    }

    public boolean isDown() {
        return flag[2];
    }

    public boolean isLeft() {
        return flag[3];
    }

    public boolean isRight() {
        return flag[1];
    }

    public boolean isShift() {
        return flag[4];
    }

    public boolean isX() {
        return flag[6];
    }

    public boolean isD() {
        return flag[7];
    }

    public boolean isC() {
        return flag[8];
    }

    public boolean isEscape() {
        return flag[9];
    }

    public boolean isEnter() {
        return flag[10];
    }

    public boolean isZ() {
        return flag[0];
    }
}

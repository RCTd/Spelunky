package GamePakage;

import GamePakage.GameWindow.GameWindow;
public class Main {
    public static void main(String[] args) {
        Game paooGame = new Game("PaooGame", 27*16 , 16*16);
        paooGame.StartGame();
    }
}

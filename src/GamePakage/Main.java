package GamePakage;

import GamePakage.GameWindow.GameWindow;
public class Main {
    public static void main(String[] args) {
        Game paooGame = new Game("PaooGame", 42*16 , 20*16);
        paooGame.StartGame();
    }
}

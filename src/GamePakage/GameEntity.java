package GamePakage;

import java.awt.*;

public interface GameEntity {
    void Update();
    void Draw(Graphics g);
    void Collide();

    int getX();
    int getY();
}

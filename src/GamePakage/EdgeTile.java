package GamePakage;

import GamePakage.Assets;
import GamePakage.Tile;

public class EdgeTile extends Tile {
    public EdgeTile(int id)
    {
        super(Assets.Brick,id,16,16);
        IsSolid=true;
    }

    @Override
    public boolean IsSolid() {
        return IsSolid;
    }
}

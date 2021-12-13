package App.Graphics.Frame.InGame.Model.GameWall;

import App.Graphics.Frame.InGame.View.Brick.Brick;
import java.awt.*;

/**
 * This interface is to get the types
 * of bricks that will be created
 */

public interface DetermineBricks {
    Brick getSpecificBrick(Point point, Dimension size);
}

package App.Graphics.Frame.InGame;

import App.Graphics.Frame.InGame.View.Brick;
import java.awt.*;

public interface DetermineBricks {
    Brick getSpecificBrick(Point point, Dimension size);
}

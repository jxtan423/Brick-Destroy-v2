package test;

import java.awt.*;

public enum ImpactDirection {

    UP_IMPACT {
        @Override
        public boolean setImpact(Brick brick, Ball ball, boolean isRubber) {
            if(isRubber)
                ball.reverseY();
            return brick.setImpact(ball.down, Crack.UP);
        }
    },
    DOWN_IMPACT{
        @Override
        public boolean setImpact(Brick brick, Ball ball, boolean isRubber) {
            if(isRubber)
                ball.reverseY();
            return brick.setImpact(ball.down, Crack.DOWN);
        }
    },
    LEFT_IMPACT {
        @Override
        public boolean setImpact(Brick brick, Ball ball, boolean isRubber) {
            if(isRubber)
                ball.reverseX();
            return brick.setImpact(ball.right, Crack.RIGHT);
        }
    },
    RIGHT_IMPACT {
        @Override
        public boolean setImpact(Brick brick, Ball ball, boolean isRubber) {
            if(isRubber)
                ball.reverseX();
            return brick.setImpact(ball.left, Crack.LEFT);
        }
    };

    public abstract boolean setImpact(Brick brick, Ball ball, boolean isRubber);
}

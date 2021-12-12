package App.Graphics.Frame.InGame.View;

/**
 * This enum class is to differentiate
 * the type of impact which the bricks
 * got will perform different functionality
 * in terms of direction and position.
 *
 */

public enum ImpactDirection {

    UP_IMPACT {
        @Override
        public boolean setImpact(Brick brick, Ball ball, boolean isRubber) {
            if(isRubber)
                ball.reverseY();
            return brick.setImpact(ball.down, Crack.UP);
        }
    },
    DOWN_IMPACT {
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

    /**
     * This abstract method will be implemented by
     * the enum constant to perform respective
     * functionality whenever the condition meets.
     *
     * @param brick The respective brick
     * @param ball The ball
     * @param isRubber Boolean value whether is rubber ball or not
     * @return The boolean value of brick impact
     */

    public abstract boolean setImpact(Brick brick, Ball ball, boolean isRubber);
}

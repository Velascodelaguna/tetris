package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.shape.Rectangle;

import static tetris.tetromino.Tetromino.PIXEL_SIZE;

/*
Translate the x and y values of a position a number of spaces
This will be used for when a tetromino needs to rotate
 */

public class BlockMover {

    private int xTrans = 0;
    private int yTrans = 0;

    public BlockMover(int x, int y) {
        xTrans = x * PIXEL_SIZE;
        yTrans = y * PIXEL_SIZE;
    }

    public Point2D move(Rectangle square) {
        return new Point2D(square.getX() + this.xTrans, square.getY() + this.yTrans);
    }

    public static BlockMover noMove() {
        return new BlockMover(0, 0);
    }

}

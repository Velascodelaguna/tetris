package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class LTetromino extends Tetromino {

    public LTetromino() {
        this.type = TetrominoType.L;
        this.color = Color.ORANGE;
        this.position = new Point2D(PIXEL_SIZE*3, 0);

        initializeSquares();
        initializeRotationCoordinates();
    }

    protected void initializeSquares() {
        IntStream.range(0, 4).forEach(i -> {
            Rectangle square = new Rectangle();
            square.setFill(this.color);
            square.setStroke(this.borderColor);
            square.setWidth(PIXEL_SIZE);
            square.setHeight(PIXEL_SIZE);

            if (i < 3) {
                square.setX(this.position.getX() + PIXEL_SIZE * i);
                square.setY(this.position.getY());
            } else {
                square.setX(this.position.getX());
                square.setY(this.position.getY() + PIXEL_SIZE);
            }

            this.squares[i] = square;
        });
    }

    @Override
    protected void initializeRotationCoordinates() {

        BlockMover[]  movers1 = new BlockMover[] {
                new BlockMover(1, -1),
                BlockMover.noMove(),
                new BlockMover(-1, 1),
                new BlockMover(0, -2)
        };

        BlockMover[] movers2 = new BlockMover[] {
                new BlockMover(1, 1),
                BlockMover.noMove(),
                new BlockMover(-1, -1),
                new BlockMover(2, 0)
        };

        BlockMover[] movers3 = new BlockMover[] {
                new BlockMover(-1, 1),
                BlockMover.noMove(),
                new BlockMover(1, -1),
                new BlockMover(0, 2)
        };

        BlockMover[] movers0 = new BlockMover[] {
                new BlockMover(-1, -1),
                BlockMover.noMove(),
                new BlockMover(1, 1),
                new BlockMover(-2, 0),
        };

        rotationList = new ArrayList<>(4);
        rotationList.add(movers0);
        rotationList.add(movers1);
        rotationList.add(movers2);
        rotationList.add(movers3);
    }

}

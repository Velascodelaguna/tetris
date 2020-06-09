package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.stream.IntStream;

public class LTetromino extends Tetromino {

    public LTetromino() {
        this.type = TetrominoType.L;
        this.color = Color.ORANGE;
        this.group = new Group();
        this.position = new Point2D(PIXEL_SIZE*3, 0);
        this.orientation = new int[][] {
                {0, 0, 0},
                {1, 1, 1},
                {1, 0, 0}
        };

        initializeSquares();
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
                square.setX(this.position.getX() + PIXEL_SIZE);
                square.setY(this.position.getY() + PIXEL_SIZE);
            }

            this.squares[i] = square;
            group.getChildren().add(square);
        });
    }
}

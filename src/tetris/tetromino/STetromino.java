package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.stream.IntStream;

public class STetromino extends Tetromino {

    public STetromino() {
        this.type = TetrominoType.S;
        this.color = Color.GREEN;
        this.group = new Group();
        this.position = new Point2D(PIXEL_SIZE*3, 0);
        this.orientation = new int[][] {
                {0, 1, 1},
                {1, 1, 0},
                {0, 0, 0}
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

            if (i == 0 || i == 1) {
                square.setX(this.position.getX() + PIXEL_SIZE * (i + 1));
            }

            if (i == 2 || i == 3) {
                square.setX(this.position.getX() + PIXEL_SIZE * (i - 2));
            }

            if (i > 1) {
                square.setY(PIXEL_SIZE);
            }

            this.squares[i] = square;
            group.getChildren().add(square);
        });
    }
}

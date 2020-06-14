package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class OTetromino extends Tetromino {

    public OTetromino() {
        this.type = TetrominoType.O;
        this.color = Color.YELLOW;

        // start at the middle columns of the play field
        this.position = new Point2D(PIXEL_SIZE*4, 0);


        initializeSquares();

    }

    protected void initializeSquares() {
        IntStream.range(0, 4).forEach(i -> {
            Rectangle square = new Rectangle();
            square.setFill(this.color);
            square.setStroke(this.borderColor);
            square.setWidth(PIXEL_SIZE);
            square.setHeight(PIXEL_SIZE);

            // align the squares so that they form the O-tetromino
            square.setX(this.position.getX());
            square.setY(this.position.getY());
            if (i % 2 == 1) {
                double newX = this.position.getX() + PIXEL_SIZE;
                square.setX(newX);
            }
            if (i >= 2) {
                double newY = this.position.getY() + PIXEL_SIZE;
                square.setY(newY);
            }

            squares[i] = square;
        });

    }

    @Override
    protected void initializeRotationCoordinates() {
        rotationList = new ArrayList<>();
    }

}

package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.stream.IntStream;

public class OTetromino extends Tetromino {

    private final TetrominoType type = TetrominoType.O;

    public OTetromino() {
        this.color = Color.YELLOW;
        this.position = new Point2D(this.PIXEL_SIZE*4, 0);
        this.orientation = new int[][] {
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 1, 1, 0},
            {0, 1, 1, 0}
        };
    }

    public Group getShape() {
        Group group = new Group();

        IntStream.range(0, 4).forEach(i -> {
            Rectangle square = new Rectangle();
            square.setFill(color);
            square.setStroke(Color.DARKGRAY);
            square.setWidth(this.PIXEL_SIZE);
            square.setHeight(this.PIXEL_SIZE);
            square.setX(this.position.getX());
            square.setY(this.position.getY());

            if (i%2 == 1) {
                double newX = this.position.getX() + this.PIXEL_SIZE;
                square.setX(newX);
            }
            if (i >= 2) {
                double newY = this.position.getY() + this.PIXEL_SIZE;
                square.setY(newY);
            }
            group.getChildren().add(square);
        });
        return group;
    }


}

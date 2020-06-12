package tetris.tetromino;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.stream.IntStream;

public class ITetromino extends Tetromino {



    public ITetromino() {
        this.type = TetrominoType.I;
        this.color = Color.CYAN;
        this.group = new Group();
        this.position = new Point2D(PIXEL_SIZE*3, 0);
        this.rotationCoordinates = new LinkedList<>();
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

            square.setX(this.position.getX() + PIXEL_SIZE * i);
            this.squares[i] = square;
            group.getChildren().add(square);
        });
    }

    @Override
    protected void initializeRotationCoordinates() {

        rotationList = new ArrayList<>(4);
        BlockMover[] movers0 = new BlockMover[] {
                new BlockMover(2, -1),
                new BlockMover(1, 0),
                new BlockMover(0, 1),
                new BlockMover(-1, 2)
        };

        BlockMover[] movers1 = new BlockMover[] {
                new BlockMover(1, 2),
                new BlockMover(0, 1),
                new BlockMover(-1, 0),
                new BlockMover(-2, -1)
        };

        BlockMover[] movers2 = new BlockMover[] {
                new BlockMover(-2, 1),
                new BlockMover(-1, 0),
                new BlockMover(0, -1),
                new BlockMover(1, -2)
        };

        BlockMover[] movers3 = new BlockMover[] {
                new BlockMover(-1, -2),
                new BlockMover(0, -1),
                new BlockMover(1, 0),
                new BlockMover(2, 1)
        };

        rotationList.add(movers3);
        rotationList.add(movers0);
        rotationList.add(movers1);
        rotationList.add(movers2);
    }
}

package tetris;

import javafx.scene.shape.Rectangle;
import tetris.tetromino.Tetromino;
import tetris.tetromino.TetrominoHandler;

import java.util.Arrays;
import java.util.stream.Stream;

public class SidePanelControl {

    private final TetrisAppControl appControl;
    private final TetrominoHandler tetrominoHandler;

    public SidePanelControl(TetrisAppControl appControl, TetrominoHandler tetrominoHandler) {
        this.appControl = appControl;
        this.tetrominoHandler = tetrominoHandler;
    }

    public void restart() {
        appControl.setStartScreen();
    }

    public void quit() {
        appControl.endGame();
    }

    public Rectangle[] getNextTetromino() {
        Rectangle[] nextSquares = tetrominoHandler.getNextTetromino().getSquares();
        Stream<Rectangle> squareStream = Arrays.stream(nextSquares);
        double minX = squareStream.mapToDouble(Rectangle::getX).min().orElse(0);
        double halfSquareSize = Tetromino.PIXEL_SIZE / 2.0;
        return Arrays.stream(nextSquares).map(square -> {
            Rectangle newSquare = new Rectangle();
            newSquare.setStroke(square.getStroke());
            newSquare.setFill(square.getFill());

            double xTrans = square.getX() - minX;
            double xCoord = xTrans == 0? 0: (xTrans / 2);
            newSquare.setX(xCoord);

            double yCoord = square.getY() == 0? 0: square.getY() - halfSquareSize;
            newSquare.setY(yCoord);

            newSquare.setWidth(halfSquareSize);
            newSquare.setHeight(halfSquareSize);
            return newSquare;
        }).toArray(Rectangle[]::new);
    }

}

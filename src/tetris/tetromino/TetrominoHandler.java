package tetris.tetromino;

import java.util.LinkedList;
import java.util.List;

public class TetrominoHandler {

    private List<Tetromino> tetrominoes;
    private Tetromino activeTetromino;

    public TetrominoHandler() {
        this.tetrominoes = new LinkedList<>();
    }

    public void initialize() {
        addTetromino(new OTetromino());
    }

    public void addTetromino(Tetromino t) {
        activeTetromino = t;
        tetrominoes.add(t);
    }

    public List<Tetromino> getTetrominoes() {
        return tetrominoes;
    }

    public Tetromino getActiveTetromino() {
        return this.activeTetromino;
    }
}

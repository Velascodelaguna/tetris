package tetris.tetromino;

import java.util.*;

public class TetrominoHandler {

    private Queue<Tetromino> tetrominoBatch;
    private Tetromino activeTetromino;
    private int tetrominoCount;

    public TetrominoHandler() {
        this.tetrominoBatch = new LinkedList<>();
    }

    public void initialize() {
        // generate two batches so that the Next Block will always
        // show an available tetromino. The queue will always have
        // at least 7 blocks inside. New batches will get generated
        // after 7 blocks have been used in the play field.
        generateTetrominoes();
        generateTetrominoes();
        addNewBlock();
    }

    private void generateTetrominoes() {
        List<TetrominoType> types = Arrays.asList(TetrominoType.values());
        Collections.shuffle(types);
        for (TetrominoType type: types) {
            switch(type) {
                case I -> tetrominoBatch.offer(new ITetromino());
                case J -> tetrominoBatch.offer(new JTetromino());
                case L -> tetrominoBatch.offer(new LTetromino());
                case O -> tetrominoBatch.offer(new OTetromino());
                case S -> tetrominoBatch.offer(new STetromino());
                case T -> tetrominoBatch.offer(new TTetromino());
                case Z -> tetrominoBatch.offer(new ZTetromino());
            }
        }
    }

    public Tetromino getActiveTetromino() {
        return this.activeTetromino;
    }

    public void addNewBlock() {
        tetrominoCount++;
        if (tetrominoCount == 7) {
            tetrominoCount = 0;
            generateTetrominoes();
        }
        this.activeTetromino = tetrominoBatch.poll();
    }

}

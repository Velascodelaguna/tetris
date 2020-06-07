package tetris.tetromino;

public enum TetrominoType {
    I('I'),
    O('O'),
    T('T'),
    S('S'),
    Z('Z'),
    J('J'),
    L('L');

    public char symbol;

    TetrominoType(char c) {
        this.symbol = c;
    }
}

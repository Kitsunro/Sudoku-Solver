package Rules;

import Sudoku.Sudoku;

abstract class DeductibleRule {
    private final Sudoku tempSudoku;
    public boolean check(Sudoku sudoku) {
        return false;
    }

    public DeductibleRule(Sudoku sudoku) {
        this.tempSudoku = sudoku;
    }

    public Sudoku getTempSudoku() {
        return tempSudoku;
    }
}

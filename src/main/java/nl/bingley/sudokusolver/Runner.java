package nl.bingley.sudokusolver;

import java.io.IOException;

public class Runner {

    private Sudoku sudoku = new Sudoku();
    private int row = 0;
    private int col = 0;
    private int num = 1;
    private long lastUpdate = System.currentTimeMillis();

    public static void main(String[] args) throws IOException {
        Runner runner = new Runner();
        runner.solve();
    }

    private void solve() throws IOException {
        //sudoku = new Sudoku("876900000010006000040305800400000210090500000050040306029000008004690173000001004");
        //sudoku = new Sudoku("000000000000504000070169050060000030081000420009408500900805001010000070050603040");
        //sudoku = new Sudoku("000206040000050100000381702030000906081763420405000070709628000002030000010509000");
        sudoku = new Sudoku("290080001630000000000203400000400800701000902006009000003701000000000054400030018");
        System.out.println("Problem:");
        System.out.println(sudoku);
        while (!sudoku.isSolved()) {
            if (num > 9) {
                sudoku.setValue(row, col, new SudokuValue());
                decrementCell();
                num = sudoku.getValue(row, col).getNumber() + 1;
            } else if (tryNumber()) {
                num = 1;
                incrementCell();
                if (lastUpdate + 5000 < System.currentTimeMillis()) {
                    System.out.println("Progress:");
                    System.out.println(sudoku);
                    lastUpdate = System.currentTimeMillis();
                }
            } else {
                num++;
            }
        }
        System.out.println("Solution:");
        System.out.println(sudoku);
    }

    private boolean tryNumber() {
        sudoku.setValue(row, col, new SudokuValue(num));
        boolean valid = Validator.validate(sudoku, row, col);
        if (valid && row == 8 && col == 8) {
            sudoku.setSolved(true);
        }
        return valid;
    }

    private void incrementCell() {
        col++;
        if (col > 8) {
            col = 0;
            row++;
        }
        if (row > 8) {
            sudoku.setSolved(true);
        } else if (sudoku.getValue(row, col).isImmutable()) {
            incrementCell();
        }
    }

    private void decrementCell() {
        col--;
        if (col < 0) {
            col = 8;
            row--;
        }
        if (sudoku.getValue(row, col).isImmutable()) {
            decrementCell();
        }
    }
}

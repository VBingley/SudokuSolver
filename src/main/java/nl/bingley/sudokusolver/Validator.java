package nl.bingley.sudokusolver;

import java.util.List;

public class Validator {

    public static boolean validate(Sudoku sudoku, int row, int column) {
        return isRowValid(sudoku, row) && isColumnValid(sudoku, column) && isBlockValid(sudoku, row, column);
    }

    private static boolean isRowValid(Sudoku sudoku, int row) {
        return isListValid(sudoku.getRowValues(row));
    }

    private static boolean isColumnValid(Sudoku sudoku, int column) {
        return isListValid(sudoku.getColumnValues(column));
    }

    private static boolean isBlockValid(Sudoku sudoku, int row, int column) {
        return isListValid(sudoku.getBlockValues(row, column));
    }

    private static boolean isListValid(List<SudokuValue> values) {
        for (Integer num = 1; num <= 9; num++) {
            boolean isValid = values.stream()
                    .map(SudokuValue::getNumber)
                    .filter(num::equals)
                    .count() <= 1;
            if (!isValid) {
                return false;
            }
        }
        return true;
    }
}

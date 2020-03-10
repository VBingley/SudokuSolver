package nl.bingley.sudokusolver;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Sudoku {

    private boolean isSolved;
    private SudokuValue[][] values;

    public Sudoku() {
        isSolved = false;
        values = new SudokuValue[9][9];
    }

    public Sudoku(String sudokuString) {
        values = new SudokuValue[9][9];
        int row = 0;
        int col = 0;
        for (char cell : sudokuString.toCharArray()) {
            if (cell != '0') {
                values[row][col] = new SudokuValue(Character.getNumericValue(cell), true);
            }
            col++;
            if (col > 8) {
                col = 0;
                row++;
            }
        }
    }

    public void setValue(int row, int column, SudokuValue value) {
        values[row][column] = value;
    }

    public SudokuValue getValue(int row, int column) {
        return Objects.requireNonNullElseGet(values[row][column], SudokuValue::new);
    }

    public List<SudokuValue> getRowValues(int row) {
        if (values[row] != null) {
            return Stream.of(values[row])
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        } else {
            return new ArrayList<>();
        }
    }

    public List<SudokuValue> getColumnValues(int column) {
        List<SudokuValue> values = new ArrayList<>();
        for (int row = 0; row < 9; row++) {
            values.add(getValue(row, column));
        }
        return values;
    }

    public List<SudokuValue> getBlockValues(int row, int column) {
        List<SudokuValue> values = new ArrayList<>();
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                values.add(getValue(x + getBlockStartCoordinate(row), y + getBlockStartCoordinate(column)));
            }
        }
        return values;
    }

    private int getBlockStartCoordinate(int coordinate) {
        return coordinate / 3 * 3;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }

    @Override
    public String toString() {
        String divider = "#########################" + System.lineSeparator();
        StringBuilder builder = new StringBuilder();
        return divider
                + toString(0)
                + toString(1)
                + toString(2)
                + divider
                + toString(3)
                + toString(4)
                + toString(5)
                + divider
                + toString(6)
                + toString(7)
                + toString(8)
                + divider;
    }

    private String toString(int row) {
        return "# " + getValue(row, 0) + ' ' + getValue(row, 1) + ' ' + getValue(row, 2) + " # "
                + getValue(row, 3) + ' ' + getValue(row, 4) + ' ' + getValue(row, 5) + " # "
                + getValue(row, 6) + ' ' + getValue(row, 7) + ' ' + getValue(row, 8) + " #" + System.lineSeparator();
    }
}

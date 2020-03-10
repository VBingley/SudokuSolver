package nl.bingley.sudokusolver;

public class SudokuValue {

    private final int number;
    private final boolean immutable;

    public SudokuValue() {
        number = -1;
        immutable = false;
    }

    public SudokuValue(int number) {
        this.number = number;
        immutable = false;
    }

    public SudokuValue(int number, boolean immutable) {
        this.number = number;
        this.immutable = immutable;
    }

    public int getNumber() {
        return number;
    }

    public boolean isImmutable() {
        return immutable;
    }

    @Override
    public String toString() {
        if (number == -1) {
            return " ";
        } else {
            return String.valueOf(number);
        }
    }
}

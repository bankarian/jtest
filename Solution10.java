import java.util.HashSet;
import java.util.Set;

public class Solution10 {
    public static void main(String[] args) {
        int[][] sudoku = {
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 0, 0, 0, 0, 0, 0},

            {0, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 3, 0, 0, 0, 0, 0},
            {0, 0, 2, 0, 0, 0, 0, 0, 0},

            {0, 0, 1, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0},
        };
        boolean res = new Solution10().validate(sudoku);
        System.out.println(res);
    }

    public boolean validate(int[][] sudoku) {
        // validate rows
        for (int r = 0; r < 9; r++) {
            Set<Integer> exist = new HashSet<>();
            for (int c = 0; c < 9; c++) {
                int val = sudoku[r][c];
                if (isEmpty(val)) {
                    continue;
                }
                if (!isValidNum(val) || exist.contains(val)) {
                    return false;
                }
                exist.add(val);
            }
        }

        // validate columns
        for (int c = 0; c < 9; c++) {
            Set<Integer> exist = new HashSet<>();
            for (int r = 0; r < 9; r++) {
                int val = sudoku[r][c];
                if (isEmpty(val)) {
                    continue;
                }
                if (!isValidNum(val) || exist.contains(val)) {
                    return false;
                }
                exist.add(val);
            }
        }

        // validate sub-box
        for (int r = 0; r < 9; r += 3) {
            for (int c = 0; c < 9; c += 3) {
                if (!validateSubBox(sudoku, new Position(r, c))) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean validateSubBox(int[][] sudoku, Position leftUp) {
        Set<Integer> exist = new HashSet<>();
        for (int r = leftUp.r; r < leftUp.r + 3; r++) {
            for (int c = leftUp.c; c < leftUp.c + 3; c++) {
                int val = sudoku[r][c];
                if (isEmpty(val)) {
                    continue;
                }
                if (!isValidNum(val) || exist.contains(val)) {
                    return false;
                }
                exist.add(val);
            }
        }
        return true;
    }

    private boolean isValidNum(int val) {
        return val > 0 && val < 10;
    }

    private boolean isEmpty(int val) {
        return val == 0;
    }
}

class Position {
    int r, c;

    Position(int _r, int _c) {
        r = _r;
        c = _c;
    }
}

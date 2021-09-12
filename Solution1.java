import java.util.Scanner;

public class Solution1 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int y = s.nextInt();
        int m = s.nextInt();
        int d = s.nextInt();
        System.out.println(new Solution1().getDay(y, m, d));
    }

    static class Date {
        int Y, M, D, d;

        Date(int Y, int M, int D, int d) {
            this.Y = Y;
            this.M = M;
            this.D = D;
            this.d = d;
        }

        Date(int Y, int M, int D) {
            this.Y = Y;
            this.M = M;
            this.D = D;
        }

        boolean isAfter(Date b) {
            if (Y != b.Y)
                return Y > b.Y;
            if (M != b.M)
                return M > b.M;
            if (D != b.D)
                return D > b.D;
            return d > b.d;
        }
    }

    static Date pivot = new Date(2021, 9, 12, 0);
    int res = 0;
    int[] days = { 0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

    // Return 0-6, representing Sunday to Saturday.
    int getDay(int y, int m, int d) {
        Date date = new Date(y, m, d);
        if (date.isAfter(pivot)) {
            int diff = calDiff(pivot, date);
            System.out.println(diff);
            return (pivot.d + diff) % 7;
        }
        int diff = calDiff(date, pivot);
        System.out.println(diff);
        int tmp = pivot.d - (diff % 7);
        return tmp < 0 ? tmp + 7 : tmp;
    }

    int calDiff(Date lo, Date hi) {
        int diff = 0;

        // in order to compare with 1/1
        int extra = 0;
        for (int i = 1; i < lo.M; i++) {
            extra += days[i];
            if (i == 2 && isLeap(lo.Y))
                extra++;
        }
        extra += lo.D - 1;

        for (int i = lo.Y; i < hi.Y; i++) {
            diff += 365;
            if (isLeap(i))
                diff++;
        }
        for (int i = 1; i < hi.M; i++) {
            diff += days[i];
            if (i == 2 && isLeap(hi.Y))
                diff++;
        }
        for (int i = 1; i < hi.D; i++) {
            diff++;
        }
        return diff - extra;
    }

    boolean isLeap(int y) {
        if (y % 100 == 0)
            return y % 400 == 0;
        return y % 4 == 0;
    }
}
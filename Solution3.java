public class Solution3 {
    public static void main(String[] args) {
        int[] a = { 3, 5, 6, 7, 8, 12, 13, 34 };
        int[] b = { 4, 12, 14, 16, 22, 26 };
        for (int k = 1; k <= a.length + b.length; k++) {
            System.out.println(new Solution3().findKth(a, b, k));
        }
    }

    int findKth(int[] a, int[] b, int k) {
        return findKth(new Slice(a), new Slice(b), k);
    }

    int findKth(Slice a, Slice b, int k) {
        if (a.size() == 0)
            return b.get(k - 1);
        if (b.size() == 0)
            return a.get(k - 1);
        if (k == 1) {
            if (a.get(0) < b.get(0))
                return a.get(0);
            return b.get(0);
        }
        int aThres = Math.min(a.size(), k / 2);
        int bThres = Math.min(b.size(), k - aThres);
        if (a.get(aThres - 1) < b.get(bThres - 1)) {
            // exclude a's subarray
            return findKth(a.slice(aThres), b, k - aThres);
        }
        // exclude b's subarray
        return findKth(a, b.slice(bThres), k - bThres);
    }

    static class Slice {
        private int[] arr;
        private final int st, en;

        Slice(int[] arr) {
            this.arr = arr;
            st = 0;
            en = arr.length;
        }

        Slice(int[] arr, int st, int en) {
            this.arr = arr;
            this.st = st;
            this.en = en;
        }

        Slice slice(int i) {
            return new Slice(arr, st + i, en);
        }

        int size() {
            if (st > en)
                return 0;
            return en - st;
        }

        int get(int i) {
            return arr[st + i];
        }

        void print() {
            for (int i = st; i < en; i++) {
                System.out.printf("%d ", arr[i]);
            }
            System.out.println();
        }
    }
}

public class Solution17_1 {
    public static void main(String[] args) {
        // int[] stream = {1, 2, 2, 3, 4};
        int[] stream = {4, 3, 3, 2, 1};
        Solution17_1 s = new Solution17_1();
        for (int num: stream) {
            s.putNum(num);
            System.out.println(s.findMedian());
        }
    }

    // follow up 1
    int[] slots = new int[101];
    int size = 0;

    double findMedian() {
        // 1. size is odd -> size / 2
        // 2. size is even -> size/ 2, size / 2 -1
        if ((size & 1) == 1) {
            return find(size / 2 + 1);
        }
        double a = find(size / 2);
        double b = find(size / 2 + 1);
        return (a + b) / 2;
    }

    int find(int pos) {
        int acc = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] == 0) {
                continue;
            }
            acc += slots[i];
            if (acc >= pos) {
                return i;
            }
        }
        return 0;
    }

    void putNum(int num) {
        slots[num]++;
        size++;
    }
}

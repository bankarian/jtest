import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

public class Solution17_2 {
    public static void main(String[] args) {
        int[] stream = {-12, 1, 2, 2, 3, 4, 120, 1000};
        // int[] stream = {4, 3, 3, 2, 1};
        Solution17_2 s = new Solution17_2();
        for (int num: stream) {
            s.putNum(num);
            System.out.println(s.findMedian());
        }
    }

    // follow up 2
    int[] slots = new int[101];
    int inSlot;

    // number->times
    SortedMap<Integer, Integer> belows = new TreeMap<>();
    SortedMap<Integer, Integer> aboves = new TreeMap<>();

    double findMedian() {
        // 1. size is odd -> size / 2
        // 2. size is even -> size/ 2, size / 2 -1
        int size = size();
        if ((size & 1) == 1) {
            return find(size / 2 + 1);
        }
        double a = find(size / 2);
        double b = find(size / 2 + 1);
        return (a + b) / 2;
    }

    int find(int pos) {
        int acc = 0;
        if (pos <= belows.size()) {
            for (Entry<Integer, Integer> entry: belows.entrySet()) {
                acc += entry.getValue();
                if (acc >= pos) {
                    return entry.getKey();
                }
            }
        }

        if (pos <= belows.size() + inSlot) {
            acc = belows.size();
            for (int i = 0; i < 101; i++) {
                acc += slots[i];
                if (acc >= pos) {
                    return i;
                }
            }
        }

        acc = belows.size() + inSlot;
        for (Entry<Integer, Integer> entry: aboves.entrySet()) {
            acc += entry.getValue();
            if (acc >= pos) {
                return entry.getKey();
            }
        }
        return 0;
    }

    int size() {
        return belows.size() + inSlot + aboves.size();
    }

    void putNum(int num) {
        if (num >= 0 && num < slots.length) {
            slots[num]++;
            inSlot++;
        } else if (num < 0) {
            belows.put(num, belows.getOrDefault(num, 0) + 1);
        } else {
            aboves.put(num, belows.getOrDefault(num, 0) + 1);
        }
    }
}

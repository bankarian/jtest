import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Solution14 {
    public static void main(String[] args) {
        int[][] sets = {
            {1, 2, 3},
            {5, 2, 3},
            {4, 2, 7},
            {8},
        };
        Solution14 s = new Solution14();
        int res = s.merge(sets);
        System.out.println(res);
        s.printSets();
    }

    // [1,2,3] [2,3,5] [8]
    // -> [1,2,3,5] [8]
    int merge(int[][] sets) {
        for (int[] set: sets) {
            for (int num: set) {
                if (!root.containsKey(num)) {
                    root.put(num, num);
                }
            }
            
            for (int i = 1; i < set.length; i++) {
                union(set[i-1], set[i]);
            }
        }
        return root.entrySet()
                    .stream()
                    .filter(entry -> entry.getKey() == entry.getValue())
                    .collect(Collectors.toList())
                    .size();
    }

    void printSets() {
        root.entrySet()
            .stream()
            .sorted((a, b) -> a.getValue() - b.getValue())
            .forEach(e -> System.out.printf("{%d, root=%d}\n", e.getKey(), find(e.getValue())));
    }


    // val -> parent
    Map<Integer, Integer> root = new HashMap<>();

    int find(int u) {
        while (root.get(u) != u) {
            int pa = root.get(u);
            root.put(u, root.get(pa));

            u = root.get(u);
        }
        return u;
    }

    void union(int u, int v) {
        int rootU = find(u), rootV = find(v);
        root.put(rootU, rootV);
    }
}

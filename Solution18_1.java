import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Solution18_1 {
    // We can use queue to represent a cycle!
    // which is easier to maintain
    public static void main(String[] args) {
        new Solution18_1().solve(10, 3)
                        .forEach(e -> System.out.printf("->%d", e));
    }

    Queue<Integer> q = new LinkedList<>();

    List<Integer> solve(int n, int m) {
        for (int i = 1; i <= n; i++) {
            q.offer(i);
        }
        List<Integer> result = new ArrayList<>(n);
        while (!q.isEmpty()) {
            int size = q.size();
            int step = m > size ? m % size : m;
            for (int i = 1; i < step; i++) {
                q.offer(q.poll());
            }
            result.add(q.poll());
        }
        return result;
    }
}

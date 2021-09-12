import java.util.Deque;
import java.util.LinkedList;
import java.util.Scanner;

public class Solution2 {
    public static void main(String[] args) {
        new Solution2().solve();
    }

    void solve() {
        Scanner sin = new Scanner(System.in);
        n = sin.nextInt();
        for (int i = 1; i <= n; i++) {
            walk(new LinkedList<>(), i);
        }
    }

    int n;

    void walk(Deque<Integer> dq, int size) {
        if (dq.size() == size) {
            print(dq);
            return;
        }
        if (dq.isEmpty()) {
            for (int i = 1; i <= n; i++) {
                dq.offerLast(i);
                walk(dq, size);
                dq.pollLast();
            }
            return;
        }
        int tail = dq.peekLast();
        for (int i = tail + 1; i <= n; i++) {
            dq.offerLast(i);
            walk(dq, size);
            dq.removeLast();
        }
    }

    void print(Deque<Integer> dq) {
        for (int i : dq) {
            System.out.printf("%d ", i);
        }
        System.out.println();
    }
}

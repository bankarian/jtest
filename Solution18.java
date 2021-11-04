import java.util.ArrayList;
import java.util.List;

public class Solution18 {
    // N person
    // M -> leave
    // reproduce the departure order

    public static void main(String[] args) {
        new Solution18().solve(10, 3)
                        .forEach(System.out::println);
    }

    List<Integer> solve(int n, int m) {
        List<Integer> res = new ArrayList<>(n);
        Node head = new Node();
        Node p = head;
        int size = n;
        for (int i = 1; i <= n; i++) {
            p.next = new Node(i);
            p = p.next;
        }
        Node first = head.next;
        p.next = first;

        while (size > 0) {
            int steps = m > size ? m % size : m;
            for (int i = 1; i < steps; i++) {
                p = p.next;
            }
            Node cur = p.next;
            int val = remove(p, cur);
            size--;
            res.add(val);
        }
        return res;
    }

    int remove(Node pre, Node cur) {
        int val = cur.val;

        pre.next = cur.next;
        cur = cur.next = null;
        return val;
    }

    static class Node {
        int val;
        Node next;

        Node() {
        }

        Node(int val) {
            this.val = val;
        }
    }
}

public class Solution16 {
    public static void main(String[] args) {
        int[] vals = {1, 2, 2, 2, 3, 4, 5, 5, 6, 7};
        Solution16 s = new Solution16();
        Node first = s.toList(vals);
        first = s.filter(first);
        for (Node p = first; p != null; p = p.next) {
            System.out.printf("->%d", p.val);
        }
    }

    Node toList(int[] vals) {
        Node head = new Node();
        Node p = head;
        for (int val: vals) {
            p.next = new Node(val);
            p = p.next;
        }
        return head.next;
    }
 
    Node filter(Node first) {
        if (first == null) {
            return null;
        }
        Node p = first.next;
        while (p != null && p.val == first.val) {
            p = p.next;
        }
        first.next = filter(p);
        return first;
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

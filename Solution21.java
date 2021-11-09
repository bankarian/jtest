import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution21 {
    public static void main(String[] args) {
        List<Node> seq = Stream.of(
            new Node(5),
            new Node(2),
            new Node(6),
            new Node(1),
            new Node(3),
            null,
            null,
            null,
            null,
            null,
            null
        ).collect(Collectors.toCollection(ArrayList::new));
        TreeHelper h = new TreeHelper();
        List<Node> seq2 = h.serialize(h.deserialize(seq));
        seq.forEach(node -> {
            if (node == null) {
                System.out.print("->null");
            } else {
                System.out.printf("->%d", node.val);
            }
        });
        System.out.println();
        seq2.forEach(node -> {
            if (node == null) {
                System.out.print("->null");
            } else {
                System.out.printf("->%d", node.val);
            }
        });
    }

    static class Node {
        int val;
        Node left, right;
        Node(int val) {
            this.val = val;
        }
    }

    static class TreeHelper {
        List<Node> serialize(Node root) {
            Queue<Node> q = new LinkedList<>();
            q.offer(root);
            List<Node> res = new LinkedList<>();
            while (!q.isEmpty()) {
                Node n = q.poll();
                res.add(n);
                if (n == null) {
                    continue;
                }
                q.offer(n.left);
                q.offer(n.right);
            }
            return res;
        }

        Node deserialize(List<Node> seq) {
            seq = new ArrayList<>(seq);
            Node root = seq.get(0);
            int leftSub = 1;
            for (int i = 0; i < seq.size(); i++) {
                Node p = seq.get(i);
                if (p == null) {
                    continue;
                }
                p.left = seq.get(leftSub);
                p.right = seq.get(leftSub + 1);
                leftSub += 2;
            }
            return root;
        }
    }
}

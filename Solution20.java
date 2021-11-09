import java.util.LinkedList;
import java.util.List;

public class Solution20 {
    public static void main(String[] args) {
        String url = "/abc.com/files/:test";
        String url2 = "/abc.com/files/test2";
        String url3 = "/abc.com/disk/test";
        Trie trie = new Trie();
        trie.register(url);
        System.out.println(trie.search(url));
        System.out.println(trie.search(url2));
        System.out.println(trie.search(url3));
    }
}

class Trie {
    static class Node {
        String pattern;
        boolean isParam;
        List<Node> subs = new LinkedList<>();

        Node() {
        }

        Node(String pattern) {
            this.pattern = pattern;
            isParam = pattern.startsWith(":");
        }

        boolean equalsPattern(String pattern) {
            return isParam || this.pattern.equals(pattern);
        }
    }

    private Node root = new Node();

    public void register(String url) {
        String[] patterns = url.split("/");
        int i = 0;
        Node p = root;
        Node sub;
        while ((sub = findEqualSub(p, patterns[i])) != null) {
            i++;
            p = sub;
        }

        // do insert
        for (; i < patterns.length; i++) {
            sub = new Node(patterns[i]);
            p.subs.add(sub);
            p = sub;
        }
    }

    public boolean search(String url) {
        String[] patterns = url.split("/");
        Node p = root, sub;
        int i;
        for (i = 0; i < patterns.length; i++) {
            if ((sub = findEqualSub(p, patterns[i])) == null) {
                System.out.println("Unmatched at " + patterns[i]);
                break;
            }
            p = sub;
        }
        return i == patterns.length;
    }

    // return null, if no equal nodes
    private Node findEqualSub(Node parent, String target) {
        for (Node sub: parent.subs) {
            if (sub.equalsPattern(target)) {
                return sub;
            }
        }
        return null;
    }
}

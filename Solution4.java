import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Solution4 {
    public static void main(String[] args) {
        new Solution4().solve();
    }

    void solve() {
        Scanner sin = new Scanner(System.in);
        n = sin.nextInt();
        m = sin.nextInt();
        mutex = new int[m][2];
        for (int i = 0; i < m; i++) {
            mutex[i][0] = sin.nextInt();
            mutex[i][1] = sin.nextInt();
        }
        initGraph();
        doColor();
        System.out.println(color);
    }

    int n, m;
    int[][] mutex;
    Vertex[] vs;

    int color = 1; // current max available color

    void initGraph() {
        vs = new Vertex[n];
        for (int i = 0; i < n; i++)
            vs[i] = new Vertex();
        for (int[] m : mutex) {
            int a = m[0], b = m[1];
            vs[a].link(b);
            vs[b].link(a);
        }
    }

    void doColor() {
        for (Vertex v : vs) {
            Set<Integer> used = new HashSet<>();
            for (int nei : v.neighbors) {
                Vertex u = vs[nei];
                if (u.hasColor()) used.add(u.color);
            }
            if (used.size() == color) {
                // need to add new color
                color++;
                v.color = color;
                continue;
            }
            // choose a color to use
            for (int i = 1; i <= color; i++) {
                if (!used.contains(i)) {
                    v.color = i;
                    break;
                }
            }
        }
    }

    class Vertex {
        int color; // color=0, means no color
        List<Integer> neighbors = new LinkedList<>();

        void link(int b) {
            neighbors.add(b);
        }

        boolean hasColor() {
            return color > 0;
        }
    }
}

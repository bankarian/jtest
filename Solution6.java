import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;

public class Solution6 {
    public static void main(String[] args) {
        List<Integer> list = new Solution6().topK(4, 6, 1);
        for (int e: list) {
            System.out.println("type=" + e);
        }
    }

    // 1. list 
    // ==> skip-list
    // 2. count the existing times
    // ==> hashmap
    // O(n) existing times
    // O(n) topK

    Solution6() {
        sk = new SkipList();
        sk.insert(0, 2);
        sk.insert(2, 2);
        sk.insert(12, 2);
        sk.insert(6, 1);
        sk.insert(3, 2);
        sk.insert(5, 1);
        sk.insert(3, 2);
        sk.insert(8, 2);
        sk.insert(0, 2);
        sk.insert(4, 2);
    }

    SkipList sk = new SkipList();
    
    public List<Integer> topK(int a, int b, int k) {
        List<Integer> res = new ArrayList<>(k);
        SkipList.EventNode left = sk.find(a);
        SkipList.EventNode right = sk.find(b);
        SkipList.EventNode p = left;
        Map<Integer, Integer> map = new HashMap<>();
        while (p != right) {
            map.put(p.type, map.getOrDefault(p.type, 0) + 1);
            p = p.next[0];
        }
        PriorityQueue<Entry<Integer, Integer>> pq = 
            new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        for (Entry<Integer, Integer> kv: map.entrySet()) {
            pq.offer(kv);
        }
        for (int i = 0; i < k; i++) {
            if (pq.isEmpty()) return res;
            res.add(pq.poll().getKey());
        }
        return res;
    }
}


class SkipList {
    final int MAX_LEVEL = 32;
    int level = 1;
    EventNode root = new EventNode(MAX_LEVEL);

    static class EventNode {
        int time;
        int type;

        int level;
        EventNode[] next;

        EventNode(int level) {
            this.level = level;
            next = new EventNode[level];
        }

        EventNode(int level, int time, int type) {
            this.level = level;
            this.time = time;
            this.type = type;
            next = new EventNode[level];
        }
    }

    public void print() {
        for (int i = level - 1; i >= 0; i--) {
            EventNode p = root;
            while (p.next[i] != null) {
                System.out.printf("->%d", p.next[i].time);
                p = p.next[i];
            }
            System.out.println();
        }
    }

    public EventNode find(int t) {
        EventNode p = root;
        for (int l = level - 1; l >= 0; l--) {
            while (p.next[l] != null && p.next[l].time < t) {
                p = p.next[l];
            }
        }
        return p.next[0];
    }

    public void insert(int time, int type) {
        EventNode n = new EventNode(genLevel(), time, type);
        EventNode p = root;
        EventNode[] needUpdates = new EventNode[n.level];
        level = Math.max(level, n.level);
        for (int l = level - 1; l >= 0; l--) {
            while (p.next[l] != null && p.next[l].time < time) {
                p = p.next[l];
            }
            if (l < n.level) {
                needUpdates[l] = p;
            }
        }
        // insert
        for (int l = n.level - 1; l >= 0; l--) {
            n.next[l] = needUpdates[l].next[l];
            needUpdates[l].next[l] = n;
        }
    }

    private int genLevel() {
        // 0.5 => 2
        // 0.25 => 3 ...
        int level = 1;
        while (Math.random() < 0.5 && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }
}

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Map.Entry;

public class Solution6_1 {
    public static void main(String[] args) {
        Solution6_1.Event[] es = {
            new Event(0, 1),
            new Event(0, 2),
            new Event(0, 1),
            new Event(1, 2),
            new Event(2, 2),
            new Event(0, 2),
            new Event(3, 3),
            new Event(5, 1),
        };
        Solution6_1 s = new Solution6_1();
        for (int i = 0; i < es.length; i++) {
            s.happen(es[i].time, es[i].type);
        }

        List<Integer> res = s.topK(0, 1, 4);
        for (int i: res) System.out.println(i);
    }

    // To make it easier to implement in interview, 
    // I use ArrayList + binary search instead of SkipList,
    // which is less tricky to code.

    public static class Event {
        int time;
        int type;
        Event(int time, int type) {
            this.time = time;
            this.type = type;
        }
    }

    private List<Event> events = new ArrayList<>();
    

    // top k in [a, b]
    public List<Integer> topK(int a, int b, int k) {
        int l = binSearch(a);
        int r = binSearch(b); 
        List<Integer> res = new ArrayList<>();
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = l; i <= r; i++) {
            Event e = events.get(i);
            if (e.time > b) break;
            map.put(e.type, map.getOrDefault(e.type, 0) + 1);
        }
        PriorityQueue<Entry<Integer, Integer>> pq = 
            new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue()); // great heap
        for (Entry<Integer, Integer> e: map.entrySet()) {
            pq.offer(e);
        }
        for (int i = 0; i < k; i++) {
            if (pq.isEmpty()) return res;
            res.add(pq.poll().getKey());
        }
        return res;
    }

    // insert sort, since the events are already sorted, 
    // and are likely to insert in time order
    public void happen(int time, int type) {
        Event e = new Event(time, type);
        for (int i = events.size() - 1; i >= 0; i--) {
            if (e.time >= events.get(i).time) {
                events.add(i + 1, e);
                return;
            }
        }
        events.add(0, e);
    }

    // return index, where event's time >= t 
    private int binSearch(int t) {
        int l = 0, r = events.size() - 1;
        int index = 0;
        while (l <= r) {
            int mid = l + (r-l)/2;
            if (events.get(mid).time >= t) {
                index = mid;
                r = mid - 1;
                continue;
            }
            l = mid + 1;
        }
        return index;
    }
}

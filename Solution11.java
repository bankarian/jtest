import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution11 {
    public static void main(String[] args) {
        List<Interval> intervals = Stream.of(
            new Interval(2, 4),
            new Interval(2, 3),
            new Interval(4, 5),
            new Interval(3, 7),
            new Interval(1, 2)
        ).collect(Collectors.toList());
        int min = new Solution11().minConference(intervals);
        System.out.println(min);
    }

    // [2, 3] [2, 4] [4, 5] [3, 7]
    // -> [2, 3] [2, 4] [3, 7] [4, 5] 
    public int minConference(List<Interval> intervals) {
        intervals.sort((a, b) -> {
            if (a.start == b.start) {
                return a.end - b.end;
            }
            return a.start - b.start;
        });

        // a set of in-progress meeting's end time
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        intervals.forEach(interval -> {
            if (pq.isEmpty()) {
                pq.offer(interval.end);
                return;
            }
            int firstEndTime = pq.peek();
            if (interval.start >= firstEndTime) {
                // conquer the conference
                pq.poll();
                pq.offer(interval.end);
            } else {
                pq.offer(interval.end);
            }
        });
        return pq.size();
    }
}

class Interval {
    int start;
    int end;

    Interval(int s, int e) {
        start = s;
        end = e;
    }
}

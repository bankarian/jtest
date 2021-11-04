import java.util.PriorityQueue;

public class Solution17 {

    public static void main(String[] args) {
        // int[] stream = {1, 2, 2, 3, 4};
        int[] stream = {4, 3, 3, 2, 1};
        Solution17 s = new Solution17();
        for (int num: stream) {
            s.putNum(num);
            System.out.println(s.findMedian());
        }
    }

    double findMedian() {
        if (downMaxHeap.isEmpty()) {
            return 0.0;
        }
        if (downMaxHeap.size() == upMinHeap.size()) {
            double up = upMinHeap.peek();
            double down = downMaxHeap.peek();
            return (up + down) / 2.0;
        }
        return downMaxHeap.peek();
    }

    void putNum(int num) {
        upMinHeap.offer(num);
        downMaxHeap.offer(upMinHeap.poll());

        if (downMaxHeap.size() > upMinHeap.size() + 1) {
            upMinHeap.offer(downMaxHeap.poll());
        }
    }

    PriorityQueue<Integer> upMinHeap = new PriorityQueue<>();
    PriorityQueue<Integer> downMaxHeap = new PriorityQueue<>((k1, k2) -> k2 - k1);
}

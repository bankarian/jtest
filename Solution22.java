public class Solution22 {
    // we can consider effectiveness in 2 aspects:
    // 1. Time. For matrix multipication, we have to multipule each position 
    //      one by one, so we don't have much to do with it.
    // 2. Space. It is pointed out in the question that the matrix is sparse, 
    //      so we may consider a way to use less space to store it.
    //      Since most elements are zero, we can consider just storing those 
    //      none-zero elements as tuples (value, position), organizing them 
    //      as a list.
    // eg. (0, 0, 0, 0, 2, 0, 0, 0, 1) can store as
    //      (2, 5) -> (1, 8)

    public static void main(String[] args) {
        Solution22 s = new Solution22();
        int[] arr1 = {0, 0, 0, 1, 3, 0, 0, 5};
        int[] arr2 = {0, 0, 0, 0, 2, 0, 0, 1};
        VectorNode h1 = new VectorNode(), p1 = h1;
        VectorNode h2 = new VectorNode(), p2 = h2;
        for (int i = 0; i < arr1.length; i++) {
            p1.next = new VectorNode(arr1[i], i);
            p1 = p1.next;
        }
        for (int i = 0; i < arr2.length; i++) {
            p2.next = new VectorNode(arr2[i], i);
            p2 = p2.next;
        }
        System.out.println(s.mul(h1.next, h2.next));
    }


    static class VectorNode {
        int val;
        int pos;
        VectorNode next;

        VectorNode() {
        }

        VectorNode(int val, int pos) {
            this.val = val;
            this.pos = pos;
        }
    }

    public int mul(VectorNode v1, VectorNode v2) {
        VectorNode p1 = v1;
        VectorNode p2 = v2;
        int res = 0;
        while (p1 != null && p2 != null) {
            if (p1.pos == p2.pos) {
                res += p1.val * p2.val;
                p1 = p1.next;
                p2 = p2.next;
            } else if (p1.pos < p2.pos) {
                p1 = p1.next;
            } else {
                p2 = p2.next;
            }
        }
        return res;
    }

    // Follow up:
    // Still, we can just enhance the space efficiency. Storing a matrix is storing
    // a series of (value, position) tuples, so to reduce the space used, we can 
    // calculate the position instead of storing it. That is, we treat the dense
    // vector as several segments, each segment have a begining position.
    // eg. (2, 3, 3, 2, 5, 0, 0, 3, 9, 10)
    // {[2, 3, 3, 2, 5], start =0} -> {[3, 9, 10], start = 7} 

    static class VectorSegment {
        int start;
        

        VectorSegment next;
    }

    

    public int mul(VectorSegment v1, VectorSegment v2) {
        if (v1 == null || v2 == null) {
            return 0;
        }
        // let v1 be the segment that 'start' ahead of v2
        if (v1.start > v2.start) {
            VectorSegment tmp = v1;
            v1 = v2;
            v2 = tmp;
        }
        int i1 = v1.start, i2 = v2.start; 
    }
}
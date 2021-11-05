public class Solution18_2 {
    // Josephus problem follow up
    // use another algo to make time complexity to O(n), only need
    // to get the last depature person

    // We realize that each depature member is calculated based on 
    // previous one, that is we used the result of last subproblem.
    // Therefore, state transition can be applied.

    /**
     * There are 2 kinds of state transition:
     *  1. Change the state properties over time, or
     *  2. Change the problem scale over time
     * 
     * For this question, the circle continues to decrease as
     * the people leave. Thus, it may be the latter.
     * 
     * So the basic state is when n=1, we define f(n) as the state, and 
     * the state's result is the last departure person's number in the n-size 
     * circle. That is, f(1) = 1
     * 
     * Now try to figure out f(n) with an example, where N=8, M=4
     *          1
     *      8       2
     *  7               3
     *      6       4
     *          5
     * We will remove '4' at first, then '5' person starts to report
     * '1' again ...
     *          1
     *      8       2
     *  7               3
     *      6       
     *          5
     * Now there is a gap in the circle, which means we have to remember to skip 
     * 4 when we are counting the next round. But we don't wanna do that, just want 
     * to iterate through the circle fluently, to do this we can re-map the index again!
     * 
     * In state f(8), 5's index is 5. After removing 4, in state f(7), 5' index is 1. So:
     *                  oldIndex - m = newIndex
     * We don't want negative number, then:
     *                  oldIndex = newIndex + m
     * We don't want number overflow the current size, then:
     *                  oldIndex = (newIndex + m) % n
     * The index should be 1~n, not 0~n-1, then:
     *                  oldIndex = (newIndex + m - 1) % n + 1
     * There you go! We successfully get the mapping between two state, at the top level, 
     * the oldIndex is the Original Index!
     * 
     * So if we define f(n)->last depature index, we have the following state transition:
     *              f(n) = (f(n-1) + m - 1) % n + 1
     */


    
    public static void main(String[] args) {
        int res = new Solution18_2().lastPerson(8, 3);
        System.out.println(res);
    }
    
    // (state) -> result
    int lastPerson(int n, int m) {
        if (n == 1) {
            return 1;
        }
        return (lastPerson(n - 1, m) + m - 1) % n + 1;
    }
}

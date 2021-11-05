public class Solution18_2 {
    // Josephus problem follow up
    // use another algo to make time complexity to O(n), only need
    // to get the last depature person

    // We realize that each depature member is calculated based on 
    // previous one, that is we used the result of last subproblem.
    // Therefore, state transition can be applied.

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

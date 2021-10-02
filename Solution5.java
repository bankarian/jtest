import java.util.Scanner;

public class Solution5 {
    // 1. > half => +1
    // 2. < half => +0
    // 3. == half => check previous bit
    public static void main(String[] args) {
        Scanner sin = new Scanner(System.in);
        while (true) {
            String num = sin.next();
            int len = sin.nextInt();
            String res = new Solution5().reserve(num, len);
            System.out.println(res);
        }
    }

    // @param len how many decimals to reserve
    String reserve(String num, int len) {
        String[] parts = num.split("\\.");
        String a = parts[0];
        String b = parts[1];
        if (len >= b.length()) {
            return num;
        }

        int discard = b.length() - len;
        int discardBase = 1 << discard;
        int val = parse(b, len);
        String trimNum = new StringBuilder()
            .append(a).append(".")
            .append(b.substring(0, len))
            .toString();
        if (val * 2 < discardBase) {
            return ret(trimNum);
        }
        if (val * 2 > discardBase) {
            return ret(increase(trimNum));
        }
        return isOdd(trimNum) ? ret(increase(trimNum)) 
            : ret(trimNum);
    }

    boolean isOdd(String num) {
        return num.charAt(num.length() - 1) == '1';
    }

    String ret(String s) {
        if (s.charAt(s.length() - 1) == '.') {
            return s.substring(0, s.length() - 1);
        }
        return s;
    }

    String increase(String num) {
        char incr = '1';
        StringBuilder bd = new StringBuilder(num);
        for (int i = bd.length() - 1; i >= 0; i--) {
            if (incr == '0' || bd.charAt(i) == '.') continue;
            // 1 + 1
            if (bd.charAt(i) == '1') {
                bd.setCharAt(i, '0');
                continue;
            }
            // 1 + 0
            bd.setCharAt(i, '1');
            incr = '0';
        }
        if (incr == '1') 
            bd.insert(0, '1');
        return bd.toString();
    }

    int parse(String s, int start) {
        int offset = s.length() - start;
        int val = 0;
        for (int i = start; i < s.length(); i++) {
            if (s.charAt(i) == '1') {
                val += 1 << (offset - 1);
            }
            offset--;
        }
        return val;
    }
}

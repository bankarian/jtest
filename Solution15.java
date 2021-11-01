import java.util.HashMap;
import java.util.Map;

public class Solution15 {
    public static void main(String[] args) {
        String s = "abdcbagadsfcgeefo";
        String t = "bca";
        System.out.println(new Solution15().findSubString(s, t));
    }

    String findSubString(String s, String t) {
        for (char c: t.toCharArray()) {
            target.put(c, target.getOrDefault(c, 0) + 1);
        }

        Window res = new Window(0, s.length() + 1);
        Window sliding = new Window(0, 0);
        while (sliding.right < s.length()) {
            char ch = s.charAt(sliding.right++);
            inWindow.put(ch, inWindow.getOrDefault(ch, 0) + 1);

            while (containsAll(t)) {
                res = Window.min(res, sliding);
                int oldLeft = sliding.left++;
                ch = s.charAt(oldLeft);
                inWindow.put(ch, inWindow.get(ch) - 1);
            }
        }
        if (res.size() < s.length() + 1) {
            return s.substring(res.left, res.right);
        }
        return "";
    }

    // char -> exist times
    Map<Character, Integer> inWindow = new HashMap<>();

    // char -> exist times
    Map<Character, Integer> target = new HashMap<>();

    boolean containsAll(String t) {
        for (char ch: target.keySet()) {
            if (!inWindow.containsKey(ch)) {
                return false;
            }
            if (inWindow.get(ch) < target.get(ch)) {
                return false;
            }
        }
        return true;
    }

    

}

class Window {
    // [left, right)
    int left, right;

    Window(int l, int r) {
        this.left = l;
        this.right = r;
    }

    int size() {
        return right - left;
    }

    static Window min(Window w1, Window w2) {
        int size1 = w1.size();
        int size2 = w2.size();
        if (size1 > size2) {
            return new Window(w2.left, w2.right);
        }
        return new Window(w1.left, w1.right);
    }
}
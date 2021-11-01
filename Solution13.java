public class Solution13 {
    public static void main(String[] args) {
        String s = "CamelCaseStringTest";
        System.out.println(new Solution13().camelToUnderscore(s));
    }

    public String camelToUnderscore(String camelString) {
        StringBuilder bd = new StringBuilder();
        char[] s = camelString.toCharArray();
        for (int i = 0; i < s.length; i++) {
            if (isCapital(s[i])) {
                if (i > 0) {
                    bd.append('_');
                }
                bd.append(toLower(s[i]));
            } else {
                bd.append(s[i]);
            }
        }
        return bd.toString();
    }

    private boolean isCapital(char c) {
        return c >= 'A' && c <= 'Z';
    }

    private char toLower(char c) {
        return (char) (c + 'a' - 'A');
    }
}

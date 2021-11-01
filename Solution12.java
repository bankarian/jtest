import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Solution12 {
    public static void main(String[] args) {
        List<Good> lst = Stream.of(
            new Good(1, 2, 3),
            new Good(1, 2, 3),
            new Good(1, 2, 3),
            new Good(6, 3, 5),
            new Good(3, 3, 3)
        ).collect(Collectors.toCollection(ArrayList::new));
        int cnt = new Solution12().calculate(lst);
        System.out.println(cnt);
    }

    public int calculate(List<Good> lst) {
        List<Good> goods = new ArrayList<>(lst);
        goods.sort((g1, g2) -> {
            if (g1.a != g2.a) {
                return g1.a - g2.a;
            }
            if (g1.b != g2.b) {
                return g1.b - g2.b;
            }
            return g1.c - g2.c;
        });
        Good lastGood = goods.get(goods.size() - 1);
        return goods.stream()
                    .filter(g -> g.a < lastGood.a && 
                                g.b < lastGood.b && 
                                g.c < lastGood.c)
                    .collect(Collectors.toList())
                    .size();
    }
}

class Good {
    int a, b, c;
 
    Good (int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }
}
package advance;

// Binary Indexed Tree 树状数组
public class BIT {
    private int[] d;
    private int size;

    private int lowbit(int x) {
        return x & (-x);
    }

    public BIT(int _size) {
        size = _size;
        d = new int[size];
    }

    // query the sum of a[0...x]
    public int query(int x) {
        int res = 0;
        while (x > 0) {
            res += d[x];
            x -= lowbit(x);
        }
        return res;
    }

    // a[x] += v
    public void add(int x, int v) {
        while (x < n) {
            d[x] += v;
            x += lowbit(x);
        }
    }

}

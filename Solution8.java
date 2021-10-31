import java.util.HashMap;
import java.util.Map;

public class Solution8 {

}


class LRU<K, V> {
    public LRU(int cap) {
        capacity = cap;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public V get(K key) {
        if (!nodeMap.containsKey(key)) {
            return null;
        }
        Node node = nodeMap.get(key);
        moveToFront(node);
        return node.value;
    }

    public void put(K key, V value) {
        if (nodeMap.containsKey(key)) {
            Node node = nodeMap.get(key);
            node.value = value;
            moveToFront(node);
            return;
        }
        Node node = new Node(key, value);
        nodeMap.put(key, node);

        Node first = head.next;

        head.next = node;
        node.pre = head;

        node.next = first;
        first.pre = node;

        if (nodeMap.size() > capacity) {
            evictLast();
        }
    }

    private void evictLast() {
        Node oldLast = tail.pre;
        Node newLast = oldLast.pre;

        newLast.next = tail;
        tail.pre = newLast;

        oldLast.pre = oldLast.next = null;
        nodeMap.remove(oldLast.key);
    }

    private void moveToFront(Node node) {
        Node pre = node.pre, nxt = node.next;
      
        // unlink from origin position
        pre.next = nxt;
        nxt.pre = pre;

        // link to root
        Node first = head.next;

        head.next = node;
        node.pre = head;

        node.next = first;
        first.pre = node;
    }

    private Node head, tail;
    private int capacity;
    private Map<K, Node> nodeMap = new HashMap<>();


    class Node {
        K key;
        V value;
        Node next, pre;

        Node () {
        }

        Node (K k, V v) {
            key = k;
            value = v;
        }
    }
}
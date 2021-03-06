import javax.swing.*;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;             // root of BST

    private class Node {
        private K key;           // sorted by key
        private V val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(K key, V val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    /** Initializes an empty symbol table. */
    public BSTMap() {
    }

    /** Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    /** Returns the value to which the specified key is mapped,
     * or null if this map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return get(root, key);
    }

    private V get(Node n, K key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        if (n == null) {
            return null;
        } else if (key.compareTo(n.key) < 0) {
            return get(n.left, key);
        } else if (key.compareTo(n.key) > 0) {
            return get(n.right, key);
        } else {
            return n.val;
        }
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if (n == null) {
            return 0;
        } else {
            return n.size;
        }
    }

    /** Associates the specified value with the specified key in this map. */
    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    private Node put(Node n, K key, V value) {
        if (key == null) throw new IllegalArgumentException("calls put() with a null key");
        if (n == null) {
            return new Node(key, value, 1);
        } else if (key.compareTo(n.key) < 0) {
            n.left = put(n.left, key, value);
        } else if (key.compareTo(n.key) > 0) {
            n.right = put(n.right, key, value);
        } else {
            n.val = value;
        }
        n.size = 1 + size(n.left) + size(n.right);
        return n;
    }


    /* Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        throw new UnsupportedOperationException();
    }

    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException. */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(Node n) {
        if (n == null) {
            return;
        } else {
            printInOrder(n.left);
            System.out.println(n.key + ": " + n.val);
            printInOrder(n.right);
        }
    }

    public static void main(String[] args) {
        BSTMap<String, Integer> b = new BSTMap<>();
        b.put("cat", 1);
        b.put("dog", 2);
        b.put("apple", 3);
        b.put("bird", 4);
        b.printInOrder();
        System.out.println(b.get("apple"));
    }

}

package bstmap;

import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.function.Consumer;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private class Node {
        K key;
        V value;
        Node left, right;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        clear();
    }
    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为 null");
        }
        root = put(root,key, value);
    }
    private Node put(Node x, K key, V value) {
        if (x == null) {
            size++;
            return new Node(key,value);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, value);
        } else if (cmp > 0) {
            x.right = put(x.right, key, value);
        } else {
            x.value = value;
        }
        return x;
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("Key 不能为 null");
        }
        return containsKey(root, key);
    }

    private boolean containsKey(Node x, K key) {
        if (x == null) {
            return false;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return containsKey(x.left, key);
        } else if (cmp > 0) {
            return containsKey(x.right, key);
        } else {
            return true;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key 不能为 null");
        }
        return get(root, key);
    }

    private V get(Node root, K key) {
        if (root == null) {
            return null;
        }
        int cmp = key.compareTo(root.key);
        if (cmp < 0) {
            return get(root.left, key);
        } else if (cmp > 0) {
            return get(root.right, key);
        } else {
            return root.value;
        }
    }

    @Override
    public int size() {
        return size;
    }
    
    @Override
    public Set<K> keySet() {
        throw new IllegalArgumentException("key 不能为 null");
    }

    @Override
    public V remove(K key) {
        throw new IllegalArgumentException("key 不能为 null");
    }

    @Override
    public V remove(K key, V value) {
        throw new IllegalArgumentException("key 不能为 null");
    }

    @Override
    public Iterator<K> iterator() {
        throw new IllegalArgumentException("key 不能为 null");
    }
    public void printInOrder(Node x) {
        if (x != null) {
            printInOrder(x.left);
            System.out.println(x.key + ":" + x.value);
            printInOrder(x.right);
        }
    }

}

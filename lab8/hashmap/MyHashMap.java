package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {


    private static final int M = 16;
    private static final double N_M = 0.75;
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    // You should probably define some more!
    private int size;
    private int bucketCount;
    private double maxLoadFactor;
    private Set<K> keySet;//便于检查是否存在同个值
    /** Constructors */
    public MyHashMap() {
        this(M, N_M);
    }

    public MyHashMap(int initialSize) {
        this(initialSize, N_M);
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        if (initialSize <= 0) {
            throw new IllegalArgumentException("初始大小必须为正数");
        }
        if (maxLoad <= 0) {
            throw new IllegalArgumentException("最大负载因子必须为正数");
        }
        this.bucketCount = initialSize;
        this.maxLoadFactor = maxLoad;
        this.size = 0;
        this.keySet = new HashSet<>();
        this.buckets = createTable(bucketCount);
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }
    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new ArrayList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        Collection<Node>[] table = (Collection<Node>[]) new Collection[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!
    /** 哈希函数：将键映射到桶的索引 */
    private int hashIndex(K key) {
        int hash = key.hashCode() & 0x7fffffff;
        return hash % bucketCount;
    }
    @Override
    public void clear() {
        this.bucketCount = M;
        this.buckets = createTable(bucketCount);
        this.size = 0;
        this.keySet.clear();
    }
    @Override
    public boolean containsKey(K key) {
        int idx = hashIndex(key);
        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int idx = hashIndex(key);
        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                return node.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int idx = hashIndex(key);
        for (Node node : buckets[idx]) {
            if (key.equals(node.key)) {
                node.value = value;
                return;
            }
        }
        buckets[idx].add(createNode(key,value));
        keySet.add(key);
        size++;
        if ((double) size / bucketCount > maxLoadFactor) {
            resize(bucketCount * 2);
        }
    }
    private void resize(int newBucketCount) {
        Collection<Node>[] oldBuckets = buckets;
        buckets = createTable(newBucketCount);
        int oldSize = size;
        size = 0;
        bucketCount = newBucketCount;
        keySet.clear();
        for (Collection<Node> bucket : oldBuckets) {
            for (Node node : bucket) {
                put(node.key, node.value);
            }
        }
    }
    @Override
    public Set<K> keySet() {
        return new HashSet<>(keySet);
    }

    @Override
    public Iterator<K> iterator() {
        return keySet.iterator();
    }
    @Override
    public V remove(K key) {
        throw new IllegalArgumentException("key 错误");
    }

    @Override
    public V remove(K key, V value) {
        throw new IllegalArgumentException("key 错误");
    }
}

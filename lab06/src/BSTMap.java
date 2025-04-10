import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K,V>{

    private int size;
    private BSTNode root;

    // BST节点，每个节点含有key,item,left,right
    private class BSTNode {
        K key;
        V value;
        BSTNode left;
        BSTNode right;

        BSTNode(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    //空的初始化方法
    public BSTMap() {
        this.size = 0;
        root = null;
    }

    //带有参数的初始化方法
    public BSTMap(K key, V value) {
        this.size = 1;
        root = new BSTNode(key, value);
    }


    /**
     * Associates the specified value with the specified key in this map.
     * If the map already contains the specified key, replaces the key's mapping
     * with the value specified.
     *
     * @param key
     * @param value
     */
    @Override
    public void put(K key, V value) {
        root = put(root,key,value);
    }

    private BSTNode put(BSTNode node, K key, V value) {
        if (node == null) {
            //empty node just insert the kv
            node = new BSTNode(key, value);
            this.size ++;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            //key < node.key, go to left
            node.left = put(node.left, key, value);
        } else if (cmp > 0) {
            //key > node.key, go to right
            node.right = put(node.right, key, value);
        } else {
            //key == node.key, update the value
            node.value = value;
        }

        return node;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key
     */
    @Override
    public V get(K key) {
        return get(root,key);
    }

    private V get (BSTNode node, K key){
        if (node == null) {
            return null;
        }
        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return get(node.left, key);
        } else if (cmp > 0) {
            return get(node.right, key);
        } else {
            return node.value;
        }
    }

    /**
     * Returns whether this map contains a mapping for the specified key.
     *
     * @param key
     */
    @Override
    public boolean containsKey(K key) {
        return containsKey(root,key) != null;
    }

    private BSTNode containsKey(BSTNode node,K key){
        if(node == null){
            return null;
        }
        int cmp = key.compareTo(node.key);
        if(cmp < 0){
            node.left = containsKey(node.left,key);
        }else if(cmp > 0){
            node.right = containsKey(node.right,key);
        }

        return node;

    }

    /**
     * Returns the number of key-value mappings in this map.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Removes every mapping from this map.
     */
    @Override
    public void clear() {
        this.root = null;
        this.size = 0;
    }

    /**
     * Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException.
     */
    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    /**
     * Removes the mapping for the specified key from this map if present,
     * or null if there is no such mapping.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException.
     *
     * @param key
     */
    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HashMap<K,V> {
	public class Node<K, V> {
		final K key;
		V value;
		Node<K, V> next;
		Node(K key, V value) {
			this.key = key;
			this.value = value;
		}
		
		public K getKey() {
			return key;	
		}
		public V getValue() {
			return value;
		}
		public void setValue(V value) {
			this.value = value;
		}
		
	}
	
	private static final int INIT_CAP = 16;
	private static final double LOAD_FACTOR = 0.7;
	private Node<K, V> [] array;
	private int size;
	
	public void MyHashMap() {
		array = (Node<K, V>[]) new Node[INIT_CAP];
		size = 0;
		
	}
	
	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public void clear() {
		Arrays.fill(array, null);
		size = 0;
	}
	
	public V get(K key) {
		Node<K, V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return node.getValue();
			}
			node = node.next;
		}
		return null;
	}
	
	public boolean containsKey(K key) {
		Node<K, V> node = array[index(key)];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				return true;
			}
			node = node.next;
		}
		return false;
	}
	
	public V put(K key, V value) {
		int i = index(key);
		Node<K, V> node = array[i];
		while (node != null) {
			if (equalsKey(node.getKey(), key)) {
				V oldValue = node.getValue();
				node.setValue(value);
				return oldValue;
			}
			node = node.next;
		}
		Node<K, V> newEntry = new Node<>(key, value);
		newEntry.next = array[i];
		array[i] = newEntry;
		size++;
		if (needRehashing()) {
			rehash();
		}
		return null;
	}
	
	private boolean needRehashing() {
		return size > LOAD_FACTOR * array.length;
	}
	
	private void rehash() {
		Node<K, V> [] old = array;
		array = (Node<K, V> []) new Node[old.length * 2];
		for (Node<K, V> e: old) {
			while (e != null) {
				Node<K, V> next = e.next;
				
			}
		}
	}
	
	private int index(K key) {
		return hash(key) % array.length;
	}
	
	private int hash(K key) {
		if (key == null) {
			return 0;
		}
		 int hashNumber = key.hashCode();
		 return hashNumber & 0x7fffffff;
	}
	
	private int getIndex(int hashNumber) {
		return hashNumber % array.length;	
	}
	
	private boolean equalsKey(K a, K b) {
		return a == b || ( a != null && a.equals(b));
	}
	
	class Solution{
		public void bucketSort(double [] array) {
			int n = array.length;
			List<List<Double>> buckets = new ArrayList<>();
			for (double x : array) {
				
			}
		}
	}

}

import java.util.HashMap;
//Code using Doubly Linked List and HashMap: 
class Node {
    int key;
    int value;
    Node pre;
    Node next;

    public Node(int key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

class LRUCache {
    private HashMap<Integer, Node> map;
    private int capacity, count;
    private Node head, tail;

    public LRUCache(int capacity)
    {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
        tail.next = null;
        count = 0;
    }

    public void deleteNode(Node node)
    {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void addToHead(Node node)
    {
        node.next = head.next;
        node.next.pre = node;
        node.pre = head;
        head.next = node;
    }

    // This method works in O(1)
    public int get(int key)
    {
        if (map.get(key) != null) {
            Node node = map.get(key);
            int result = node.value;
            deleteNode(node);
            addToHead(node);
            System.out.println("Got the value : " + result
                               + " for the key: " + key);
            return result;
        }
        System.out.println("Did not get any value"
                           + " for the key: " + key);
        return -1;
    }
	
	void print(){
		System.out.println("-------------------------------------------");
			for (Integer name: map.keySet()) {
				String key = name.toString();
				String value = map.get(name).toString();
				System.out.println("Key="+key + "  " + value+"  Value="+map.get(name).value);
				System.out.println("       map.get(name).pre.value  Value ="+map.get(name).pre.value);
				System.out.println("       map.get(name).next.value  Value ="+map.get(name).next.value);
			}
			System.out.println("-------------------------------------------");
		}
	void getHeadTailValue(){
			System.out.println("---------------getHeadTailValue()----------------------------");
			System.out.print("   head ="+head.value);
			//System.out.print("   head pre ="+(Integer.parseInt(head.pre.value)=true? head.pre.value:null) );
			System.out.print("   head next ="+head.next.value);
			System.out.print("   head next next ="+head.next.next.value);
			System.out.print("   tail ="+tail.value);

			System.out.print("   tail pre ="+tail.pre.value);
									System.out.println();
			//System.out.print("   tail next ="+tail.next.value);	
			System.out.println("----------------------------------------------------------");
		
		}


    // This method works in O(1)
    public void set(int key, int value)
    {
        System.out.println("Going to set the (key, "
                           + "value) : (" + key + ", "
                           + value + ")");
        if (map.get(key) != null) {
            Node node = map.get(key);
            node.value = value;
            deleteNode(node);
            addToHead(node);
        }
        else {
            Node node = new Node(key, value);
            map.put(key, node);
            if (count < capacity) {
                count++;
                addToHead(node);
					System.out.print("*****Test in cycle1 =");
					print();
            }
            else {
                map.remove(tail.pre.key);
                deleteNode(tail.pre);
                addToHead(node);
					System.out.print("#####Test in cycle2  =");
					print();
            }
        }
    }
}

public class LRU {
    public static void main(String[] args)
    {
        System.out.println("Going to test the LRU "
                           + " Cache Implementation");
        LRUCache cache = new LRUCache(2);

        // it will store a key (1) with value
        // 10 in the cache.
        cache.set(1, 10);

        // it will store a key (1) with value 10 in the
        // cache.
        cache.set(2, 20);
			System.out.println("Value for the key: 1 is "+ cache.get(1)); // returns 10
			System.out.println("=======  After cache.set(1, 10) and cache.set(2, 20);  =======");	
			cache.print();	
			cache.getHeadTailValue();			 

        // evicts key 2 and store a key (3) with
        // value 30 in the cache.
        cache.set(3, 30);
			System.out.println("Value for the key: 2 is "  + cache.get(2)); // returns -1 (not found)
			System.out.println("=======  After cache.set(3, 30);  =======");	
			cache.print();	
			cache.getHeadTailValue();

        // evicts key 1 and store a key (4) with
        // value 40 in the cache.
        cache.set(4, 40);
			System.out.println("=======  After cache.set(3, 30);  =======");	
			cache.print();	
			cache.getHeadTailValue();		
		
        System.out.println("Value for the key: 1 is "+ cache.get(1)); // returns -1 (not found)
        System.out.println("Value for the key: 3 is "
                           + cache.get(3)); // returns 30
        System.out.println("Value for the key: 4 is "
                           + cache.get(4)); // return 40
						   
		System.out.println("======   After cache.set(4, 40);  =======");	
			 cache.print();

		
    }
}
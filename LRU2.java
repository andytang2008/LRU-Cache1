import java.util.HashMap;
//Code using Doubly Linked List and HashMap: 
class NodeX {
    int key;
    int value;
    NodeX pre;
    NodeX next;

    public NodeX(int key, int value)
    {
        this.key = key;
        this.value = value;
    }
}

class LRUCache2 {
    private HashMap<Integer, NodeX> map;
    private int capacity, count;
    private NodeX head, tail;

    public LRUCache2(int capacity)
    {
        this.capacity = capacity;
        map = new HashMap<>();
        head = new NodeX(0, 0);
        tail = new NodeX(0, 0);
        head.next = tail;
        tail.pre = head;
        head.pre = null;
       tail.next = null;
	           //head.pre = null;
       // tail.next = null;
        count = 0;
    }

    public void deleteNodeX(NodeX NodeX)
    {
        NodeX.pre.next = NodeX.next;
        NodeX.next.pre = NodeX.pre;
    }

    public void addToHead(NodeX NodeX)
    {
        NodeX.next = head.next;
        NodeX.next.pre = NodeX;
        NodeX.pre = head;
        head.next = NodeX;
    }

    // This method works in O(1)
    public int get(int key)
    {
        if (map.get(key) != null) {
            NodeX NodeX = map.get(key);
            int result = NodeX.value;
            deleteNodeX(NodeX);
            addToHead(NodeX);
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
            NodeX NodeX = map.get(key);
            NodeX.value = value;
            deleteNodeX(NodeX);
            addToHead(NodeX);
        }
        else {
            NodeX NodeX = new NodeX(key, value);
            map.put(key, NodeX);
            if (count < capacity) {
                count++;
                addToHead(NodeX);
					System.out.print("*****Test in cycle1 =");
					print();
            }
            else {
                map.remove(tail.pre.key);
                deleteNodeX(tail.pre);
                addToHead(NodeX);
				
					System.out.print("#####Test in cycle2  =");
					print();
            }
        }
    }
}

public class LRU2 {
    public static void main(String[] args)
    {
        System.out.println("Going to test the LRU "
                           + " Cache Implementation");
        LRUCache2 cache = new LRUCache2(2);

        // it will store a key (1) with value
        // 10 in the cache.
        cache.set(1, 10);
				cache.getHeadTailValue();
        // it will store a key (1) with value 10 in the
        // cache.
        cache.set(2, 20);
				cache.getHeadTailValue();
       // System.out.println("Value for the key: 1 is "+ cache.get(1)); // returns 10
						   
		System.out.println("********  After cache.set(1, 10) and cache.set(2, 20);  ********");	
			 cache.print();		
		     cache.getHeadTailValue();

        // evicts key 2 and store a key (3) with
        // value 30 in the cache.
        cache.set(3, 30);
 	
     //   System.out.println("Value for the key: 2 is "  + cache.get(2)); // returns -1 (not found)
			
			
		System.out.println("******** After cache.set(3, 30);  ********");	
			 cache.print();	
			 cache.getHeadTailValue();

        // evicts key 1 and store a key (4) with
        // value 40 in the cache.
        cache.set(4, 40);
		
		System.out.println("******** After cache.set(3, 30);  ********");	
			 cache.print();	
			 cache.getHeadTailValue();
			 
       // System.out.println("Value for the key: 1 is "+ cache.get(1)); // returns -1 (not found)
        System.out.println("Value for the key: 3 is "
                           + cache.get(3)); // returns 30
        System.out.println("Value for the key: 4 is "
                           + cache.get(4)); // return 40
						   
		System.out.println("======   After cache.set(4, 40);  =======");	
			 cache.print();

		
    }
}
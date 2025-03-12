// Time Complexity : O(1) for all methods.
// Space Complexity : O(capacity).
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no

import java.util.*;
class LRUCache {
    class listnode
    {
        int key;
        int value;
        listnode prev;
        listnode next;

        listnode(int key, int val)
        {
            this.key = key;
            value = val;
            prev = null;
            next = null;
        }
    }

    HashMap<Integer, listnode> map;
    int cap;
    listnode head;
    listnode tail;
    public LRUCache(int capacity) {
        cap = capacity;
        map = new HashMap<>();
        head = new listnode(-1,-1);
        tail = new listnode(-1,-1);
        head.next = tail;
        tail.prev = head;
    }
    private void removeNode(listnode node)
    {
        node.next.prev = node.prev;
        node.prev.next = node.next;
    }
    private void addAtHead(listnode node)
    {
        node.next = head.next;
        node.prev = head;
        head.next = node;
        node.next.prev = node;
    }
    public int get(int key) {
        if(!map.containsKey(key)) return -1;

        listnode node = map.get(key);
        removeNode(node);
        addAtHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key))
        {
            listnode node = map.get(key);
            node.value = value;
            removeNode(node);
            addAtHead(node);
        }
        else
        {
            if(map.size() == cap)
            {
                listnode tailprev = tail.prev;
                removeNode(tailprev);
                map.remove(tailprev.key);
            }
                
            listnode node = new listnode(key, value);
            map.put(key, node);
            addAtHead(node);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
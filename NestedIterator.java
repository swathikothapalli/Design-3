/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer, rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds, if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds, if it holds a nested list
 *     // Return empty list if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */

// Time Complexity : worst case O(h) where h is the stack height but TC will be Amortized O(1) for next and hasnext.
// Space Complexity : O(h) stack space.
// Did this code successfully run on Leetcode : yes
// Any problem you faced while coding this : no


import java.util.*;
interface NestedInteger {
    public boolean isInteger();
    public Integer getInteger();
    public List<NestedInteger> getList();
}

class NestedIterator implements Iterator<Integer> {
    Stack<Iterator<NestedInteger>> stk;
    NestedInteger nextElement;
    public NestedIterator(List<NestedInteger> nestedList) {
        stk = new Stack<>();
        stk.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        return nextElement.getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stk.isEmpty())
        {
            if(!stk.peek().hasNext()) stk.pop();
            else if((nextElement = stk.peek().next()).isInteger())
                return true;
            else
                stk.push(nextElement.getList().iterator());
        }
        return false;
    }
}

/**
 * Your NestedIterator object will be instantiated and called as such:
 * NestedIterator i = new NestedIterator(nestedList);
 * while (i.hasNext()) v[f()] = i.next();
 */
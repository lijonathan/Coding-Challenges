import java.util.ArrayList;

public class Min_Stack {
/*
Rating: Easy

Design a stack that supports push, pop, top, and retrieving the minimum
element in constant time.

    push(x) -- Push element x onto stack.
    pop() -- Removes the element on top of the stack.
    top() -- Get the top element.
    getMin() -- Retrieve the minimum element in the stack.



Example 1:

Input
["MinStack","push","push","push","getMin","pop","top","getMin"]
[[],[-2],[0],[-3],[],[],[],[]]

Output
[null,null,null,null,-3,null,0,-2]

Explanation
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.getMin(); // return -3
minStack.pop();
minStack.top();    // return 0
minStack.getMin(); // return -2



Constraints:

    Methods pop, top and getMin operations will always be called on non-empty stacks.
*/

/*
    Approach: ArrayList that pushes onto end of list and removes from end of list. ArrayList
              holds a Pair object that consists of the value and the minimum of the value of the stack
              up to that point. The minimum tracking in the Pair class allows for O(1) access to the min
              value at all times.
*/


    /** initialize your data structure here. */

    class Pair
    {
        int val;
        int min;
        public Pair(int val, int min)
        {
            this.val = val;
            this.min = min;
        }
    }
    // O(1) time for push, pop, top, getMin
    // O(n) space
    // Runtime: 4 ms, faster than 93.82% of Java online submissions
    // Memory Usage: 41.2 MB, less than 55.52% of Java online submissions
    ArrayList<Pair> stack;
    public Min_Stack() {
        stack = new ArrayList<Pair>();
    }

    public void push(int x) {
        int min = x;
        if (stack.size() > 0)
        {
            min = Math.min(x, stack.get(stack.size() - 1).min);
        }
        Pair p = new Pair(x, min);
        stack.add(p);
    }

    public void pop() {
        stack.remove(stack.size() - 1);
    }

    public int top() {
        return stack.get(stack.size() - 1).val;
    }

    public int getMin() {
        return stack.get(stack.size() - 1).min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

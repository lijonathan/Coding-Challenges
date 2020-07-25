import java.util.Stack;

public class Binary_Search_Tree_Iterator {
/*
Rating: Medium

Implement an iterator over a binary search tree (BST). Your iterator
will be initialized with the root node of a BST.

Calling next() will return the next smallest number in the BST.

 

Example:

BSTIterator iterator = new BSTIterator(root);
iterator.next();    // return 3
iterator.next();    // return 7
iterator.hasNext(); // return true
iterator.next();    // return 9
iterator.hasNext(); // return true
iterator.next();    // return 15
iterator.hasNext(); // return true
iterator.next();    // return 20
iterator.hasNext(); // return false


Note:

    next() and hasNext() should run in average O(1) time and uses O(h) memory, where h is the height of the tree.
    You may assume that next() call will always be valid, that is, there will be at least a next smallest number in
    the BST when next() is called.
*/


/*
    Approach: Create a stack that initially contains all values of calling root.left until
              the leaf is reached. After each pop from a next() call, check if the current popped
              off node has any right children. If not, continue, otherwise add the current node's right
              child and calls to root.left until it hits a leaf. Continue this process.

              The stack contains a one slice height of nodes in the tree.

              Worst case average for next() is O(n) for completely unbalanced tree to the left. The
              populate_stack() function only gets called for nodes with right children and only
              runs for O(n) in the case of completely skewed trees.
*/

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    // O(1) time for hasNext, O(1) time average for next(), O(n) time worst case
    // O(h) space, h = depth of the tree
    // Runtime: 15ms, faster than 93.73% of Java online submissions
    // Memory Usage: 44.3 MB, less than 72.38% of Java online submissions
    Stack<TreeNode> depth_stack;
    public Binary_Search_Tree_Iterator(TreeNode root) {
        depth_stack = new Stack<TreeNode>();
        populate_stack(root);
    }

    private void populate_stack(TreeNode curr)
    {
        while(curr != null)
        {
            depth_stack.push(curr);
            curr = curr.left;
        }
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode pop = depth_stack.pop();
        if (pop.right != null)
        {
            populate_stack(pop.right);
        }
        return pop.val;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !depth_stack.empty();
    }
}

/**
 * Your BSTIterator object will be instantiated and called as such:
 * BSTIterator obj = new BSTIterator(root);
 * int param_1 = obj.next();
 * boolean param_2 = obj.hasNext();
 */

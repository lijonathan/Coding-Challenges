import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Preorder_Traversal {
/*
Rating: Medium

Given a binary tree, return the preorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,2,3]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*
    Approach: preorder : root, left, right
              Push root onto stack. Pop stack and if not null,
              push right child and then push left child. Repeat while the stack is
              not empty.
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
    // O(n) runtime
    // O(n) space for the stack and a completely unbalanced tree
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 37.6 MB, less than 83.93% of Java online submissions
    public List<Integer> preorderTraversal(TreeNode root) {
        // preorder, root, left, right
        Stack<TreeNode> stack = new Stack<TreeNode>();
        ArrayList<Integer> preorder = new ArrayList<Integer>();
        if (root != null)
        {
            stack.push(root);
        }
        while(!stack.empty())
        {
            TreeNode curr = stack.pop();
            preorder.add(curr.val);
            if (curr.right != null)
            {
                stack.push(curr.right);
            }
            if (curr.left != null)
            {
                stack.push(curr.left);
            }
        }
        return preorder;
    }
}

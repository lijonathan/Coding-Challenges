import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Binary_Tree_Inorder_Traversal {
/*
Rating: Medium

Given a binary tree, return the inorder traversal of its nodes' values.

Example:

Input: [1,null,2,3]
   1
    \
     2
    /
   3

Output: [1,3,2]

Follow up: Recursive solution is trivial, could you do it iteratively?
*/

/*
    Approach: Inorder : left, root, right

              While root is not null, push all left children onto the stack. Pop off the stack.
              If right is not null, push right onto the stack, and then while not null,
              push all left onto stack. Repeat while the stack is not empty.
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
    // O(n) space for completely unbalanced tree
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38 MB, less than 49.84% of Java online submissions
    public List<Integer> inorderTraversal(TreeNode root) {
        ArrayList<Integer> inorder = new ArrayList<Integer>();
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null)
        {
            stack.push(root);
            root = root.left;
        }
        while(!stack.empty())
        {
            TreeNode curr = stack.pop();
            inorder.add(curr.val);
            TreeNode right = curr.right;
            while (right != null)
            {
                stack.push(right);
                right = right.left;
            }
        }
        return inorder;
    }
}

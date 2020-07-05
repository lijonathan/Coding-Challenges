public class Minimum_Depth_of_Binary_Tree {
/*
Rating: Easy

Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the
shortest path from the root node down to the nearest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its minimum depth = 2.
*/


/*
    Approach: Use DFS to return the minimum depth. If the current node is null, return 0.
              If the left child is null, recurse on the right child and return the return value +1.
              Same for if the right child is null, recurse on left and return the return value + 1.

              If both children are null, return 1 since it is a leaf node.

              Otherwise, recurse on both left and right and return the smallest value + 1.
*/


    // O(n) time to visit each node
    // O(n) space for the call stack for an unbalanced tree
    // Runtime: 0ms beats 100.00% of Java online submissions
    // Memory Usage: 39.2MB beats 85.79% of Java online submissions


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

    public int minDepth(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        else if (root.left == null)
        {
            return minDepth(root.right) + 1;
        }
        else if (root.right == null)
        {
            return minDepth(root.left) + 1;
        }
        else
        {
            int left = minDepth(root.left) + 1;
            int right = minDepth(root.right) + 1;
            return Math.min(left, right);
        }
    }
}

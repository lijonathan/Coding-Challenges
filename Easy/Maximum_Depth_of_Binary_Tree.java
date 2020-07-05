public class Maximum_Depth_of_Binary_Tree {
/*
Rating: Easy

Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path
from the root node down to the farthest leaf node.

Note: A leaf is a node with no children.

Example:

Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its depth = 3.
*/


/*
    Approach: If the current node is null, return 0. Otherwise, recurse on the left and right
              children and increment their return values by 1. Return the greater of the two.
*/


    // O(n) runtime where n = # nodes in tree
    // O(n) space for recursive stack and completely unbalanced tree
    // Runtime: 0ms beats 100.00% of Java online submissions
    // Memory Usage: 39.6MB beats 48.98% of Java online submissions

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

    public int maxDepth(TreeNode root) {
        if (root == null)
        {
            return 0;
        }
        else
        {
            int left = maxDepth(root.left) + 1;
            int right = maxDepth(root.right) + 1;
            return Math.max(left, right);
        }
    }
}

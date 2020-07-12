public class Diameter_of_Binary_Tree {
/*
Rating: Easy

Given a binary tree, you need to compute
the length of the diameter of the tree. The diameter
of a binary tree is the length of the longest path between
any two nodes in a tree. This path may or may not pass
through the root.

Example:
Given a binary tree

          1
         / \
        2   3
       / \     
      4   5    

Return 3, which is the length of the path [4,2,1,3] or [5,2,1,3].

Note: The length of path between two nodes is represented by
      the number of edges between them. 
*/

/*
    Approach: At each node, calculate the max distance between the left and right subtree.
              --> Calculate max left height and max right height and sum them together.

              Set a tracker for max difference seen so far. i.e. Array of size 1 as input value.
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
    // O(n) space for recursive call stack
    // Runtime: 1 ms, faster than 30.89% of Java online submissions
    // Memory Usage: 41.1 MB, less than 13.07% of Java online submissions
    public int max_dist(TreeNode root, int[] max)
    {
        if (root == null)
        {
            return 0;
        }
        else
        {
            int left = max_dist(root.left, max) + 1;
            int right = max_dist(root.right, max) + 1;
            if (left + right - 2 > max[0])
            {
                max[0] = left + right - 2;
            }
            return Math.max(left, right);
        }
    }
    
    public int diameterOfBinaryTree(TreeNode root) {

        int[] max = new int[1];
        max_dist(root, max);
        return max[0];
    }
}

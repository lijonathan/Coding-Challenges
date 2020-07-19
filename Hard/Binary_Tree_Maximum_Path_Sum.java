public class Binary_Tree_Maximum_Path_Sum {
/*
Rating: Hard

Given a non-empty binary tree, find the maximum path sum.

For this problem, a path is defined as any sequence of nodes
from some starting node to any node in the tree along the
parent-child connections. The path must contain at least one
node and does not need to go through the root.

Example 1:

Input: [1,2,3]

       1
      / \
     2   3

Output: 6

Example 2:

Input: [-10,9,20,null,null,15,7]

   -10
   / \
  9  20
    /  \
   15   7

Output: 42
*/

/*
    Approach: Traverse the tree in a DFS manner. At each recursive call, get the sum
              of the left child and the right child. Take the values of left, right,
              root, left and root, right and root, and right-root-left, and current greatest.
              Set greatest to the largest of those values. Return the greater of right + root or
              left + root.
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
    // O(n) runtime, each node in the tree gets visited once
    // O(n) space for the recursive call stack
    // Runtime: 1 ms, faster than 48.77% of Java online submissions
    // Memory Usage: 41.3 MB, less than 65.11% of Java online submissions
    public Integer traverse(TreeNode root, int[] greatest)
    {
        if (root == null)
        {
            return null;
        }
        else
        {
            Integer left_sum = traverse(root.left, greatest);
            Integer right_sum = traverse(root.right, greatest);

            int left_and_root = root.val;
            int right_and_root = root.val;
            int total_three = root.val;
            if (left_sum != null)
            {
                left_and_root += left_sum;
                total_three += left_sum;
            }
            else
            {
                left_sum = Integer.MIN_VALUE;
            }
            if (right_sum != null)
            {
                right_and_root += right_sum;
                total_three += right_sum;
            }
            else
            {
                right_sum = Integer.MIN_VALUE;
            }
            greatest[0] = Math.max(root.val, Math.max(greatest[0],
                                    Math.max(left_and_root, Math.max(right_and_root,
                                    Math.max(total_three, Math.max(left_sum, right_sum))))));
            return Math.max(root.val, Math.max(left_and_root, right_and_root));
        }
    }
    public int maxPathSum(TreeNode root) {
        int[] greatest = new int[1];
        greatest[0] = Integer.MIN_VALUE;
        traverse(root, greatest);
        return greatest[0];
    }
}

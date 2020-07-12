public class Path_Sum {
/*
Rating: Easy

Given a binary tree and a sum, determine if the
tree has a root-to-leaf path such that adding up
all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1

return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
*/

/*
    Approach: DFS down the tree and for each call to the child node, pass the curr_sum + root.val.
              If both child nodes are null --> leaf node, check if sum = target. Return true, otherwise
              return false from the leaf node. At non-leaf node, return left recursive call || right recursive
              call because only one needs to match to target. That return gets passed up to tree as one of the parents
              children return calls.
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
    // O(n) space for recursive stack of DFS and worst case completely unbalanced tree
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.6 MB, less than 34.62% of Java online submissions
    public boolean sum(TreeNode root, int target, int curr_sum)
    {
        if (root != null)
        {
            curr_sum += root.val;
            if (root.left == null && root.right == null && curr_sum == target)
            {
                return true;
            }
            else if (root.left == null && root.right == null)
            {
                return false;
            }
            else
            {
                return sum(root.left, target, curr_sum) || sum(root.right, target, curr_sum);
            }
        }
        else
        {
            return false;
        }
    }

    public boolean hasPathSum(TreeNode root, int sum) {
        return sum(root, sum, 0);
    }
}

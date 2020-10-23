public class Validate_Binary_Search_Tree {
/*
Rating: Medium

Given a binary tree, determine if it is a valid binary search tree (BST).

Assume a BST is defined as follows:

    The left subtree of a node contains only nodes with keys less than the node's key.
    The right subtree of a node contains only nodes with keys greater than the node's key.
    Both the left and right subtrees must also be binary search trees.



Example 1:

    2
   / \
  1   3

Input: [2,1,3]
Output: true

Example 2:

    5
   / \
  1   4
     / \
    3   6

Input: [5,1,4,null,null,3,6]
Output: false
Explanation: The root node's value is 5 but its right child's value is 4.
*/

    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38.7 MB, less than 98.39% of Java online submissions
    // O(n) runtime
    // O(n) space for stack in completely unbalanced tree

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public boolean check_valid(TreeNode curr, Integer min, Integer max)
    {
        if (curr == null)
        {
            return true;
        }
        else if ((min != null && curr.val <= min) || (max != null && curr.val >= max))
        {
            return false;
        }
        return check_valid(curr.left, min, max == null ? curr.val : Math.min(max, curr.val)) &&
                check_valid(curr.right, min == null ? curr.val : Math.max(min, curr.val), max);
    }
    public boolean isValidBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null))
        {
            return true;
        }
        return check_valid(root, null, null);
    }
}

public class Subtree_of_Another_Tree {

/*
Rating: Easy

Given two non-empty binary trees s and t, check whether tree t
has exactly the same structure and node values with a subtree of s.
A subtree of s is a tree consists of a node in s and all of this
node's descendants. The tree s could also be considered as a subtree of itself.

Example 1:
Given tree s:

     3
    / \
   4   5
  / \
 1   2

Given tree t:

   4 
  / \
 1   2

Return true, because t has the same structure and node values with a subtree of s.



Example 2:
Given tree s:

     3
    / \
   4   5
  / \
 1   2
    /
   0

Given tree t:

   4
  / \
 1   2

Return false. 
*/

/*
    Approach: If the root of the subtree and the current node in the larger tree have the same value
              check if the smaller tree and the current subtree are equal. Recurse through both trees
              checking that that the smaller tree and the current subtree are equal.
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
    // O(m * n) where n is the size of the larger tree and m is the size of the smaller tree
    // O(n) for a completely skewed tree
    // Runtime: 5 ms, faster than 97.22% of Java online submissions
    // Memory Usage: 39.5 MB, less than 50.55% of Java online submissions
    public boolean sameTree(TreeNode s, TreeNode t)
    {
        if (s != t && (s == null || t == null))
        {
            return false;
        }
        else if (s == null && t == null)
        {
            return true;
        }
        else if (s.val != t.val)
        {
            return false;
        }
        else
        {
            return sameTree(s.left, t.left) && sameTree(s.right, t.right);
        }
    }

    public boolean isSubtree(TreeNode s, TreeNode t) {

        if (s == null)
        {
            return false;
        }
        else if (s.val == t.val && sameTree(s, t))
        {
            return true;
        }
        else
        {
            return isSubtree(s.left, t) || isSubtree(s.right, t);
        }
    }
}

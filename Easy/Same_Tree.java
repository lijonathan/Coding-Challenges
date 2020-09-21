public class Same_Tree {
/*
Rating: Easy

Given two binary trees, write a function to check if they are the same or not.

Two binary trees are considered the same if they are structurally
identical and the nodes have the same value.

Example 1:

Input:     1         1
          / \       / \
         2   3     2   3

        [1,2,3],   [1,2,3]

Output: true

Example 2:

Input:     1         1
          /           \
         2             2

        [1,2],     [1,null,2]

Output: false

Example 3:

Input:     1         1
          / \       / \
         2   1     1   2

        [1,2,1],   [1,1,2]

Output: false
*/

/*
  Approach: Recurse through both trees. If both nodes are null, return true, otherwise
            if one is and another is not return false. If the values are not equal, return false.
            Return both the left and right subtrees are equal.
*/

  // O(n) runtime
  // O(n) space for recursive call stack
  // Runtime: 0 ms, faster than 100.00% of Java online submissions
  // Memory Usage: 37.3 MB, less than 53.10% of Java online submissions

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
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null)
        {
            return true;
        }
        else if ( (p != null && q == null) || (p == null && q != null) )
        {
            return false;
        }
        else if (p.val != q.val)
        {
            return false;
        }
        else
        {
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }
    }
}

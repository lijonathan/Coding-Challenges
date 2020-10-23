import java.util.ArrayList;
import java.util.List;

public class Find_Leaves_of_Binary_Tree {
/*
Rating: Medium

Given a binary tree, collect a tree's nodes as if you were doing this:
Collect and remove all leaves, repeat until the tree is empty.

Example:

Input: [1,2,3,4,5]

          1
         / \
        2   3
       / \     
      4   5    

Output: [[4,5,3],[2],[1]]


Explanation:

1. Removing the leaves [4,5,3] would result in this tree:

          1
         / 
        2          



2. Now removing the leaf [2] would result in this tree:

          1          



3. Now removing the leaf [1] would result in the empty tree:

          []         

[[3,5,4],[2],[1]], [[3,4,5],[2],[1]], etc, are also consider correct
answers since per each level it doesn't matter the order on which elements
are returned.
*/

    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38.1 MB, less than 38.29% of Java online submissions
    // O(n) runtime
    // O(n) space for stack space of completely unbalanced tree
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public int traverse(TreeNode root, ArrayList<List<Integer>> levels)
    {
        if (root == null)
        {
            return -1;
        }
        else
        {
            int max_depth = Math.max(traverse(root.left, levels), traverse(root.right, levels)) + 1;
            if (max_depth > levels.size() - 1)
            {
                levels.add(new ArrayList<Integer>());
            }
            levels.get(max_depth).add(root.val);
            return max_depth;
        }
    }

    public List<List<Integer>> findLeaves(TreeNode root) {
        ArrayList<List<Integer>> levels = new ArrayList<List<Integer>>();
        traverse(root, levels);
        return levels;
    }
}

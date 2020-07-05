import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Level_Order_Traversal {
/*
Rating: Medium

Given a binary tree, return the level order traversal of its nodes'
values. (ie, from left to right, level by level).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its level order traversal as:

[
  [3],
  [9,20],
  [15,7]
]
*/

/*
    Approach: DFS to traverse the tree. Track the depth through a function argument and at each depth
              check if the current depth is in the arraylist by making sure the size of the arraylist
              is greater than the current depth. Append the node onto the current depth's arraylist.

              Left to right occurs by calling left child before right in DFS.
*/


    // O(n) time, n = # nodes in tree
    // O(1) space excluding recursive call stack -- O(n) worst case for unbalanced tree
    // Runtime: 0ms beats 100.00% of Java online submissions
    // Memory Usage: 39.8MB beats 43.28% of java online submissions

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

    public void dfs(TreeNode root, ArrayList<List<Integer>> levels, int depth)
    {
        if (root == null)
        {
            return;
        }
        else
        {
            if (levels.size() > depth)
            {
                List<Integer> curr_depth = levels.get(depth);
                curr_depth.add(root.val);
            }
            else
            {
                ArrayList<Integer> curr_depth = new ArrayList<Integer>();
                curr_depth.add(root.val);
                levels.add(curr_depth);
            }
            dfs(root.left, levels, depth + 1);
            dfs(root.right, levels, depth + 1);
        }
    }
    public List<List<Integer>> levelOrder(TreeNode root) {
        ArrayList<List<Integer>> levels = new ArrayList<List<Integer>>();
        dfs(root, levels, 0);
        return levels;
    }
}

import java.util.ArrayList;
import java.util.List;

public class Path_Sum_II {
/*
Rating: Medium

Given a binary tree and a sum, find all root-to-leaf
paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1

Return:

[
   [5,4,11,2],
   [5,8,4,5]
]
*/

/*
    Approach: DFS down the tree with a tracked path ArrayList and current sum. Increment sum with
              current node value at each recursive stack frame. Once a leaf is reached, if it equals
              the target, copy the list into another list and put into the answer list.

              Prior to each recursive call stack returning, remove the current node from the path
              arraylist.
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

    // O(n^2) time, full tree, each leaf node requires copy n worst case(completely unbalanced tree) -> n depth
    // O(n) space for the curr_path list, disregarding the output list

    // Runtime: 1 ms, faster than 99.88% of Java online submissions
    // Memory Usage: 39.9 MB, less than 60.02% of Java online submissions
    private void dfs(ArrayList<List<Integer>> paths, ArrayList<Integer> curr_path,
            int target_sum, int curr_sum, TreeNode root)
    {
        if (root == null)
        {
            return;
        }
        else if (root.left == null && root.right == null && curr_sum + root.val == target_sum)
        {
            curr_path.add(root.val);
            paths.add(new ArrayList<Integer>(curr_path));
            curr_path.remove(curr_path.size() - 1);
        }
        else
        {
            curr_path.add(root.val);
            dfs(paths, curr_path, target_sum, curr_sum + root.val, root.left);
            dfs(paths, curr_path, target_sum, curr_sum + root.val, root.right);
            curr_path.remove(curr_path.size() - 1);
        }
    }

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        ArrayList<List<Integer>> paths = new ArrayList<List<Integer>>();
        dfs(paths, new ArrayList<Integer>(), sum, 0, root);
        return paths;
    }
}

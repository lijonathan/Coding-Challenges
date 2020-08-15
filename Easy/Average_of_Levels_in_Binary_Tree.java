import java.util.ArrayList;

public class Average_of_Levels_in_Binary_Tree {
/*
Rating: Easy

Given a non-empty binary tree, return the average
value of the nodes on each level in the form of an array.

Example 1:

Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5,
and on level 2 is 11. Hence return [3, 14.5, 11].

Note:

    The range of node's value is in the range of 32-bit signed integer.
*/

/*
    Approach: DFS through the tree and add all nodes to an arraylist of arraylists
              where each arraylist is all nodes in that level.
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

    // O(n) runtime, n = # of nodes in the tree
    // O(n) space
    // Runtime: 2 ms, faster than 81.33% of Java online submissions
    // Memory Usage: 41.4 MB, less than 54.84% of Java online submissions
    public void traverse(TreeNode root, ArrayList<ArrayList<Integer>> levels, int depth)
    {
        if (root != null)
        {
            if(depth >= levels.size())
            {
                levels.add(new ArrayList<Integer>());
            }
            levels.get(depth).add(root.val);
            traverse(root.left, levels, depth + 1);
            traverse(root.right, levels, depth + 1);
        }
    }

    public List<Double> averageOfLevels(TreeNode root) {
        ArrayList<ArrayList<Integer>> levels = new ArrayList<ArrayList<Integer>>();
        ArrayList<Double> avg = new ArrayList<Double>();
        traverse(root, levels, 0);
        for (int i = 0; i < levels.size(); ++i)
        {
            ArrayList<Integer> curr_l = levels.get(i);
            double averg = 0.0;
            for (int k = 0; k < curr_l.size(); ++k)
            {
                averg += (double) curr_l.get(k);
            }
            averg = averg / (double) curr_l.size();
            avg.add(averg);
        }
        return avg;
    }
}

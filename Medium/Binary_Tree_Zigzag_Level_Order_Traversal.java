import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Binary_Tree_Zigzag_Level_Order_Traversal {
/*
Rating: Medium

Given a binary tree, return the zigzag level order traversal of its nodes'
values. (ie, from left to right, then right to left for the next level and
alternate between).

For example:
Given binary tree [3,9,20,null,null,15,7],

    3
   / \
  9  20
    /  \
   15   7

return its zigzag level order traversal as:

[
  [3],
  [20,9],
  [15,7]
]
*/

/*
    Approach: DFS through the tree tracking the level. If current depth >= list.size(), append a new list, a LinkedList
              if the level is even, an ArrayList if the level is odd.

              If the level if even, append to the front of the list at the index that is equal to the current
              depth. Otherwise if the level is odd, append to the end of the list of the index that is equal to the depth.
*/

    // O(n) runtime
    // O(n) space for recursive stack in completely unbalanced tree
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 38.2 MB, less than 91.29% of Java online submissions

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
    public void dfs_zigzag(TreeNode root, int depth, ArrayList<List<Integer>> levels)
    {
        if (root != null)
        {
            if (depth + 1 > levels.size())
            {
                if (depth % 2 == 0)
                {
                    levels.add(new ArrayList<Integer>());
                }
                else
                {
                    levels.add(new LinkedList<Integer>());
                }
            }
            if (depth % 2 == 0)
            {
                levels.get(depth).add(root.val);
            }
            else
            {
                LinkedList<Integer> ll = (LinkedList) levels.get(depth);
                ll.addFirst(root.val);
            }
            dfs_zigzag(root.left, depth + 1, levels);
            dfs_zigzag(root.right, depth + 1, levels);
        }
    }
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {

        ArrayList<List<Integer>> levels = new ArrayList<List<Integer>>();
        dfs_zigzag(root, 0, levels);
        return levels;
    }
}

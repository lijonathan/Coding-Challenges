import java.util.ArrayList;
import java.util.List;

public class Binary_Tree_Paths {
/*
Rating: Easy

Given a binary tree, return all root-to-leaf paths.

Note: A leaf is a node with no children.

Example:

Input:

   1
 /   \
2     3
 \
  5

Output: ["1->2->5", "1->3"]

Explanation: All root-to-leaf paths are: 1->2->5, 1->3
*/

/*
    Approach: DFS down the tree and append the current state with "->" afterwards. Once a leaf is hit,
              append the leaf node value and add the string to the list. After the return from a DFS call,
              and prior to returning from the current stack call, revert the state back(cleanup) by deleting
              the appropriate end indices of the current stack frame state in StringBuffer.
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
    // O(n) time, each, visiting each node and sb.toString once a leaf is reached
    // O(n) space for recursive call stack and string space
    // Runtime: 4 ms, faster than 69.64% of Java online submissions
    // Memory Usage: 39.7 MB, less than 58.20% of Java online submissions
    public void dfs(ArrayList<String> paths, StringBuffer sb, TreeNode root)
    {
        if (root != null && root.left == null && root.right == null) // leaf node
        {
            sb.append(root.val);
            paths.add(sb.toString());
            sb.delete(sb.length() - Integer.toString(root.val).length(), sb.length());
        }
        else if (root != null)
        {
            sb.append(root.val);
            sb.append("->");
            dfs(paths, sb, root.left);
            dfs(paths, sb, root.right);
            sb.delete(sb.length() - Integer.toString(root.val).length() - 2, sb.length());
        }
    }
    
    public List<String> binaryTreePaths(TreeNode root) {
        ArrayList<String> paths = new ArrayList<String>();
        dfs(paths, new StringBuffer(), root);
        return paths;
    }
}

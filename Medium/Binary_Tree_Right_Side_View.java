import java.util.ArrayList;

class Binary_Tree_Right_Side_View {

/*
Rating: Medium

Given a binary tree, imagine yourself standing on the right
side of it, return the values of the nodes you can see ordered
from top to bottom.

Example:

Input: [1,2,3,null,5,null,4]
Output: [1, 3, 4]
Explanation:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---

*/
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
    
    public void view(ArrayList<Integer> curr_view, TreeNode root, int depth)
    {
        if (root == null)
        {
            return;
        }
        else
        {
            if (curr_view.size() - 1 >= depth)
            {
                curr_view.set(depth, root.val);
            }
            else
            {
                curr_view.add(root.val);
            }
            view(curr_view, root.left, depth + 1);
            view(curr_view, root.right, depth + 1);
        }
    }
    public List<Integer> rightSideView(TreeNode root) {
        // see from each depth level
        // O(n) runtime, traverse each node in the tree
        // O(n) space to store the node answer list, excludes recursive
        // call space
        ArrayList<Integer> ans = new ArrayList<Integer>();
        view(ans, root, 0);
        return ans;
    }
}

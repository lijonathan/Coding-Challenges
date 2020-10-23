import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class All_Nodes_Distance_K_in_Binary_Tree {
/*
Rating: Medium

We are given a binary tree (with root node root), a
target node, and an integer value K.

Return a list of the values of all nodes that have
a distance K from the target node.  The answer can be returned in any order.

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2

Output: [7,4,1]

Explanation: 
The nodes that are a distance 2 from the target node (with value 5)
have values 7, 4, and 1.



Note that the inputs "root" and "target" are actually TreeNodes.
The descriptions of the inputs above are just serializations of these objects.


Note:

    The given tree is non-empty.
    Each node in the tree has unique values 0 <= node.val <= 500.
    The target node is a node in the tree.
    0 <= K <= 1000.
*/


    // Runtime: 11 ms, faster than 68.20% of Java online submissions
    // Memory Usage: 39.7 MB, less than 50.29% of Java online submissions
    // O(n) runtime, 3 passes over the tree
    // O(n) space for the map and the recursive stack
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    public void populate_subtree(TreeNode curr, TreeNode target, TreeNode parent,
            HashMap<Integer, Integer> dist)
    {
        if (curr != null)
        {
            if (curr != target && parent != null)
            {
                dist.put(curr.val, dist.get(parent.val) + 1);
            }
            populate_subtree(curr.left, target, curr, dist);
            populate_subtree(curr.right, target, curr, dist);
        }
    }
    public void nodes_above(TreeNode curr, TreeNode target, TreeNode parent,
            HashMap<Integer, Integer> dist)
    {
        if (curr != null)
        {
            if (curr != target)
            {
                nodes_above(curr.left, target, curr, dist);
                nodes_above(curr.right, target, curr, dist);
                if (curr.left != null && dist.get(curr.left.val) != null)
                {
                    dist.put(curr.val, dist.get(curr.left.val) + 1);
                }
                else if (curr.right != null && dist.get(curr.right.val) != null)
                {
                    dist.put(curr.val, dist.get(curr.right.val) + 1);
                }
                else if (parent != null && dist.get(parent.val) != null)
                {
                    dist.put(curr.val, dist.get(parent.val) + 1);
                }
            }
        }
    }
    public void fill_left(TreeNode root, TreeNode parent,
            TreeNode target, HashMap<Integer, Integer> dist)
    {
        if (root != null)
        {
            if (dist.get(root) == null && root != target)
            {
                int left = (root.left != null && dist.get(root.left.val) != null)
                        ? dist.get(root.left.val) : Integer.MAX_VALUE;
                int right = (root.right != null && dist.get(root.right.val) != null)
                                ? dist.get(root.right.val) : Integer.MAX_VALUE;
                int p_val = (parent != null && dist.get(parent.val) != null)
                                        ? dist.get(parent.val) : Integer.MAX_VALUE;
                int distance = Math.min(left, Math.min(right, p_val));
                dist.put(root.val, distance + 1);
            }
            if (root != target)
            {
                fill_left(root.left, root, target, dist);
                fill_left(root.right, root, target, dist);
            }
        }
    }
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        HashMap<Integer, Integer> dist = new HashMap<Integer, Integer>();
        dist.put(target.val, 0);
        populate_subtree(target, target, null, dist);
        nodes_above(root, target, null, dist);
        fill_left(root, null, target, dist);
        ArrayList<Integer> nodes_k_away = new ArrayList<Integer>();
        for (Integer node : dist.keySet())
        {
            if (dist.get(node) == K)
            {
                nodes_k_away.add(node);
            }
        }
        return nodes_k_away;
    }
}

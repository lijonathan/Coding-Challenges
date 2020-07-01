public class Lowest_Common_Ancestor_of_a_Binary_Tree {
/*
Rating: Medium

Given a binary tree, find the lowest common ancestor (LCA)
of two given nodes in the tree.

According to the definition of LCA on Wikipedia:
“The lowest common ancestor is defined between two
nodes p and q as the lowest node in T that has both
p and q as descendants (where we allow a node to
be a descendant of itself).”

Given the following binary tree:  root = [3,5,1,6,2,0,8,null,null,7,4]

Example 1:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
Output: 3
Explanation: The LCA of nodes 5 and 1 is 3.

Example 2:

Input: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
Output: 5
Explanation: The LCA of nodes 5 and 4 is 5, since a node can be a
             descendant of itself according to the LCA definition.

Note:

    All of the nodes' values will be unique.
    p and q are different and both values will exist in the binary tree.

*/

/*
    Approach: Recurse in a DFS manner down the tree. If either of the nodes are found
              return that node in the function call. Otherwise, if it reaches a null child,
              return null. Trigger the recursive call on both the left and right subtree.
              If both left and right return values are not null, then the current node is the
              LCA. Otherwise, return the node that was found back up the recursive tree.

              If the LCA is found, return the LCA up the tree. The LCA will propogate up to the
              root because 

              If specified a node may not be in the tree(out of scope in this problem), would need
              to iterate through the whole tree to verify both nodes exist.
*/

    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
    
    // O(n) time, each node is traversed in the recursive call
    // O(n) space for the recursive call stack
    // Runtime: 4ms beats 100% of Java online submissions
    // Memory Usage: 41.8MB beats 47.65% of Java online submissions
    public TreeNode children(TreeNode root, TreeNode p, TreeNode q)
    {
        if (root == null)
        {
            return null;
        }
        else if (root == p)
        {
            return p;
        }
        else if (root == q)
        {
            return q;
        }
        else
        {
            TreeNode left = children(root.left, p, q);
            TreeNode right = children(root.right, p, q);
            if (left != null && right != null)
            {
                return root; // the LCA will always make it to the top because any nodes further
            }                // up the tree will only have one child return non-null, the LCA that was found
            else if (left != null) // and it will propogate that non-null node to the root node.
            {
                return left;
            }
            else if (right != null)
            {
                return right;
            }
            else
            {
                return null;
            }
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        return children(root, p, q);
    }
}

import java.util.ArrayList;
import java.util.List;

public class Boundary_of_Binary_Tree {
/*
Rating: Medium

Given a binary tree, return the values of its boundary in anti-clockwise direction
starting from root. Boundary includes left boundary, leaves, and right boundary in
order without duplicate nodes.  (The values of the nodes may still be duplicates.)

Left boundary is defined as the path from root to the left-most node. Right boundary
is defined as the path from root to the right-most node. If the root doesn't have left
subtree or right subtree, then the root itself is left boundary or right boundary.
Note this definition only applies to the input binary tree, and not applies to any
subtrees.

The left-most node is defined as a leaf node you could reach when you always firstly
travel to the left subtree if exists. If not, travel to the right subtree. Repeat
until you reach a leaf node.

The right-most node is also defined by the same way with left and right exchanged.

Example 1

Input:
  1
   \
    2
   / \
  3   4

Ouput:
[1, 3, 4, 2]

Explanation:
The root doesn't have left subtree, so the root itself is left boundary.
The leaves are node 3 and 4.
The right boundary are node 1,2,4. Note the anti-clockwise direction means
you should output reversed right boundary.
So order them in anti-clockwise without duplicates and we have [1,3,4,2].

 

Example 2

Input:
    ____1_____
   /          \
  2            3
 / \          / 
4   5        6   
   / \      / \
  7   8    9  10  
       
Ouput:
[1,2,4,7,8,9,10,6,3]

Explanation:
The left boundary are node 1,2,4. (4 is the left-most node according to definition)
The leaves are node 4,7,8,9,10.
The right boundary are node 1,3,6,10. (10 is the right-most node).
So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
*/

/*
    Approach: Trace the leftmost path to the leftmost child node by first checking if root has a left
              child. If not, skip to collecting leaves, otherwise, take the left child and DFS always
              choosing left unless left is null, then choosing the right child. Stop at the leaf node.

              To collect leaves, remove the leftmost leaf from the answer array and DFS with left child
              first then right child and collect all nodes with no children.

              If root.right is not null, collect right boundary. Remove the rightmost child from the answer
              list and recurse on the right child from root. On subtrees, recurse left only if right child is nulll
              until the rightmost child gets hit. Add the leaves after the recursive calls in order to collect the leaves
              in the correct order.
*/

    // O(n) runtime where n = # of nodes in the tree
    // O(n) space for the recursive call stack in a completely unbalanced tree
    // Runtime: 1 ms, faster than 77.38% of Java online submissions
    // Memory Usage: 39.9 MB, less than 71.76% of Java online submissions

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

    public void find_leftmost(TreeNode root, ArrayList<Integer> path)
    {
        if (root != null)
        {
            path.add(root.val);
            if (root.left == null && root.right == null)
            {
                return;
            }
            else if (root.left != null)
            {
                find_leftmost(root.left, path);
            }
            else
            {
                find_leftmost(root.right, path);
            }
        }
    }
    public void leaves(TreeNode root, ArrayList<Integer> path)
    {
        if (root != null)
        {
            if (root.left == null && root.right == null)
            {
                path.add(root.val);
            }
            else
            {
                leaves(root.left, path);
                leaves(root.right, path);
            }
        }
    }
    public void find_rightmost(TreeNode root, ArrayList<Integer> path)
    {
        if (root != null)
        {
            if (root.right != null)
            {
                find_rightmost(root.right, path);
            }
            else if (root.left != null)
            {
                find_rightmost(root.left, path);
            }
            path.add(root.val);
        }
    }
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        ArrayList<Integer> boundary = new ArrayList<Integer>();
        if (root == null)
        {
            return boundary;
        }
        boundary.add(root.val);
        if (root.left == null && root.right == null)
        {
            return boundary;
        }
        if (root.left != null)
        {
            boundary.remove(0);
            find_leftmost(root, boundary); // includes top
            boundary.remove(boundary.size() - 1);
        }
        leaves(root, boundary);
        if (root.right != null)
        {
            boundary.remove(boundary.size() - 1);
            find_rightmost(root, boundary);
            boundary.remove(boundary.size() - 1);
        }
        return boundary;
    }
}

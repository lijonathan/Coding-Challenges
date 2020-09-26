import java.util.Stack;

public class Kth_Smallest_Element_in_a_BST {
/*
Rating: Medium

Given a binary search tree, write a function kthSmallest to find the
kth smallest element in it.

Example 1:

Input: root = [3,1,4,null,2], k = 1
   3
  / \
 1   4
  \
   2
Output: 1

Example 2:

Input: root = [5,3,6,2,4,null,null,1], k = 3
       5
      / \
     3   6
    / \
   2   4
  /
 1
Output: 3

Follow up:
What if the BST is modified (insert/delete operations) often and you need
to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Constraints:

    The number of elements of the BST is between 1 to 10^4.
    You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
*/

/*
    Approach: Inorder iterative traversal of the tree. Use a stack and a counter to track
              the current index.

              Inorder traversal iterative pushes the root and continues pushing root.left until
              root is null. After popping off an element, check for a right child. If not, continue, otherwise
              push the right child and continue pushing curr.left until curr is null. Repeat this process.
*/


    // O(k) runtime
    // O(n) space for a completely unbalanced tree in the stack
    // Runtime: 0 ms, faster than 100.00% of Java online submissions
    // Memory Usage: 39.2 MB, less than 83.94% of Java online submissions

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
    public int kthSmallest(TreeNode root, int k) {
        // inorder traversal
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while(root != null)
        {
            stack.push(root);
            root = root.left;
        }
        int counter = 1;
        while(!stack.empty())
        {
            TreeNode curr = stack.pop();
            if (counter == k)
            {
                return curr.val;
            }
            curr = curr.right;
            while(curr != null)
            {
                stack.push(curr);
                curr = curr.left;
            }
            ++counter;
        }
        return -1;
    }
}

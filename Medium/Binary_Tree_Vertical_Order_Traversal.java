import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Binary_Tree_Vertical_Order_Traversal {

/*
Rating: Medium

Given a binary tree, return the vertical order traversal of its nodes'
values. (ie, from top to bottom, column by column).

If two nodes are in the same row and column, the
order should be from left to right.

Examples 1:

Input: [3,9,20,null,null,15,7]

   3
  /\
 /  \
 9  20
    /\
   /  \
  15   7 

Output:

[
  [9],
  [3,15],
  [20],
  [7]
]

Examples 2:

Input: [3,9,8,4,0,1,7]

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7 

Output:

[
  [4],
  [9],
  [3,0,1],
  [8],
  [7]
]

Examples 3:

Input: [3,9,8,4,0,1,7,null,null,null,2,5]
(0's right child is 2 and 1's left child is 5)

     3
    /\
   /  \
   9   8
  /\  /\
 /  \/  \
 4  01   7
    /\
   /  \
   5   2

Output:

[
  [4],
  [9,5],
  [3,0,1],
  [8,2],
  [7]
]

*/
    /*
    Approach: BFS keeping track of level and offset from center
    Custom class "Pair" that tracked TreeNode with its respective offset
    from the center. Pair class was added into queue during BFS.
     */

    /* Definition for a binary tree node.   */
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

    class Solution {

        private class Pair
        {
            public TreeNode node;
            public int offset;

            public Pair(TreeNode node, int offset)
            {
                this.node = node;
                this.offset = offset;
            }
        }
        // O(n) runtime where n is the number of nodes in the tree
        // each node is visited once, the left list is reversed and the
        // right list is appended to the left list
        // O(n) space, 1/2 nodes in left list, 1/2 nodes in right, then another
        // 1/2 as right list is appended to left list
        public List<List<Integer>> verticalOrder(TreeNode root) {
            // Runtime: 2 ms, faster than 89.59% of Java online submissions
            // Memory Usage: 39.5 MB, less than 68.00% of Java online submissions
            ArrayList<List<Integer>> left = new ArrayList<List<Integer>>();
            ArrayList<List<Integer>> right = new ArrayList<List<Integer>>();
            ArrayDeque<Pair> queue = new ArrayDeque<Pair>();
            if (root != null)
            {
                Pair p = new Pair(root, 0);
                queue.add(p);
            }
            while(!queue.isEmpty()) // BFS
            {
                Pair item = queue.remove();
                if (item.offset < 0) // left
                {
                    int offset = item.offset * -1;
                    if (offset > left.size())
                    {
                        ArrayList<Integer> entry = new ArrayList<Integer>();
                        entry.add(item.node.val);
                        left.add(entry);
                    }
                    else
                    {
                        left.get(offset - 1).add(item.node.val);
                    }
                }
                else // middle and right
                {
                    int offset = item.offset;
                    if (offset + 1 > right.size())
                    {
                        ArrayList<Integer> entry = new ArrayList<Integer>();
                        entry.add(item.node.val);
                        right.add(entry);
                    }
                    else
                    {
                        right.get(offset).add(item.node.val);
                    }
                }
                if (item.node.left != null) // left offset = parent offset - 1
                {
                    Pair l = new Pair(item.node.left, item.offset - 1);
                    queue.add(l);
                }
                if (item.node.right != null) // right offset = parent offset + 1
                {
                    Pair r = new Pair(item.node.right, item.offset + 1);
                    queue.add(r);
                }
            }
            Collections.reverse(left);
            left.addAll(right);
            return left;
        }
    }
}

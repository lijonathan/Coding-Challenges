import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Vertical_Order_Traversal_of_a_Binary_Tree {

/*
Rating: Medium

Given a binary tree, return the vertical order traversal of its nodes values.

For each node at position (X, Y), its left and right children respectively
will be at positions (X-1, Y-1) and (X+1, Y-1).

Running a vertical line from X = -infinity to X = +infinity, whenever the
vertical line touches some nodes, we report the values of the nodes in order
from top to bottom (decreasing Y coordinates).

If two nodes have the same position, then the value of the node that is
reported first is the value that is smaller.

Return an list of non-empty reports in order of X coordinate.
Every report will have a list of values of nodes.

 

Example 1:

Input: [3,9,20,null,null,15,7]
Output: [[9],[3,15],[20],[7]]
Explanation: 
Without loss of generality, we can assume the root node is at position (0, 0):
Then, the node with value 9 occurs at position (-1, -1);
The nodes with values 3 and 15 occur at positions (0, 0) and (0, -2);
The node with value 20 occurs at position (1, -1);
The node with value 7 occurs at position (2, -2).

Example 2:

Input: [1,2,3,4,5,6,7]
Output: [[4],[2],[1,5,6],[3],[7]]
Explanation: 
The node with value 5 and the node with value 6 have the
same position according to the given scheme.
However, in the report "[1,5,6]", the node value of 5 comes first
since 5 is smaller than 6.

 

Note:

    The tree will have between 1 and 1000 nodes.
    Each node's value will be between 0 and 1000.

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

    private class tuple_cols_comparator implements Comparator<Tuple>
    {
        public int compare(Tuple a, Tuple b)
        {
            return a.col - b.col;
        }
    }
    private class tuple_rows_comparator implements Comparator<Tuple>
    {
        public int compare(Tuple a, Tuple b)
        {
            return a.row - b.row;
        }
    }
    private class tuple_val_comparator implements Comparator<Tuple>
    {
        public int compare(Tuple a, Tuple b)
        {
            return a.val - b.val;
        }
    }

    private class Tuple
    {
        int row;
        int col;
        int val;
        public Tuple(int row, int col, int val)
        {
            this.row = row;
            this.col = col;
            this.val = val;
        }
    }
    /*
    Approach: DFS keeping track of offset from center and the depth. Sort all tuples
    by the columns then group them by columns. Then sort each grouped column by ascending
    row. If the row is the same in the same group of column, sort by ascending value.
     */
    private void dfs(TreeNode root, int offset, int depth, ArrayList<Tuple> col)
    {
        if (root != null)
        {
            Tuple p = new Tuple(depth, offset, root.val);
            col.add(p);
            dfs(root.left, offset - 1, depth + 1, col);
            dfs(root.right, offset + 1, depth + 1, col);
        }
    }
    // O(nlogn) runtime, n nodes are traversed and at worst all n nodes need sorting
    // as an upper bound
    // O(n) space. Each node gets stored.
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        // Runtime: 8ms beat 9.00% of Java submissions
        // Memory Usage: 40.3MB beats 9.05% of Java submissions
        // Runtime and memory usage are asymptotically in line
        ArrayList<Tuple> all_nodes = new ArrayList<Tuple>();
        dfs(root, 0, 0, all_nodes);
        Collections.sort(all_nodes, new tuple_cols_comparator()); // sort by columns first

        List<List<Tuple>> divided_cols = new ArrayList<List<Tuple>>();
        int counter = -1;
        for (int i = 0; i < all_nodes.size(); ++i) // group the columns
        {
            if (i > 0 && all_nodes.get(i).col == all_nodes.get(i - 1).col)
            {
                divided_cols.get(counter).add(all_nodes.get(i));
            }
            else
            {
                ArrayList<Tuple> new_entry = new ArrayList<Tuple>();
                new_entry.add(all_nodes.get(i));
                divided_cols.add(new_entry);
                ++counter;
            }
        }
        for (int i = 0; i < divided_cols.size(); ++i) // sort each group of columns by their rows
        {
            Collections.sort(divided_cols.get(i), new tuple_rows_comparator());   
        }
        for (int i = 0; i < divided_cols.size(); ++i)
        {
            for (int k = 1; k < divided_cols.get(i).size(); ++k)
            {
                // sort any values that have the same column and row.
                if (divided_cols.get(i).get(k).row == divided_cols.get(i).get(k - 1).row)
                {
                    int start = k - 1;
                    int end = k;
                    while(end < divided_cols.get(i).size() &&
                            divided_cols.get(i).get(start).row == divided_cols.get(i).get(end).row)
                    {
                        ++end;
                    }
                    Collections.sort(divided_cols.get(i).subList(start, end), new tuple_val_comparator());
                    k = end - 1;
                }
            }
        }
        ArrayList<List<Integer>> ans = new ArrayList<List<Integer>>();
        for (int i = 0; i < divided_cols.size(); ++i)
        {
            ArrayList<Integer> col = new ArrayList<Integer>();
            for (int k = 0; k < divided_cols.get(i).size(); ++k)
            {
                col.add(divided_cols.get(i).get(k).val);
            }
            ans.add(col);
        }
        return ans;
    }
}

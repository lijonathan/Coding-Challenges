import java.util.HashMap;

public class Serialize_and_Deserialize_Binary_Tree {
    
/*
Rating: Hard
 
Serialization is the process of converting a data structure
or object into a sequence of bits so that it can be stored in
a file or memory buffer, or transmitted across a network connection
link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree.
There is no restriction on how your serialization/deserialization
algorithm should work. You just need to ensure that a binary tree
can be serialized to a string and this string can be deserialized
to the original tree structure.

Example: 

You may serialize the following tree:

    1
   / \
  2   3
     / \
    4   5

as "[1,2,3,null,null,4,5]"

Clarification: The above format is the same as how LeetCode
serializes a binary tree. You do not necessarily need to follow
this format, so please be creative and come up with different
approaches yourself.

Note: Do not use class member/global/static variables to
store states. Your serialize and deserialize algorithms
should be stateless.

*/

/*
    Approach:
    Serialize - DFS through each node. Keep a unique counter for the current number of nodes visited
    and add that as the nodes unique id. Pass the ID to the child so it can reference the parent id.
    Pass a boolean true to the left child and false for the right child. Append these all to a string.
    Do not need to do a full serizliation so null leafs are not included in the string(will cause timeout)

    Deserialize: Split the string based on established delimiter(" ") to seperate each nodes data.
    Split the individual nodes data on (":") for each piece of data (node id, parent id, left vs right).
    Place all nodes into a HashMap of id -> node. Iterate through all nodes in the HasMap and connect
    the parent to the child(left vs right and parent ID are all available in the node data).

*/
    // Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // O(n) runtime for serialization and deserialization. Each node is visited once.
    // Serialize traverses each node through dfs and appends a string with stringbuffer.
    // Deserialize splits the string into arrays and traverses each array, with which there are n, one array
    // of data representing each node
    // O(n) space. The String size is directly proportional to how many nodes are in the tree
    // and the nodes array and nodes_data array are also a constant factor of how many nodes there are

    // Runtime: 33 ms beating 22.62% of Java online submissions
    // Memory usage: 45.3 MB beating 16.61% of Java online submissions

    // Memory and runtime are asymptotically in line with expected runtime and space usage
    private void dfs(TreeNode root, StringBuffer sb, double[] counter, double parent_id, boolean left)
    {
        //self id : left id : right id
        double curr_id = counter[0];
        sb.append(Integer.toString(root.val));
        sb.append(":");
        sb.append(parent_id);
        sb.append(":");
        sb.append(counter[0]);
        sb.append(":");
        sb.append(left);
        sb.append(" ");
        //points to parent, curr_id distinguishes l or r child
        counter[0] = counter[0] + 1;
        if (root.left != null)
        {
            dfs(root.left, sb, counter, curr_id, true);
        }
        if (root.right != null)
        {
            dfs(root.right, sb, counter, curr_id, false);
            // curr_id marking child overflowing
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        double[] counter = new double[1];
        counter[0] = 1;
        dfs(root, sb, counter, counter[0], false);
        return sb.toString().stripTrailing();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == "")
        {
            return null;
        }
        String[] nodes = data.split(" ");
        HashMap<Double, TreeNode> node_index = new HashMap<Double, TreeNode>();
        for (int i = 0; i < nodes.length; ++i)
        {
            String[] node_data = nodes[i].split(":");
            TreeNode node = new TreeNode(Integer.parseInt(node_data[0]));
            double id = Double.parseDouble(node_data[2]);
            node_index.put(id, node);
        }
        for (int i = 1; i < nodes.length; ++i)
        {
            String[] node_data = nodes[i].split(":");
            TreeNode parent = node_index.get(Double.parseDouble(node_data[1]));
            double child_index = Double.parseDouble(node_data[2]);
            TreeNode child = node_index.get(child_index);
            boolean left = Boolean.parseBoolean(node_data[3]);
            if (left)
            {
                parent.left = child;
            }
            else
            {
                parent.right = child;
            }
        }
        return node_index.get(1.0);
    }
}

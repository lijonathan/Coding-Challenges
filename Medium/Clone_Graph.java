import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Clone_Graph {
/*
Rating: Medium

Given a reference of a node in a connected undirected graph.

Return a deep copy (clone) of the graph.

Each node in the graph contains a val (int) and a list (List[Node]) of its neighbors.

class Node {
    public int val;
    public List<Node> neighbors;
}

Test case format:

For simplicity sake, each node's value is the same as the node's index (1-indexed).
For example, the first node with val = 1,
the second node with val = 2, and so on.
The graph is represented in the test case using an adjacency list.

Adjacency list is a collection of unordered lists used to represent a finite graph.
Each list describes the set of neighbors of a node in the graph.

The given node will always be the first node with val = 1.
You must return the copy of the given node as a reference to the cloned graph.

 

Example 1:

Input: adjList = [[2,4],[1,3],[2,4],[1,3]]
Output: [[2,4],[1,3],[2,4],[1,3]]
Explanation: There are 4 nodes in the graph.
1st node (val = 1)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
2nd node (val = 2)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).
3rd node (val = 3)'s neighbors are 2nd node (val = 2) and 4th node (val = 4).
4th node (val = 4)'s neighbors are 1st node (val = 1) and 3rd node (val = 3).

Example 2:

Input: adjList = [[]]
Output: [[]]
Explanation: Note that the input contains one empty list.
The graph consists of only one node with val = 1 and it does not have any neighbors.

Example 3:

Input: adjList = []
Output: []
Explanation: This an empty graph, it does not have any nodes.

Example 4:

Input: adjList = [[2],[1]]
Output: [[2],[1]]


Constraints:

    1 <= Node.val <= 100
    Node.val is unique for each node.
    Number of Nodes will not exceed 100.
    There is no repeated edges and no self-loops in the graph.
    The Graph is connected and all nodes can be visited starting from the given node.
*/

/*
    Approach: DFS through the entire graph and put all the nodes into a map with the value as the key
              and the node as the value. Use the map keyset as the set of visited nodes, do not recurse
              to a node that has already been visited.

              To get the neighbors to build the graph, DFS through the original graph again with an input set
              of visited nodes. As a node is visited, add it to the set and do not recurse to a node that is in
              the visited set.

              Whilst iterating through the neighbors and recursing, build out the neighbors Arraylist for the cloned
              graph.
*/


    // O(n) space to store the map and the set, excluding the output graph
    // O(n^2) time where n = # of nodes, worst case each node is connected to all other nodes
    // iterating through all neighbors takes n time for each node
    // Runtime: 27 ms, faster than 81.18% of Java online submissions
    // Memory Usage: 39.5 MB, less than 78.81% of Java online submissions

    // Definition for a Node.
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }
    public void dfs_nodes(Node curr, HashMap<Integer, Node> visited)
    {
        Node new_node = new Node(curr.val, (ArrayList<Node>)curr.neighbors);
        visited.put(new_node.val, new_node);
        for (Node neighbor : curr.neighbors)
        {
            if (!visited.containsKey(neighbor.val))
            {
                dfs_nodes(neighbor, visited);
            }
        }
    }

    public void dfs_neighbors(Node curr_orig, Node new_curr, HashSet<Integer> visited,
            HashMap<Integer, Node> nodes)
    {
        visited.add(new_curr.val);
        ArrayList<Node> neighbors = new ArrayList<Node>();
        for (Node neighbor : curr_orig.neighbors)
        {
            neighbors.add(nodes.get(neighbor.val));
            if (!visited.contains(neighbor.val))
            {
                dfs_neighbors(neighbor, nodes.get(neighbor.val), visited, nodes);
            }
        }
        new_curr.neighbors = neighbors;
    }

    public Node cloneGraph(Node node) {
        if (node == null)
        {
            return null;
        }
        HashMap<Integer, Node> visited = new HashMap<Integer, Node>();
        dfs_nodes(node, visited);
        dfs_neighbors(node, visited.get(1), new HashSet<Integer>(), visited);
        return visited.get(1);
    }
}

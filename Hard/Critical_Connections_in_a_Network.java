import java.util.ArrayList;
import java.util.List;

public class Critical_Connections_in_a_Network {
/*
Rating: Hard

There are n servers numbered from 0 to n-1 connected by
undirected server-to-server connections forming a network
where connections[i] = [a, b] represents a connection between
servers a and b. Any server can reach any other server directly
or indirectly through the network.

A critical connection is a connection that, if removed, will
make some server unable to reach some other server.

Return all critical connections in the network in any order.

Example 1:

Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.



Constraints:

    1 <= n <= 10^5
    n-1 <= connections.length <= 10^5
    connections[i][0] != connections[i][1]
    There are no repeated connections.
*/

/*
    Approach: Tarjan's Algorithm

    Create an adjacency list for the graph -- for each vertex have a list of all connected vertices.

    Have an array the size of the number of vertices that will store the low value, or the lowest value that
    each vertex is connected to. Values will be assigned to vertices in the order that they are visited.

    Track an array of visited vertices so that DFS does not recurse to vertices already visited. Have a timer
    that increments in each call that tracks visit order.

    Choose any node to start the DFS with. For each node that it is connected to, mark it as visited, set its
    current low_val equal to the timer(order visited). Iterate through all connected nodes in the adjacency list
    and if it is the parent, skip it.

    If the next node has not been visited, recurse to it. Set the current low value to the minimum of
    the current node low value and the neighbor nodes low val.

    If the low value of the neighbor node is less than the current id, it is a critical edge.
    Intuition: After an update, the lowest value node the neighbor has seen is greater than the lowest value
    node the current node has seen, hence previous nodes that have been visited are connected to the neighbor
    node ONLY through the current edge.

    Otherwise if the node has already been visited, update the current low value with the minimum
    of the current low value and the neighbor low value.
*/

    // O(V + E) runtime
    // O(V) space for visited array
    // Runtime: 80 ms, faster than 99.25% of Java online submissions
    // Memory Usage: 108.1 MB, less than 70.71% of Java online submissions
    public void dfs(ArrayList<Integer>[] adj_l, int curr, int parent, boolean[] visited, int[] timer,
            int[] low_val, List<List<Integer>> critical)
    {
        visited[curr] = true;
        low_val[curr] = timer[0];
        int curr_time = timer[0];
        ++timer[0];
        for (int i = 0; i < adj_l[curr].size(); ++i)
        {
            int next = adj_l[curr].get(i);
            if (next == parent)
            {
                continue;
            }
            else if (!visited[next])
            {
                dfs(adj_l, next, curr, visited, timer, low_val, critical);
                low_val[curr] = Math.min(low_val[curr], low_val[next]);
                if (low_val[next] > curr_time)
                {
                    ArrayList<Integer> critical_edge = new ArrayList<Integer>();
                    critical_edge.add(curr);
                    critical_edge.add(next);
                    critical.add(critical_edge);
                }
            }
            else
            {
                low_val[curr] = Math.min(low_val[curr], low_val[next]);
            }
        }
    }
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

        ArrayList<List<Integer>> critical_connections = new ArrayList<List<Integer>>();
        ArrayList[] adjacency_list = new ArrayList[n];
        int[] low_val = new int[n];
        // build out adjacency list
        for (int i = 0; i < connections.size(); ++i)
        {
            int first_network = connections.get(i).get(0);
            int second_network = connections.get(i).get(1);
            ArrayList<Integer> first_connections = adjacency_list[first_network];
            if (first_connections == null)
            {
                adjacency_list[first_network] = new ArrayList<Integer>();
                first_connections = adjacency_list[first_network];
            }
            ArrayList<Integer> second_connections = adjacency_list[second_network];
            if (second_connections == null)
            {
                adjacency_list[second_network] = new ArrayList<Integer>();
                second_connections = adjacency_list[second_network];
            }
            first_connections.add(second_network);
            second_connections.add(first_network);
        }
        low_val[0] = 0;
        dfs(adjacency_list, 0, -1, new boolean[n], low_val, new int[n], critical_connections);
        return critical_connections;
    }
}

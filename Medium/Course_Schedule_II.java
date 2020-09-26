import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class Course_Schedule_II {
/*
Rating: Medium

There are a total of n courses you have to take labelled from 0 to n - 1.

Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi]
this means you must take the course bi before the course ai.

Given the total number of courses numCourses and a list of the prerequisite pairs,
return the ordering of courses you should take to finish all courses.

If there are many valid answers, return any of them. If it is impossible to finish
all courses, return an empty array. 

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: [0,1]
Explanation: There are a total of 2 courses to take. To take course 1 you should have finished course 0.
So the correct course order is [0,1].

Example 2:

Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,2,1,3]
Explanation: There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2.
Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].

Example 3:

Input: numCourses = 1, prerequisites = []
Output: [0]


Constraints:

    1 <= numCourses <= 2000
    0 <= prerequisites.length <= numCourses * (numCourses - 1)
    prerequisites[i].length == 2
    0 <= ai, bi < numCourses
    ai != bi
    All the pairs [ai, bi] are distinct.
*/

/*
    Approach: Directed Graph Cycle Detection and Topological Sort

              Create an adjacency list of the courses and check if there is a cycle.
              Use two arrays, visited array and visiting array. Start with all false values
              in both arrays. Iterate through the vertices and if the current vertex is visiting[neighbor] = true,
              return true for a cycle. Otherwise, if it is not visited, dfs to that neighbor and return
              true if the neighbor detects a cycle.

              Mark visited of curr after all neighbors have been visited as true, and mark visiting of curr as false.

              If no cycles, topological sort for the ordering. Reset all vertices to unvisited. Iterate and for
              each unvisited vertex, visit it and mark it as visited, then recursively visit all its neighbors.
              After all neighbors of the current vertex has been visited, push it onto the stack. Return the stack
              popping off the vertices in order.
*/

    // O(V + E) time where V is number of courses and E is number of prerequisites
    // O(V) space
    // Runtime: 2 ms, faster than 99.76% of Java online submissions
    // Memory Usage: 41.2 MB, less than 37.43% of Java online submissions
    public boolean detect_cycle(ArrayList[] adj_l, boolean[] visited,
            boolean[] visiting, int curr)
    {
        if (adj_l[curr] == null)
        {
            return false;
        }
        visiting[curr] = true;
        for (int i = 0; i < adj_l[curr].size(); ++i)
        {
            int neighbor = (Integer) adj_l[curr].get(i);
            if (visiting[neighbor])
            {
                return true;
            }
            else if (!visited[neighbor])
            {
                if (detect_cycle(adj_l, visited, visiting, neighbor))
                {
                    return true;
                }
            }
        }
        visited[curr] = true;
        visiting[curr] = false;
        return false;
    }
    public void topological_sort(ArrayList[] adj_l, int curr,
            boolean[] visited, Stack<Integer> order)
    {
        visited[curr] = true;
        if (adj_l[curr] != null)
        {
            for (int i = 0; i < adj_l[curr].size(); ++i)
            {
                int neighbor = (int) adj_l[curr].get(i);
                if (!visited[neighbor])
                {
                    topological_sort(adj_l, neighbor, visited, order);
                }
            }
        }
        order.push(curr);
    }
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        ArrayList[] adj_l = new ArrayList[numCourses];
        for (int i = 0; i < prerequisites.length; ++i)
        {
            int prereq = prerequisites[i][1];
            int postreq = prerequisites[i][0];
            if (adj_l[prereq] == null)
            {
                adj_l[prereq] = new ArrayList<Integer>();
            }
            adj_l[prereq].add(postreq);
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];
        for (int i = 0; i < adj_l.length; ++i)
        {
            if (adj_l[i] != null && !visited[i])
            {
                if (detect_cycle(adj_l, visited, visiting, i))
                {
                    return new int[0];
                }
            }
        }
        Stack<Integer> order = new Stack<Integer>();
        Arrays.fill(visited, false);
        for (int i = 0; i < numCourses; ++i)
        {
            if (!visited[i])
            {
                topological_sort(adj_l, i, visited, order);
            }
        }
        int[] ordering = new int[numCourses];
        int index = 0;
        while(!order.empty())
        {
            ordering[index] = order.pop();
            ++index;
        }
        return ordering;
    }
}

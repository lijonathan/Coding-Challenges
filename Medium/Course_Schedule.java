import java.util.ArrayList;
import java.util.Arrays;

public class Course_Schedule {
/*
Rating: Medium

There are a total of numCourses courses you have to take,
labeled from 0 to numCourses-1.

Some courses may have prerequisites, for example to take
course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite
pairs, is it possible for you to finish all courses?

Example 1:

Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0. So it is possible.

Example 2:

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: There are a total of 2 courses to take. 
             To take course 1 you should have finished course 0, and to take course 0 you should
             also have finished course 1. So it is impossible.

Constraints:

    The input prerequisites is a graph represented by a list of edges, not adjacency matrices.
    Read more about how a graph is represented.
    You may assume that there are no duplicate edges in the input prerequisites.
    1 <= numCourses <= 10^5
*/

/*
    Approach: This is a graph cycle detection problem. Create an adjacency list from all the courses
              and their corresponding edges.

              2 Arrays that mark unvisited, currently visiting.

              DFS from any starting vertex. Mark current as visiting. For each neighbor, if it is unvisited,
              dfs to that neighbor. Otherwise, if it is currently visiting, a cycle has been
              detected and return true. After all neighbors have been visited, mark visiting and
              unvisited as false and visited as true.
*/

    // O(V + E) runtime
    // O(V + E) space
    // Runtime: 2 ms, faster than 99.70% of Java online submissions
    // Memory Usage: 40.1 MB, less than 72.42% of Java online submissions
    public boolean dfs(ArrayList<Integer>[] adj_list, boolean[] unvisited,
                       boolean[] visiting, int curr)
    {
        ArrayList<Integer> edges = adj_list[curr];
        if (edges == null)
        {
            return false;
        }
        boolean cycle = false;
        int l = edges.size();
        visiting[curr] = true;
        unvisited[curr] = false;
        for (int i = 0; i < l; ++i)
        {
            int neighbor = edges.get(i);
            if (unvisited[neighbor])
            {
                if(dfs(adj_list, unvisited, visiting, neighbor))
                {
                    cycle = true;
                    break;
                }
            }
            else if (visiting[neighbor])
            {
                return true;
            }
        }
        visiting[curr] = false;
        return cycle;
    }
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // cycle detection --> DFS
        ArrayList<Integer>[] adj_list = new ArrayList[numCourses];
        for (int i = 0; i < prerequisites.length; ++i)
        {
            int course = prerequisites[i][1];
            if (adj_list[course] == null)
            {
                adj_list[course] = new ArrayList<Integer>();
            }
            adj_list[course].add(prerequisites[i][0]);
        }
        
        boolean[] visiting = new boolean[numCourses];
        boolean[] unvisited = new boolean[numCourses];
        Arrays.fill(unvisited, true);
        for (int i = 0; i < numCourses; ++i)
        {
            if (unvisited[i])
            {
                if (dfs(adj_list, unvisited, visiting, i))
                {
                    return false;
                }
            }
        }
        return true;
    }
}

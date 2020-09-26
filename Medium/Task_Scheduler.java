import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Task_Scheduler {
/*
Rating: Medium

Given a characters array tasks, representing the tasks a CPU needs to
do, where each letter represents a different task. Tasks could be done
in any order. Each task is done in one unit of time. For each unit of time,
the CPU could complete either one task or just be idle.

However, there is a non-negative integer n that represents the cooldown
period between two same tasks (the same letter in the array), that is
that there must be at least n units of time between any two same tasks.

Return the least number of units of times that the CPU will take to finish
all the given tasks.

Example 1:

Input: tasks = ["A","A","A","B","B","B"], n = 2
Output: 8
Explanation: 
A -> B -> idle -> A -> B -> idle -> A -> B
There is at least 2 units of time between any two same tasks.

Example 2:

Input: tasks = ["A","A","A","B","B","B"], n = 0
Output: 6
Explanation: On this case any permutation of size 6 would work since n = 0.
["A","A","A","B","B","B"]
["A","B","A","B","A","B"]
["B","B","B","A","A","A"]
...
And so on.

Example 3:

Input: tasks = ["A","A","A","A","A","A","B","C","D","E","F","G"], n = 2
Output: 16
Explanation: 
One possible solution is
A -> B -> C -> A -> D -> E -> A -> F -> G -> A -> idle -> idle -> A -> idle -> idle -> A

Constraints:

    1 <= task.length <= 104
    tasks[i] is upper-case English letter.
    The integer n is in the range [0, 100].
*/

/*
    Approach: Similar to Reorganize String -- simulate execution of the tasks.

              Create a Task class with the task field, the number of that task type
              that remains, and the time that the current task type was last executed.

              Create tasks by iterating thru the tasks array and insert them all into
              a MaxHeap that puts the task with the most count of that task left at the top.

              Create a queue that holds idle tasks.

              Track a time that starts at zero. While the heap has elements or the queue has elements,
              check if the current time - time of element at front of queue is greater than n. If it is,
              pull it from the front of the queue and insert it into the heap.

              If the heap size if greater than 0, pull an element off the heap, decrement its task
              count and set the time to the current time.

              Return the time.
*/

    // O(time) where time is total amount of time to finish
    // O(1) space since only max 26 unique tasks
    // Runtime: 22 ms, faster than 31.02% of Java online submissions
    // Memory Usage: 40.6 MB, less than 85.92% of Java online submissions
    class Task
    {
        char task;
        int count;
        int time;
        public Task(char task, int count, int time)
        {
            this.task = task;
            this.count = count;
            this.time = time;
        }
    }
    public class TaskComparator implements Comparator<Task>
    {
        public int compare(Task a, Task b)
        {
            return b.count - a.count;
        }
    }
    public int leastInterval(char[] tasks, int n) {
        int time = 0;
        PriorityQueue<Task> pq = new PriorityQueue<Task>(new TaskComparator());
        HashMap<Character, Task> counts = new HashMap<Character, Task>();
        for (int i = 0; i < tasks.length; ++i)
        {
            Task p = counts.get(tasks[i]);
            if (p == null)
            {
                p = new Task(tasks[i], 0, 0);
                counts.put(tasks[i], p);
            }
            p.count += 1;
        }
        for (Task p : counts.values())
        {
            pq.offer(p);
        }
        ArrayDeque<Task> queue = new ArrayDeque<Task>();
        while(pq.size() > 0 || !queue.isEmpty())
        {
            ++time;
            Task p = queue.peek();
            if (p != null && time - p.time - 1 >= n)
            {
                queue.remove();
                pq.offer(p);
            }
            if (pq.size() > 0)
            {
                p = pq.remove();
                p.count -= 1;
                if (p.count > 0)
                {
                    p.time = time;
                    queue.add(p);
                }
            }

        }
        return time;
    }
}

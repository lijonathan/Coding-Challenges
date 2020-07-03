import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class High_Five {
/*
Rating: Easy

Given a list of scores of different students,
return the average score of each student's top
five scores in the order of each student's id.

Each entry items[i] has items[i][0] the student's id,
and items[i][1] the student's score.  The average score
is calculated using integer division.

Example 1:

Input: [[1,91],[1,92],[2,93],[2,97],[1,60],[2,77],[1,65],
        [1,87],[1,100],[2,100],[2,76]]
Output: [[1,87],[2,88]]
Explanation: 
The average of the student with id = 1 is 87.
The average of the student with id = 2 is 88.6.
But with integer division their average converts to 88.

Note:

    1 <= items.length <= 1000
    items[i].length == 2
    The IDs of the students is between 1 to 1000
    The score of the students is between 1 to 100
    For each student, there are at least 5 scores
*/

/*
    Approach: Iterate through the scores and group all scores from the same
              student into a HashMap as arraylists. Iterate through all the students
              in the HashMap and use a min heap priority queue and ensure the size
              of the queue remains no larger than 5. Retrieve those 5 scores to calculate the
              average
*/
    // O(n) time where n = total number of scores
    // O(n) space where n = total number of scores
    // Runtime: 5ms beats 69.57% of Java online submissions
    // Memory Usage: 39.5MB beats 84.77% of Java online submissions
    public int[][] highFive(int[][] items) {
        HashMap<Integer, ArrayList<Integer>> student_scores = new HashMap<Integer, ArrayList<Integer>>();
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
        for (int i = 0; i < items.length; ++i)
        {
            ArrayList<Integer> s_scores = student_scores.get(items[i][0]);
            if (s_scores == null)
            {
                s_scores = new ArrayList<Integer>();
            }
            s_scores.add(items[i][1]);
            student_scores.put(items[i][0], s_scores);
        }
        int[][] averages = new int[student_scores.size()][2];
        int index = 0;
        for (Integer student: student_scores.keySet())
        {
            ArrayList<Integer> scores = student_scores.get(student);
            int length = scores.size();
            for (int i = 0; i < length; ++i)
            {
                if(pq.size() < 5)
                {
                    pq.add(scores.get(i));
                }
                else
                {
                    if (pq.peek() < scores.get(i))
                    {
                        pq.poll();
                        pq.add(scores.get(i));
                    }
                }
            }
            int avg = 0;
            while(pq.size() > 0)
            {
                avg += pq.poll();
            }
            avg = avg / 5;
            averages[index][0] = student;
            averages[index][1] = avg;
            ++index;
        }
        return averages;
    }
}

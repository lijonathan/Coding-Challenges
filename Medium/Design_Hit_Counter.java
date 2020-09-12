import java.util.ArrayList;

public class Design_Hit_Counter {
/*
Rating: Medium

Design a hit counter which counts the number of hits received in the past 5 minutes.

Each function accepts a timestamp parameter (in seconds granularity) and you may assume that calls are being made to the system in chronological order (ie, the timestamp is monotonically increasing). You may assume that the earliest timestamp starts at 1.

It is possible that several hits arrive roughly at the same time.

Example:

HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301); 

Follow up:
What if the number of hits per second could be very large? Does your design scale?
*/

/*
    Approach: Create a new class Pair with a timestamp and a counter. Use an arraylist to
              record hits. If the timestamp matches the most recent timestamp, increment the hits
              counter, otherwise, make a new Pair with the new timestamp and counter 1.

              To find the index of the previous 5 minutes, take the current timestamp and subtract
              299. Binary Search for the timestamp and get the index. Sum from that index to the end for
              the number of hits.
*/

    // O(1) time for hit(), O(log n) time for getHits()
    // O(n) space where n = # of timestamps
    // Runtime: 1 ms, faster than 97.57% of Java online submissions
    // Memory Usage: 37.8 MB, less than 45.39% of Java online submissions
    class HitCounter {

        /** Initialize your data structure here. */
        
        class Pair
        {
            int timestamp;// seconds;
            int count;
            public Pair(int timestamp, int count)
            {
                this.timestamp = timestamp;
                this.count = count;
            }
        }
        
        ArrayList<Pair> hits;
        public HitCounter() {
            hits = new ArrayList<Pair>();
        }
        
        /** Record a hit.
            @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            if (hits.size() == 0)
            {
                Pair p = new Pair(timestamp, 1);
                hits.add(p);
            }
            else
            {
                Pair p = hits.get(hits.size() - 1);
                if (p.timestamp == timestamp)
                {
                    p.count += 1;
                }
                else
                {
                    p = new Pair(timestamp, 1);
                    hits.add(p);
                }
            }
        }
        
        /** Return the number of hits in the past 5 minutes.
            @param timestamp - The current timestamp (in seconds granularity). */
        public int find_start(int target)
        {
            int start = 0;
            int end = hits.size() - 1;
            while(start <= end)
            {
                int mid = (start + end) / 2;
                int time = hits.get(mid).timestamp;
                if (time == target)
                {
                    return mid;
                }
                else if (time < target)
                {
                    if (mid == hits.size() - 1)
                    {
                        return mid + 1;
                    }
                    else if (hits.get(mid + 1).timestamp >= target)
                    {
                        return mid + 1;
                    }
                    else
                    {
                        start = mid + 1;
                    }
                }
                else
                {
                    if (mid == 0)
                    {
                        return 0;
                    }
                    else if (hits.get(mid - 1).timestamp < target)
                    {
                        return mid;
                    }
                    else if (hits.get(mid - 1).timestamp == target)
                    {
                        return mid - 1;
                    }
                    else
                    {
                        end = mid - 1;
                    }
                }
            }
            return hits.size();
        }
        public int getHits(int timestamp) {
            int min = timestamp - 299;
            int start = find_start(min);
            int end = hits.size();
            int count = 0;
            for (int i = start; i < end; ++i)
            {
                count += hits.get(i).count;
            }
            return count;
        }
    }

    /**
     * Your HitCounter object will be instantiated and called as such:
     * HitCounter obj = new HitCounter();
     * obj.hit(timestamp);
     * int param_2 = obj.getHits(timestamp);
     */
}

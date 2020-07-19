import java.util.ArrayList;
import java.util.HashMap;

public class Time_Based_Key_Value_Store {
/*
Rating: Medium

Create a timebased key-value store class TimeMap, that supports two operations.

1. set(string key, string value, int timestamp)

    Stores the key and value, along with the given timestamp.

2. get(string key, int timestamp)

    Returns a value such that set(key, value, timestamp_prev) was
    called previously, with timestamp_prev <= timestamp.
    If there are multiple such values, it returns the one with the largest
    timestamp_prev.
    If there are no values, it returns the empty string ("").


Example 1:

Input: inputs = ["TimeMap","set","get","get","set","get","get"],
       inputs = [[],["foo","bar",1],["foo",1],["foo",3],["foo","bar2",4],
                    ["foo",4],["foo",5]]
Output: [null,null,"bar","bar",null,"bar2","bar2"]
Explanation:   
TimeMap kv;   
kv.set("foo", "bar", 1); // store the key "foo" and value "bar" along with timestamp = 1   
kv.get("foo", 1);  // output "bar"   
kv.get("foo", 3);  // output "bar" since there is no value corresponding to foo at timestamp 3 and timestamp 2,
                   // then the only value is at timestamp 1 ie "bar"   
kv.set("foo", "bar2", 4);   
kv.get("foo", 4);  // output "bar2"   
kv.get("foo", 5);  // output "bar2"   

Example 2:

Input: inputs = ["TimeMap","set","set","get","get","get","get","get"],
       inputs = [[],["love","high",10],["love","low",20],["love",5],
                ["love",10],["love",15],["love",20],["love",25]]
Output: [null,null,null,"","high","high","low","low"]

 

Note:

    All key/value strings are lowercase.
    All key/value strings have length in the range [1, 100]
    The timestamps for all TimeMap.set operations are strictly increasing.
    1 <= timestamp <= 10^7
    TimeMap.set and TimeMap.get functions will be called a total of 120000
    times (combined) per test case.
*/

/*
    Approach: New class "Tuple" that stores the String value and the timestamp.
              HashMap of key to ArrayList of Tuple values. Set appends to end of
              key's ArrayList because timestamps are always ascending. Get performs
              Binary Search on the Key's ArrayList.
*/

    /** Initialize your data structure here. */

    class Tuple
    {
        String value;
        int timestamp;
        public Tuple(String value, int timestamp)
        {
            this.value = value;
            this.timestamp = timestamp;
        }
    }
    // O(1) set
    // O(log n) for get
    // Runtime: 122 ms, faster than 95.11% of Java online submissions
    // Memory Usage: 113.7 MB, less than 83.35% of Java online submissions
    HashMap<String, ArrayList<Tuple>> key_value_time;
    public Time_Based_Key_Value_Store() {
        key_value_time = new HashMap<String, ArrayList<Tuple>>();
    }

    public void set(String key, String value, int timestamp) {

        ArrayList<Tuple> values = key_value_time.get(key);
        if (values == null)
        {
            values = new ArrayList<Tuple>();
        }
        Tuple v = new Tuple(value, timestamp);
        values.add(v);
        key_value_time.put(key, values);
    }
    private int binarySearch(ArrayList<Tuple> time_stamps, int time, int start, int end)
    {
        if (start <= end)
        {
            int mid = (start + end) / 2;
            if (time_stamps.get(mid).timestamp == time)
            {
                return mid;
            }
            else if (time_stamps.get(mid).timestamp > time)
            {
                if (mid > 0 && time_stamps.get(mid - 1).timestamp < time)
                {
                    return mid - 1;
                }
                else
                {
                    return binarySearch(time_stamps, time, start, mid - 1);
                }
            }
            else
            {
                if (mid == time_stamps.size() - 1)
                {
                    return mid;
                }
                else
                {
                    return binarySearch(time_stamps, time, mid + 1, end);
                }
            }
        }
        else
        {
            return -1;
        }
    }
    public String get(String key, int timestamp) {
        ArrayList<Tuple> values = key_value_time.get(key);
        if (values == null)
        {
            return "";
        }
        int index = binarySearch(values, timestamp, 0, values.size() - 1);
        if (index == -1)
        {
            return "";
        }
        else
        {
            return values.get(index).value;
        }
    }
}

/**
 * Your TimeMap object will be instantiated and called as such:
 * TimeMap obj = new TimeMap();
 * obj.set(key,value,timestamp);
 * String param_2 = obj.get(key,timestamp);
 */

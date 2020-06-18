import java.awt.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Group_Anagrams {

/*
Rating: Medium

Given an array of strings, group anagrams together.

Example:

Input: ["eat", "tea", "tan", "ate", "nat", "bat"],
Output:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]

Note:

    All inputs will be in lowercase.
    The order of your output does not matter.

*/
    public List<List<String>> groupAnagrams(String[] strs) {
        // O(26kn) time where k is the average length of each string
        // O(kn) space
        // brute force -- sort
        ArrayList<List<String>> ans = new ArrayList<List<String>>();
        HashMap<ArrayList<Integer>, ArrayList<String>> groups = new HashMap<ArrayList<Integer>, ArrayList<String>>();
        for (int i = 0; i < strs.length; ++i)
        {

            ArrayList<Integer> ascii = new ArrayList<Integer>();
            for (int p = 0; p < 26; ++p)
            {
                ascii.add(0);
            }
            for(int k = 0; k < strs[i].length(); ++k)
            {

                // use array ASCII table 97 - 122 for lowercase letters
                Integer val = new Integer((strs[i].charAt(k)) - 97);
                int count = ascii.get(val) + 1;
                ascii.set(val, count);
            }
            ArrayList<String> list = groups.get(ascii);
            if (list == null)
            {
                list = new ArrayList<String>();
            }
            list.add(strs[i]);
            groups.put(ascii, list);
        }
        for (ArrayList<Integer> key: groups.keySet())
        {
            ans.add(groups.get(key));
        }
        return ans;
        
    }
}

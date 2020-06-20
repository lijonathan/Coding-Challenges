import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Find_Duplicate_File_in_System {

/*
Rating: Medium

Given a list of directory info including directory path, and all the
files with contents in this directory, you need to find out all the groups
of duplicate files in the file system in terms of their paths.

A group of duplicate files consists of at least two files that have exactly
the same content.

A single directory info string in the input list has
the following format:

"root/d1/d2/.../dm f1.txt(f1_content) f2.txt(f2_content) ... fn.txt(fn_content)"

It means there are n files (f1.txt, f2.txt ... fn.txt with content f1_content,
f2_content ... fn_content, respectively) in directory root/d1/d2/.../dm.
Note that n >= 1 and m >= 0. If m = 0, it means the directory is just
the root directory.

The output is a list of group of duplicate file paths.
For each group, it contains all the file paths of the
files that have the same content. A file path is a string
that has the following format:

"directory_path/file_name.txt"

Example 1:

Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)",
"root 4.txt(efgh)"]
Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]

 

Note:

    No order is required for the final output.
    You may assume the directory name, file name and file content only
    has letters and digits, and the length of file content is in the range of [1,50].
    The number of files given is in the range of [1,20000].
    You may assume no files or directories share the same name in the same directory.
    You may assume each given directory info represents a unique directory.
    Directory path and file info are separated by a single blank space.

 
Follow-up beyond contest:

    Imagine you are given a real file system, how will you search files? DFS or BFS?
    If the file content is very large (GB level), how will you modify your solution?
    If you can only read the file by 1kb each time, how will you modify your solution?
    What is the time complexity of your modified solution? What is the most time-consuming part
    and memory consuming part of it? How to optimize?
    How to make sure the duplicated files you find are not false positive?

*/

/*
    Approach: Split paths, and data, use HashMap to append all files with the same data together.  
*/

    // O(m) runtime, m = # of directories
    // O(p * n) space, n = # of unique data, p = avg number of directories for a data
    // Runtime: 26 ms, faster than 71.61% of Java online submissions
    // Memory Usage: 48.7 MB, less than 65.88% of Java online submissions

    public List<List<String>> findDuplicate(String[] paths) {
        HashMap<String, ArrayList<String>> contents = new HashMap<String, ArrayList<String>>();
        for (int i = 0; i < paths.length; ++i)
        {
            String[] data = paths[i].split(" ");
            String directory = data[0];
            for (int k = 1; k < data.length; ++k)
            {
                String[] file_data_split = data[k].split("\\(");
                ArrayList<String> files = contents.get(file_data_split[1]);
                if (files == null)
                {
                    files = new ArrayList<String>();
                }
                files.add(directory + "/" + file_data_split[0]);
                contents.put(file_data_split[1], files);
            }
        }
        ArrayList<List<String>> groups = new ArrayList<List<String>>();
        for (String key: contents.keySet())
        {
            ArrayList<String> al = contents.get(key);
            if (al.size() != 1)
            {
                groups.add(al);
            }
        }
        return groups;
    }
}

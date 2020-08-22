import java.util.HashMap;

public class Longest_Absolute_File_Path {
/*
Rating: Medium

Suppose we abstract our file system by a string in the following manner:

The string "dir\n\tsubdir1\n\tsubdir2\n\t\tfile.ext" represents:

dir
    subdir1
    subdir2
        file.ext

The directory dir contains an empty sub-directory subdir1 and
a sub-directory subdir2 containing a file file.ext.

The string "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" represents:

dir
    subdir1
        file1.ext
        subsubdir1
    subdir2
        subsubdir2
            file2.ext

The directory dir contains two sub-directories subdir1 and subdir2. subdir1
contains a file file1.ext and an empty second-level sub-directory subsubdir1.
subdir2 contains a second-level sub-directory subsubdir2 containing a file file2.ext.

We are interested in finding the longest (number of characters) absolute
path to a file within our file system. For example, in the second example above, the longest absolute path is
"dir/subdir2/subsubdir2/file2.ext", and its length is 32 (not including the double quotes).

Given a string representing the file system in the above format,
return the length of the longest absolute path to file in the abstracted
file system. If there is no file in the system, return 0.

Note:

    The name of a file contains at least a . and an extension.
    The name of a directory or sub-directory will not contain a ..

Time complexity required: O(n) where n is the size of the input string.

Notice that a/aa/aaa/file1.txt is not the longest file path, if there is another path aaaaaaaaaaaaaaaaaaaaa/sth.png.
*/

/*
    Approach: Split the string on \n character. For each string in the split array, check what level it is at
              by splitting it on \t and the level = # of cells in split array - 1. If it is a directory (it does not contain
              the character "."), put it into a map with the level as the key and the dir as the value.

              Have another HashMap that maps String's to their respective lengths. If it is a directory, get the previous level
              directory from the dirs HashMap and use that string to get the length. Add the current directory length
              + 1 to the previous length and put that item in the lengths directory.

              If it is a file, get the length by finding the level it is on and appending the level directory's
              size from the lengths map to the current file name size + 1. Track the largest length.
*/

    // O(n) runtime
    // (n) space
    // Runtime: 1 ms, faster than 85.71% of Java online submissions
    // Memory Usage: 37.3 MB, less than 77.24% of Java online submissions
    public int lengthLongestPath(String input) {
        String[] items = input.split("\n");
        HashMap<Integer, String> dirs = new HashMap<Integer, String>();
        HashMap<String, Integer> lengths = new HashMap<String, Integer>();
        int max_length = 0;
        for (int i = 0; i < items.length; ++i)
        {
            String[] split = items[i].split("\t");
            int levels = split.length - 1;
            String item = split[split.length - 1];
            if (!item.contains("."))
            {
                // dir
                dirs.put(levels, item);
                if (levels == 0)
                {
                    lengths.put(item, item.length());
                }
                else
                {
                    int size = lengths.get(dirs.get(levels - 1)) + 1 + item.length();
                    lengths.put(item, size);
                }
            }
            else
            {
                // file
                int size = item.length();
                if (levels != 0)
                {
                    size = lengths.get(dirs.get(levels - 1)) + 1 + size;
                }
                if (size > max_length)
                {
                    max_length = size;
                }
            }
        }
        return max_length;
    }
}


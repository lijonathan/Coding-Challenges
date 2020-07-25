public class H_Tree_Construction {
/*
An H-tree is a geometric shape that consists of a repeating pattern
resembles the letter “H”.

It can be constructed by starting with a line segment of arbitrary
length, drawing two segments of the same length at right angles to
the first through its endpoints, and continuing in the same vein,
reducing (dividing) the length of the line segments drawn at each stage by √2.

Here are some examples of H-trees at different levels of depth:

alt depth = 1

alt depth = 2

alt depth = 3

Write a function drawHTree that constructs an H-tree, given its
center (x and y coordinates), a starting length, and depth. Assume
that the starting line is parallel to the X-axis.

depth = 1       depth = 2
                |-| |-|         
|---|            |---|
                |-| |-|

Use the function drawLine provided to implement your algorithm.
In a production code, a drawLine function would render a real line
between two points. However, this is not a real production environment,
so to make things easier, implement drawLine such that it simply prints
its arguments (the print format is left to your discretion).

Analyze the time and space complexity of your algorithm. In your analysis,
assume that drawLine's time and space complexities are constant, i.e. O(1).

Constraints:

    [time limit] 5000ms

    [input] double x

    [input] double y

    [input] double length

    [input] double depth
        0 ≤ depth ≤ 10
*/

/*
    Approach: Recursively draw the tree by tracking the current depth and center point and the
              length. At each recursive call, calculate the points to draw the three lines for the H
              at and recursively call one depth further. At the target depth, only draw the line.
*/

    static public void drawLine(double x_1, double y_1, double x_2, double y_2)
    {
        System.out.println(x_1 + "  " + y_1 + " : " + x_2 + " " + y_2);
    }
    // O(4^depth) runtime --> 4 recursive calls per stack frame
    // O(depth) space
    static public void drawHTree(double x, double y, double length, double depth,
            int c_depth)
    {
        if (c_depth == depth)
        {
            drawLine(x - length/2, y, x + length / 2, y);
            drawLine(x - length / 2, y - length / 2, x - length / 2, y + length / 2);
            drawLine(x + length / 2, y - length / 2, x + length / 2, y + length / 2);
        }
        else if (c_depth < depth)
        {
            drawLine(x - length / 2, y, x + length / 2, y);
            drawLine(x - length / 2, y - length / 2, x - length / 2, y + length / 2);
            drawLine(x + length / 2, y - length / 2, x + length / 2, y + length / 2);

            drawHTree(x - length / 2, y - length / 2, length / Math.sqrt(2), depth, c_depth + 1);
            drawHTree(x - length / 2, y + length / 2, length / Math.sqrt(2), depth, c_depth + 1);

            drawHTree(x + length / 2, y - length / 2, length / Math.sqrt(2), depth, c_depth + 1);
            drawHTree(x + length / 2, y - length / 2, length / Math.sqrt(2), depth, c_depth + 1);
        }
    }
}

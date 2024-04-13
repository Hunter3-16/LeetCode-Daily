/*
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing only 1's and return its area.

 

Example 1:


Input: matrix = [["1","0","1","0","0"],["1","0","1","1","1"],["1","1","1","1","1"],["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.
Example 2:

Input: matrix = [["0"]]
Output: 0
Example 3:

Input: matrix = [["1"]]
Output: 1
 

Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.
*/

class Solution {
    public int maximalRectangle(char[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int[] help= new int[col];
        for(int j = 0 ; j < help.length ; j++)
        {
            if(matrix[0][j] == '1')
            help[j] = 1;
            else
            help[j] = 0;
        }
        int max = getMaxArea(help);
        for(int i = 1 ; i < row ; i++)
        {
            for(int j = 0 ; j < col ; j++)
            {
                if(matrix[i][j] == '1')
                    help[j] = help[j] + 1;
                else
                    help[j] = 0;
            }
            max = Math.max(getMaxArea(help) , max);
        }
       return max;
    }
    public static int getMaxArea(int hist[]) 
    {
        int[] left = new int[hist.length];
        int[] right = new int[hist.length];
        Stack<Integer> stack = new Stack<>();
        for(int i = 0 ; i < hist.length ; i++)
        {
            while(!stack.isEmpty() && hist[stack.peek()] >= hist[i])
                stack.pop();
            if(stack.isEmpty())
                left[i] = 0;
            else
                left[i] = stack.peek() + 1;
            stack.push(i);
        }
        while(!stack.isEmpty())
            stack.pop();
        for(int i = hist.length - 1 ; i >= 0 ; i--)
        {
            while(!stack.isEmpty() && hist[stack.peek()] >= hist[i])
                stack.pop();
            if(stack.isEmpty())
                right[i] = hist.length - 1;
            else
                right[i] = stack.peek() - 1;
            stack.push(i);
        }
        int max = 0;
        int ans = 0;
        for(int i = 0 ; i < hist.length ; i++)
        {
            max = hist[i] * (right[i] - left[i] + 1);
            if(max > ans)
                ans = max;
        }
        return ans;
    }
}

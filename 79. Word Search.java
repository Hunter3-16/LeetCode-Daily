/*
Given an m x n grid of characters board and a string word, return true if word exists in the grid.

The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.

 

Example 1:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
Output: true
Example 2:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
Output: true
Example 3:


Input: board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
Output: false
 

Constraints:

m == board.length
n = board[i].length
1 <= m, n <= 6
1 <= word.length <= 15
board and word consists of only lowercase and uppercase English letters.
 

Follow up: Could you use search pruning to make your solution faster with a larger board?
  */

class Solution {
    public boolean exist(char[][] b, String word) 
    {
        for(int r = 0 ; r < b.length ; r++)
            for(int c = 0 ; c < b[0].length ; c++)
                if(b[r][c] == word.charAt(0) && help(b,word,0,r,c))
                    return true;
        return false;
    }
    
    public boolean help(char[][] b, String word, int start, int r, int c)
    {
        if(word.length() <= start)
            return true;
        
        if(r < 0 || c < 0 || r >= b.length || c >= b[0].length || b[r][c] == '0' || b[r][c] != word.charAt(start))
            return false;
        
        char tmp = b[r][c];
        b[r][c] = '0';

        if(help(b,word,start+1,r+1,c) || help(b,word,start+1,r-1,c) || help(b,word,start+1,r,c+1) || help(b,word,start+1,r,c-1))
            return true;
        
        b[r][c] = tmp;
        
        return false;
    }
}

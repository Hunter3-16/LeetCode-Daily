/*
Given a string s and a dictionary of strings wordDict, add spaces in s to construct a sentence where each word is a valid dictionary word. Return all such possible sentences in any order.

Note that the same word in the dictionary may be reused multiple times in the segmentation.

 

Example 1:

Input: s = "catsanddog", wordDict = ["cat","cats","and","sand","dog"]
Output: ["cats and dog","cat sand dog"]
Example 2:

Input: s = "pineapplepenapple", wordDict = ["apple","pen","applepen","pine","pineapple"]
Output: ["pine apple pen apple","pineapple pen apple","pine applepen apple"]
Explanation: Note that you are allowed to reuse a dictionary word.
Example 3:

Input: s = "catsandog", wordDict = ["cats","dog","sand","and","cat"]
Output: []
 

Constraints:

1 <= s.length <= 20
1 <= wordDict.length <= 1000
1 <= wordDict[i].length <= 10
s and wordDict[i] consist of only lowercase English letters.
All the strings of wordDict are unique.
Input is generated in a way that the length of the answer doesn't exceed 105.
*/

class Solution {
    private String s;
    private Map<Integer, String> substringMap;
    public List<String> wordBreak(String s, List<String> wordDict) 
    {
        this.s = s;
        substringMap = new HashMap<>();
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) 
        {
            substringMap.put(i, new String(array, i, array.length - i));
        }
        Set<String> wordSet = new HashSet<>(wordDict);
        Map<Integer, List<String>> memo = new HashMap<>();
        return dfs(s, 0, wordSet, memo);
    }
    private List<String> dfs(String s, int index, Set<String> wordSet, Map<Integer, List<String>> memo) 
    {
        if (memo.containsKey(index)) 
        {
            return memo.get(index);
        }
        List<String> result = new ArrayList<>();
        if (s == null || s.length() == 0) 
        {
            result.add("");
            return result;
        }
        StringBuilder sb = new StringBuilder();
        for (String word : wordSet) 
        {
            if (s.startsWith(word)) 
            {
                List<String> nextResult = dfs(substringMap.get(index + word.length()), index + word.length(), wordSet, memo);
                for (String nextComponent : nextResult) 
                {
                    sb.setLength(0);
                    sb.append(word).append(nextComponent.isEmpty() ? "" : " ").append(nextComponent);
                    result.add(sb.toString());
                }
            }
        }
        memo.put(index, result);
        return result;
    }
}

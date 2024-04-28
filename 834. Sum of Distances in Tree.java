/*
There is an undirected connected tree with n nodes labeled from 0 to n - 1 and n - 1 edges.

You are given the integer n and the array edges where edges[i] = [ai, bi] indicates that there is an edge between nodes ai and bi in the tree.

Return an array answer of length n where answer[i] is the sum of the distances between the ith node in the tree and all other nodes.

 

Example 1:


Input: n = 6, edges = [[0,1],[0,2],[2,3],[2,4],[2,5]]
Output: [8,12,6,10,10,10]
Explanation: The tree is shown above.
We can see that dist(0,1) + dist(0,2) + dist(0,3) + dist(0,4) + dist(0,5)
equals 1 + 1 + 2 + 2 + 2 = 8.
Hence, answer[0] = 8, and so on.
Example 2:


Input: n = 1, edges = []
Output: [0]
Example 3:


Input: n = 2, edges = [[1,0]]
Output: [1,1]
 

Constraints:

1 <= n <= 3 * 104
edges.length == n - 1
edges[i].length == 2
0 <= ai, bi < n
ai != bi
The given input represents a valid tree.
*/

public class Solution {
    public int[] sumOfDistancesInTree(int n, int[][] edges) 
    {
        List<List<Integer>> tree = new ArrayList<>();
        int[] subtreeSizes = new int[n];
        int[] distanceSums = new int[n];
        for (int i = 0; i < n; i++) 
        {
            tree.add(new ArrayList<>());
        }
        for (int[] edge : edges) 
        {
            tree.get(edge[0]).add(edge[1]);
            tree.get(edge[1]).add(edge[0]);
        }
        dfs1(0, -1, tree, subtreeSizes, distanceSums);
        dfs2(0, -1, tree, subtreeSizes, distanceSums);
        return distanceSums;
    }
    public void dfs1(int node, int parent, List<List<Integer>> tree, int[] subtreeSizes, int[] distanceSums) 
    {
        subtreeSizes[node] = 1; 
        for (int neighbor : tree.get(node)) 
        {
            if (neighbor == parent) 
                continue; 
            dfs1(neighbor, node, tree, subtreeSizes, distanceSums);
            subtreeSizes[node] = subtreeSizes[node] + subtreeSizes[neighbor];
            distanceSums[0] = distanceSums[0] + distanceSums[neighbor] + subtreeSizes[neighbor];
        }
    }
    public void dfs2(int node, int parent, List<List<Integer>> tree, int[] subtreeSizes, int[] distanceSums) 
    {
        for (int neighbor : tree.get(node)) 
        {
            if (neighbor == parent) 
                continue;
            distanceSums[neighbor] = distanceSums[node] - subtreeSizes[neighbor] + (subtreeSizes.length - subtreeSizes[neighbor]);
            dfs2(neighbor, node, tree, subtreeSizes, distanceSums);
        }
    }
}

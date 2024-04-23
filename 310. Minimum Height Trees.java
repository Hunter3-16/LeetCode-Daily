/*
A tree is an undirected graph in which any two vertices are connected by exactly one path. In other words, any connected graph without simple cycles is a tree.

Given a tree of n nodes labelled from 0 to n - 1, and an array of n - 1 edges where edges[i] = [ai, bi] indicates that there is an undirected edge between the two nodes ai and bi in the tree, you can choose any node of the tree as the root. When you select a node x as the root, the result tree has height h. Among all possible rooted trees, those with minimum height (i.e. min(h))  are called minimum height trees (MHTs).

Return a list of all MHTs' root labels. You can return the answer in any order.

The height of a rooted tree is the number of edges on the longest downward path between the root and a leaf.

 

Example 1:


Input: n = 4, edges = [[1,0],[1,2],[1,3]]
Output: [1]
Explanation: As shown, the height of the tree is 1 when the root is the node with label 1 which is the only MHT.
Example 2:


Input: n = 6, edges = [[3,0],[3,1],[3,2],[3,4],[5,4]]
Output: [3,4]
 

Constraints:

1 <= n <= 2 * 104
edges.length == n - 1
0 <= ai, bi < n
ai != bi
All the pairs (ai, bi) are distinct.
The given input is guaranteed to be a tree and there will be no repeated edges.
*/

class Solution {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        List<Integer> list = new ArrayList<>();
        if (edges.length == 0) 
        {
            list.add(0);
            return list;
        }
        int[] indegree = new int[n];
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(i, new ArrayList<>());
        for (int i = 0; i < edges.length; i++) 
        {
            indegree[edges[i][0]]++;
            indegree[edges[i][1]]++;
            map.get(edges[i][0]).add(edges[i][1]);
            map.get(edges[i][1]).add(edges[i][0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (indegree[i] == 1)
                q.offer(i);
        int count = 0;
        while (!q.isEmpty()) 
        {
            int size = q.size();
            count += size;
            for (int i = 0; i < size; i++) 
            {
                Integer id = q.poll();
                indegree[id]--;
                if (count == n)
                    list.add(id);
                for (Integer adjId : map.get(id)) 
                {
                    if (indegree[adjId] != 0) 
                    {
                        indegree[adjId]--;
                        if (indegree[adjId] == 1)
                            q.offer(adjId);
                    }
                }
            }
        }
        return list;
    }
}

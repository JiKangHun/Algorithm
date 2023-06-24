import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
        
        for(int i=0; i<n+1; i++){
            graph.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<edge.length; i++){
            int node1 = edge[i][0];
            int node2 = edge[i][1];
            
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        
        int[] cnt = new int[n+1];
        
        Queue<Integer> q = new ArrayDeque<>();
        q.add(1);
        cnt[1] = 1;
        int edgeCnt = 1;
        
        while(!q.isEmpty()){
        
            int size = q.size();  
            ++edgeCnt;
            
            for(int i=0; i<size; i++) {
            
                int cur = q.poll();

                for(int j=0; j<graph.get(cur).size(); j++){
                    
                    int next = graph.get(cur).get(j);
                    if(cnt[next] != 0) continue;
                    q.add(next);
                    cnt[next] = edgeCnt;
                }

            }
                    
        }
        
        for(int i=2; i<=n; i++){
            if(cnt[i] == edgeCnt-1) ++answer;
        }
        
        return answer;
    }
}
import java.util.*;

class Solution {
    

    public static class Node{
        int vertex;
        int weight;
        
        public Node(int vertex, int weight){
            this.vertex = vertex;
            this.weight = weight;
        }
    }
    
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        
        int[] intensity = new int[n+1];
        Arrays.fill(intensity, Integer.MAX_VALUE);
        
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> o1.weight - o2.weight );

        int[] type = new int[n+1]; //1은 출입구, 2는 산봉우리
        for(int i=0; i<gates.length; i++){
            type[gates[i]] = 1;
            pq.add(new Node(gates[i],0));
            intensity[gates[i]] = 0;
        }
        
        for(int i=0; i<summits.length; i++){
            type[summits[i]] = 2;
        }
        
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<Node>());
        }
        for(int i=0; i<paths.length; i++){
            int from = paths[i][0];
            int to = paths[i][1];
            int weight = paths[i][2];
            
            if(type[from] == 1 || type[to] == 2){
                graph.get(from).add(new Node(to, weight));
            }else if(type[from] == 2 || type[to] == 1){
                graph.get(to).add(new Node(from, weight));
            }else{
                graph.get(from).add(new Node(to, weight));
                graph.get(to).add(new Node(from, weight));
            }
        }
        
        
        
        while(!pq.isEmpty()){
            
            Node cur = pq.poll();
            int weight = cur.weight;
            if(intensity[cur.vertex] < weight) continue;
            
            for(int i=0; i<graph.get(cur.vertex).size(); i++){
                
                Node next = graph.get(cur.vertex).get(i);
                
                if(intensity[next.vertex] > Math.max(intensity[cur.vertex], next.weight)){
                    intensity[next.vertex] = Math.max(intensity[cur.vertex], next.weight);
                    pq.add(new Node(next.vertex, intensity[next.vertex]));
                }
                
            }
                
        }
        
        int summit = 0;
        int intense = Integer.MAX_VALUE;
        
        for(int i=1; i<=n; i++){
            System.out.print(intensity[i]);
        }
        Arrays.sort(summits);
        for(int i=0; i<summits.length; i++){
            
            if(intensity[summits[i]] < intense){
                intense = intensity[summits[i]];
                answer[0] = summits[i];
            }
            
        }
        
        answer[1] = intense;
        return answer;
    }
    

}
import java.util.*;

// 다익스트라의 양방향은 어떻게 메모리초과가 발생하지 않고 탐색을 멈추는가 bfs와 달리?

class Solution {
    
    static class Node{    
        int value;
        int distance;
        
        public Node(int value, int distance){
            this.value = value;
            this.distance = distance;
        }        
    }
    
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        int[] d = new int[N+1];
        Arrays.fill(d, Integer.MAX_VALUE);
        
        ArrayList<ArrayList<Node>> map = new ArrayList<>();
        
        for(int i=0; i<=N; i++){
            map.add(new ArrayList<Node>());
        }
        
        for(int i=0; i<road.length; i++){
            int from = road[i][0];
            int to = road[i][1];
            int distance = road[i][2];
            map.get(from).add(new Node(to, distance));
            map.get(to).add(new Node(from, distance));
        }
        
        for(int i=0; i<map.size(); i++){
            for(int j=0; j<map.get(i).size(); j++){
                System.out.print(map.get(i).get(j).distance);
            }
        }
        
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1, o2) -> { 
            return o1.distance - o2.distance;
        });
        
        Node node = new Node(1, 0);
        pq.add(node);
        d[1] = 0;
        
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(d[cur.value] < cur.distance) continue;

            for(int i=0; i<map.get(cur.value).size(); i++){
                Node next = map.get(cur.value).get(i);
                int cost = d[cur.value] + next.distance;
                
                if(d[next.value] > cost){
                    d[next.value] = cost;
                    pq.add(new Node(next.value, cost));
                }
            }
        }
        
        for(int i=1; i<=N; i++) {
            if(d[i] <= K) ++answer;
        }

        return answer;
    }
}
import java.util.*;

class Solution {
    
    static class Node{
        
        int vertex;
        int sheep;
        int wolf;
        ArrayList<Node> visited;
        
        public Node(int vertex, int sheep, int wolf, ArrayList<Node> visited){
            this.vertex = vertex;
            this.sheep = sheep;
            this.wolf = wolf;
            this.visited = visited;
        }
        
        public Node(int vertex, int sheep, int wolf){
            this.vertex = vertex;
            this.sheep = sheep;
            this.wolf = wolf;
        }
    } 
    
    public int solution(int[] info, int[][] edges) {
        
        int answer = 0;
        int N = info.length;
        
        ArrayList<ArrayList<Integer>> tree = new ArrayList<>();
        for(int i=0; i<N; i++){
            tree.add(new ArrayList());
        }
    
        for(int i=0; i<edges.length; i++){

            int node1 = edges[i][0];
            int node2 = edges[i][1];

            tree.get(node1).add(node2);
            tree.get(node2).add(node1);
        }

        Queue<Node> q  = new ArrayDeque<>();
        Node root = new Node(0,1,0, new ArrayList<Node>());
        root.visited.add(new Node(0,1,0));
        q.add(root);

        while(!q.isEmpty()){
            
            Node cur = q.poll();
            answer = Math.max(answer, cur.sheep);
            
            ArrayList<Node> visitList = cur.visited;
    
            outer:
            for(int i=0; i<tree.get(cur.vertex).size(); i++){
 
                int next = tree.get(cur.vertex).get(i);
                boolean isVisited = false;
                for(Node node: visitList){
                    if(node.vertex == next) {
                        isVisited = true;
                        if(cur.sheep == node.sheep && cur.wolf == node.wolf) continue outer;
                    }
                }

                if(!isVisited && info[next] == 1 && cur.sheep <= cur.wolf + 1) continue;
                    
                ArrayList<Node> newVisitList = new ArrayList<Node>();
                if(!isVisited){
                    for(int j=0; j<cur.visited.size(); j++){
                        newVisitList.add(cur.visited.get(j));
                    }
                    

                    if(info[next]==1) {
                        newVisitList.add(new Node(next,cur.sheep, cur.wolf+1));
                        q.add(new Node(next, cur.sheep, cur.wolf+1, newVisitList));
                    }else{
                        newVisitList.add(new Node(next,cur.sheep+1, cur.wolf));
                        q.add(new Node(next, cur.sheep+1, cur.wolf, newVisitList));
                    }
                }else{
                    for(int j=0; j<cur.visited.size(); j++){
                        newVisitList.add(cur.visited.get(j));
                    }
                    newVisitList.add(new Node(next,cur.sheep, cur.wolf));
                    q.add(new Node(next, cur.sheep, cur.wolf, newVisitList));
                }            
            }
        }
        return answer;
    }
}
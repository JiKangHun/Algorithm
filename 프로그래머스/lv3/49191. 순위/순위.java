import java.util.*;

class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        //모든 선수와의 승패를 알면 순위를 결정할 수 있다
        //a행의 각 열의 값은 내가 승리했는지 여부
        //a열의 각 행은 내가 패배했는지 여부
        //각 행 + 각 열에 0이 아닌 값의 개수 = 승패결정의 개수
        //승패 결정의 개수가 n-1개 라면 승패 결정 = 순위 알 수 있음
        
        int[][] graph = new int[n+1][n+1];
        
        for(int i=0; i<results.length; i++){
            graph[results[i][0]][results[i][1]] = 1;
            
        }
        
        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(i==j) continue;
                    if(graph[i][k] + graph[k][j] == 2) graph[i][j] = 1;
                }
            }
        }
        
        
        for(int i=1; i<=n; i++){
            int cnt = 0;
            for(int j=1; j<=n; j++){
                if(graph[i][j] == 1) ++cnt;
            }
            
            for(int j=1; j<=n; j++){
                if(graph[j][i] == 1) ++cnt;
            }
            if(cnt == n-1) ++answer;
        }
        return answer;
    }
}
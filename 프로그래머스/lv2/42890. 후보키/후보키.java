import java.util.*;

class Solution {
    
    static int[] p;
    static int n, answer;
    static ArrayList<int[]> keyList = new ArrayList<>();
    public int solution(String[][] relation) {
        answer = 0;
        n = relation[0].length;
        
        
        // 속성 1개부터 최대 갯수까지 조합을 뽑아봄
        // 만약 후보키인 조합이면 저장
        // 다음 조합에서는 저장된 후보키 조합이 포함된 조합이면 CONTINUE
        // 조합에 대해서는 +연산으로 SET에 넣어서 사이즈가 튜플 개수보다 작으면 불가능, 튜플개수이면 가능
        // 이미 선정된 조합 건너뛰는 건 어떻게 구현?
        for(int i=1; i<=n; i++){
            p = new int[i];    
            combi(i, 0, 0, relation);
        }    
        
        return answer;
    }
    
    public static void combi(int total, int cnt, int start, String[][] relation){
        
        if(cnt == total){
            
            
            boolean[] selected = new boolean[n];
            for(int i=0; i<total; i++){
                selected[p[i]] = true;
            }
            
            //0, 2가 후보키로 이미 선정됐다면, 0,1,2는 후보키가 될 수 없음
            for(int i=0; i<keyList.size(); i++){
                
                int[] keyCombi = keyList.get(i);
                boolean isPossible = false;
                
                for(int j=0; j<keyCombi.length; j++){
                    
                    if(!selected[keyCombi[j]]) isPossible = true;
                    
                }
                
                if(!isPossible) return;
                
            }
            
            Set<String> set = new HashSet<>();
            
            for(int i=0; i<relation.length; i++){
                String key = "";
                for(int j=0; j<total; j++){
                    key += relation[i][p[j]]; 
                }
                set.add(key);
            }
            
            if(set.size() == relation.length){
                ++answer;
                keyList.add(p.clone());
            }
            
            return;
        }
        
        //칼럼 번호 뽑기
        for(int i=start; i<n; i++){
            
            p[cnt] = i;
            combi(total, cnt+1, i+1, relation);
        }
    }
}
import java.util.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        int[] answer = new int[query.length];
        
        HashMap<String, int[]> map = new HashMap<>();
        
        //조합을 KEY로 해시맵에 저장
        for(int i=0; i<info.length; i++){
            String[] applicant = info[i].split("\\s");
            String group = "";
            for(int j=0; j<4; j++){
                group += applicant[j]+" ";
            }
            int score = Integer.parseInt(applicant[4]);
            
            if(map.get(group) == null) map.put(group, new int[100001]);     
            ++map.get(group)[score];
            
        }
        //각 조합의 점수 누적합
        for(String key: map.keySet()){
            
            int[] scoreBoard = map.get(key);
            for(int i=1; i<=100000; i++){
                scoreBoard[i] += scoreBoard[i-1];
            }
        }
        
        for(int i=0; i<query.length; i++){
            
            String[] str = query[i].split("\\s");
            int limit = Integer.parseInt(str[7])-1;
            int sum = 0;
            
            outer:
            for(String key: map.keySet()){
                
                int[] scoreBoard = map.get(key);
                
                for(int j=0; j<7; j++){
                    if( str[j].equals("-") || str[j].equals("and") ) continue;
                    if(!key.contains(str[j])) continue outer;           
                }
                
                sum += scoreBoard[100000] - scoreBoard[limit];
            }
            answer[i] = sum;
            
        }
        return answer;
    }
}
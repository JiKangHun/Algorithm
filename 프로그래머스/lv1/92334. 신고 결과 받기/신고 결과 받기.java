import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        
        HashMap<String, Set<String>> rptList = new HashMap<>();
        int[] answer = new int[id_list.length];
        
        //각 유저별 신고 받은 횟수를 알아야 함
        //각 유저별 신고한 유저의 목록을 알아야 함
        //신고받은 횟수가 K번 이상인 유저를 신고한 유저의 경우 메일 수를 플러스
        
        for(int i=0; i<report.length; i++){
            String[] reportId = report[i].split("\\s");
            
            
            //유저별 신고한 사람 리스트 갱신
            if(rptList.get(reportId[1]) != null){
                rptList.get(reportId[1]).add(reportId[0]);
            }else{
                Set<String> set = new HashSet<>();
                set.add(reportId[0]);
                rptList.put(reportId[1], set);
            }
        }
        
        for(String key: rptList.keySet()){
            if(rptList.get(key).size() >= k){
               Set<String> set = rptList.get(key);
                
                for(String id : set){
                    for(int i=0; i<id_list.length; i++){
                        if(id_list[i].equals(id)) ++answer[i];
                    }    
                }
                
            }
        }
        
        return answer;
    }
}
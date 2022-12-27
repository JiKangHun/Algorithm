import java.util.*;

class Solution {

    public String solution(String[] survey, int[] choices) {
        String answer = "";
        
        HashMap<Character, Integer> typeScore = new HashMap<>();
        typeScore.put('R', 0);
        typeScore.put('T', 0);
        typeScore.put('F', 0);
        typeScore.put('C', 0);
        typeScore.put('M', 0);
        typeScore.put('J', 0);
        typeScore.put('A', 0);
        typeScore.put('N', 0);
        
        for(int i=0; i<survey.length; i++){
            
            int[] result = calScore(survey[i], choices[i]); //1번 열: 성격 , 2번 열: 점수, 1번 열이 0이면 continue?
            
            if(result[0] == 0) continue; // 4번 선택했을 때 점수변화 없음
            
            typeScore.put((char)result[0], typeScore.get((char)result[0])+result[1]); //결과 점수에 반영
        }
        
        answer = makeAnswer(typeScore); // 점수 비교해서 정답 만들기
        
        return answer;
    }
    
    public static String makeAnswer(HashMap<Character, Integer> typeScore){
        
        String answer = "";
        
        if(typeScore.get('R') > typeScore.get('T')){
            answer += "R";
        }else if(typeScore.get('R') < typeScore.get('T')){
            answer += "T";
        }else {
            answer += "R";
        }
        
        if(typeScore.get('C') > typeScore.get('F')){
            answer += "C";
        }else if(typeScore.get('C') < typeScore.get('F')){
            answer += "F";
        }else {
            answer += "C";
        }
        
        if(typeScore.get('J') > typeScore.get('M')){
            answer += "J";
        }else if(typeScore.get('J') < typeScore.get('M')){
            answer += "M";
        }else {
            answer += "J";
        }
        
        if(typeScore.get('A') > typeScore.get('N')){
            answer += "A";
        }else if(typeScore.get('A') < typeScore.get('N')){
            answer += "N";
        }else {
            answer += "A";
        }
        return answer;
    }
    
    public static int[] calScore(String survey, int choice){
        
        int[] result = new int[2];
        
        if(choice == 4) {
            return result;
        }
        
        if(choice < 4){
            result[0] = survey.charAt(0); // int로 자동형변환 됨
            if(choice == 1){
                result[1] = 3;            
            }else if(choice == 2){
                result[1] = 2;
            }else if(choice == 3){
                result[1] = 1;
            }
        }else{
            result[0] = survey.charAt(1); // int로 자동형변환 됨
            if(choice ==5){
                result[1] = 1;
            }else if(choice == 6){
                result[1] = 2;
            }else if(choice == 7){
                result[1] = 3;
            }
        }
        return result;
    }
}
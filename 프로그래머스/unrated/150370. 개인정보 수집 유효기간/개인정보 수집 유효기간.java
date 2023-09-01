import java.util.*;

class Solution {
    
    public int[] solution(String today, String[] terms, String[] privacies) {
        
        String[] convertToday = today.split("\\.");
        
        int todayYear = Integer.parseInt(convertToday[0]);
        int todayMonth = Integer.parseInt(convertToday[1]);
        int todayDay = Integer.parseInt(convertToday[2]);
        
        int[] termByType = new int[26];
        for(int i=0; i<terms.length; i++){
            String[] typeAndDuration = terms[i].split(" ");
            termByType[typeAndDuration[0].charAt(0)-65] = Integer.parseInt(typeAndDuration[1]);
        }
        
        PriorityQueue<Integer> expiredList = new PriorityQueue<>();
        
        for(int i=0; i<privacies.length; i++){
            
            String[] dateAndType = privacies[i].split(" ");
            String[] collectDate = dateAndType[0].split("\\.");
            int rule = dateAndType[1].charAt(0)-65;
            int year = Integer.parseInt(collectDate[0]);
            int month = Integer.parseInt(collectDate[1]);
            int day = Integer.parseInt(collectDate[2]);
            
            int[] convertDate = makeDate(termByType[rule], year, month, day);
            
            if(todayYear > convertDate[0]){
                expiredList.add(i+1);
                continue;
            }else if(todayYear == convertDate[0]){
                if(todayMonth > convertDate[1]){
                    expiredList.add(i+1);
                    continue;
                }else if(todayMonth == convertDate[1]){
                    if(todayDay > convertDate[2]){
                        expiredList.add(i+1);
                        
                    }
                }
            }

        }
        
        int qSize = expiredList.size();
        int[] answer = new int[qSize];
        
        for(int i=0; i<qSize; i++){
            answer[i] = expiredList.poll();
        }
        
        return answer;
    }
    
    public static int[] makeDate(int duration, int year, int month, int day){
        
        int[] convertDate = new int[3];
        //년도를 어떻게 변환
        //(수집 달 + 약관의 달) % 12 == 0 이면 (몫 - 1) 만큼 +
        //(수집 달 + 약관의 달) % 12 != 0 이면 몫만큼 +
        
        if((month + duration) % 12 == 0){
            convertDate[0] = year + (duration + month)/12 -1;
        }else{
            convertDate[0] = year + (duration + month)/12;
        }
        
        //5월 1일 + 7개월 -> 11.28
        //5월 2일 + 7개월 -> 12.1
        //19.5월 1일 + 8개월 -> 19.12.28
        //5월 1일 + 9개월 -> 1.28
        
        //달을 어떻게 변환
        
        //12보다 크면 % 12, 아니면 그냥 더하기
        if((duration + month) % 12 == 0){
            convertDate[1] = 12;
        }else{
            convertDate[1] = (duration + month) % 12;
        }
        //일을 어떻게 변환
        //수집 일 - 1, 만약 1일 이라면 달을 1빼고 28로
        if(day == 1){
            convertDate[1] -= 1;
            if(convertDate[1] == 0){
              convertDate[0]--; 
              convertDate[1] = 12;  
            } 
            convertDate[2] = 28;
        }else{
            convertDate[2] = day-1;
        }
        
        return convertDate;
    }
        
}
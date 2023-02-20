import java.util.*;

class Solution {
    public ArrayList<Integer> solution(int[] fees, String[] records) {
        
        ArrayList<Integer> answer = new ArrayList();
        
        HashMap<String, Integer> stackFee = new HashMap<>();
        HashMap<String, String> inOutTime = new HashMap<>();
               
        for(String record : records){
            String[] info = record.split(" ");
            
            String carNum = info[1];
            String time = info[0];
            
            String inOutRecord = inOutTime.get(carNum);
            
            if(inOutRecord==null){
                inOutTime.put(carNum, time);
            }else{
                int calTime = calParkingTime(inOutRecord, time);
                stackFee.put(carNum, stackFee.getOrDefault(carNum,0) + calTime);
                inOutTime.remove(carNum);  // 입출차 1set 계산 후 제거 
            }            
        }
        
        for(String carNum : inOutTime.keySet()){
            String remainTime = inOutTime.get(carNum);
            int calTime = calParkingTime(remainTime, "23:59");
            stackFee.put(carNum, stackFee.getOrDefault(carNum,0) + calTime);
        } //출차 기록이 없으면 23:59로 해서 누적시간 추가
    
        ArrayList<String> arrList = new ArrayList<>(stackFee.keySet());
        Collections.sort(arrList);
        for(String carNum: arrList){
            int fee = calParkingFee(fees, stackFee.get(carNum));
            answer.add(fee);            
        }
 
        return answer;
    }
    
    public int calParkingTime(String inTime, String outTime){
        
        System.out.println("inTime: "+ inTime);
        System.out.println("outTime: " + outTime);
        String[] inHourMinute = inTime.split(":"); 
        String[] outHourMinute = outTime.split(":");
        
        int time1 = (Integer.parseInt(outHourMinute[0]) 
            - Integer.parseInt(inHourMinute[0])) * 60;
        int time2 = Integer.parseInt(outHourMinute[1]) 
            - Integer.parseInt(inHourMinute[1]);
        
        return time1 + time2;
    }
    
    public int calParkingFee(int[] fees, int time){
        
        int basicTime = fees[0];
        int basicFee = fees[1];
        int unitTime = fees[2];
        int unitFee = fees[3];
        if(time <= basicTime){
            return basicFee;
        }else{
            System.out.println(time);
            return basicFee + (int)(Math.ceil( (time - basicTime)*1.0 / unitTime ) * unitFee);
        }
    }
}
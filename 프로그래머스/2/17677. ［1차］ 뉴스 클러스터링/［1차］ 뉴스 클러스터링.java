import java.util.*;

class Solution {

    public int solution(String str1, String str2) {
        int answer = 0;
        
        //문자열 파싱
                
        // 조건
        // 1) A, B 모두 공집합일 경우 1
        // 2) 문자열 길이 2~1000
        // 3) 파싱할 때 영문 글자가 아닌 것은 모두 버린다
        // 4) 대문자와 소문자는 같은 것으로 취급한다
        // 5) 문자열을 2글자로 끊되, 중간 글자는 겹친다
        
        // 교집합의 원소의 갯수 / 합집합의 원소의 갯수를 리턴
        
        //STR1 해시맵,  STR2 해시맵
        
        //각 파싱한 문자열을 키로(모두 대문자로 바꿔 파싱), 들어있는 개수를 벨류로
        
        HashMap<String, Integer> parse1 = parse(str1);
        HashMap<String, Integer> parse2 = parse(str2);
        
        if(parse1.size() == 0 && parse2.size() == 0){
            return 65536;
        }
        
        //키의 갯수가 다를 수 있다. 
        //한 쪽 키를 돌면서 다른 쪽에도 있는 문자열인지 확인
        //있으면 갯수를 확인
        //교집합의 갯수는 무조건 양 쪽 중 MIN 값으로 한다
        //합집합의 갯수는 양쪽 중 MAX로 한다
        //같은 문자열이 없으면 위랑 같다
        //HASHMAP에서 꺼냈던 거는 삭제해버린다.
        //그리고 STR2에서 남은 KEY에 대해서 위 연산을 동일하게 진행.
        
        float inter = 0;
        float union = 0;
        for(String key: parse1.keySet()){
            
            if(parse2.get(key)!=null){
                union += convert(0, parse1.get(key), parse2.get(key));
                inter += convert(1, parse1.get(key), parse2.get(key));
                parse2.remove(key);
            }else{
                union += parse1.get(key);                
            }
        }
        
        for(String key: parse2.keySet()){
            
            union += parse2.get(key);
            
        }
        
        answer = (int)(inter / union * 65536);
        return answer;
    }
    
    public static HashMap<String, Integer> parse(String str){
        
        HashMap<String, Integer> map = new HashMap<>();
        
        for(int i=0; i<str.length()-1; i++){
            char first = str.charAt(i);
            char second = str.charAt(i+1);

            if(!Character.isLetter(first) || !Character.isLetter(second)) continue;
             
            String key = Character.toUpperCase(first) +""+Character.toUpperCase(second);
            ;
            
            if(map.get(key)==null){
                map.put(key,0);
            }
            map.put(key,map.get(key)+1);
        }

        return map;
    }
    
    public static int convert(int type, int str1, int str2){
        //합집합 연산
        if(type==0){
            return Math.max(str1, str2);
        //교집합 연산
        }else{
            return Math.min(str1, str2);
        }
    }
}
import java.util.*;

class Solution {
    
    public int[] solution(String[][] places) {
        int[] answer = new int[5];        
        for(int i=0; i<5; i++){
            char[][] room = new char[5][5];
            for(int j=0; j<5; j++){
                for(int k=0; k<5; k++){
                    room[j][k] = places[i][j].charAt(k);                    
                }                
            }            
            int result = isPass(room);
            answer[i] = result;
        }                
        return answer;
    }

    public int isPass(char[][] room){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(room[i][j] == 'P'){
                    boolean chk = check(i,j,room);
                    if(!chk) return 0;
                }
            }
        }        
        return 1;
    }
    
    public boolean check(int r, int c, char[][] room){
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                if(i==r && i==c) continue;                
                int distance = calDistance(r,c,i,j);                    
                if(distance == 1){
                    if(room[i][j] == 'P') return false;
                }else if(distance == 2){
                    if(room[i][j] == 'P') {                        
                        if(r!=i && c!=j){
                            if(room[r][j] != 'X' || room[i][c] != 'X'){
                                return false;
                            }
                        }else if(r==i && c!=j){
                            if(c<j){
                                if(room[r][c+1] != 'X') return false;
                            }else{
                                if(room[r][c-1] != 'X') return false;
                            }
                        }else if(r!=i && c==j){
                            if(r<i){
                                if(room[r+1][c] != 'X') return false;
                            }else{
                                if(room[r-1][c] != 'X') return false;
                            }
                        }
                    }    
                }
            }                
        }
        return true;
    }        
    
    public int calDistance(int r1, int c1, int r2, int c2) {
        return Math.abs(r1-r2) + Math.abs(c1-c2);
    }
    
}
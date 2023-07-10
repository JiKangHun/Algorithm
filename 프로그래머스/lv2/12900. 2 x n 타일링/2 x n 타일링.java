import java.util.*;

class Solution {
    public int solution(int n) {
        int[] arr = new int[60001];
        arr[1] = 1;
        arr[2] = 2;
        for(int i=3; i<=n; i++){
            arr[i] = (arr[i-1]+arr[i-2]) % 1000000007;
        }
        return arr[n];
    }
}
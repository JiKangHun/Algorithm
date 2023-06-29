import java.util.*;

class Solution {
    public int solution(int distance, int[] rocks, int n) {
        Arrays.sort(rocks); // 바위들을 오름차순으로 정렬
        int answer = 0;
        int left = 0; // 이분 탐색의 왼쪽 범위
        int right = distance; // 이분 탐색의 오른쪽 범위
        
        while (left <= right) {
            int mid = (left + right) / 2; // 이분 탐색을 위한 중간 값
            int removedRocks = 0; // 제거한 바위의 개수
            int prevRock = 0; // 이전 바위의 위치
           
            for (int i = 0; i < rocks.length; i++) {
                // 현재 바위와 이전 바위 사이의 거리가 mid보다 작다면 바위 제거
                if (rocks[i] - prevRock < mid) {
                    removedRocks++;
                } else {
                    // 현재 바위를 이전 바위로 갱신
                    prevRock = rocks[i];
                }
            }
            
            // 마지막 바위부터 도착지점 사이의 거리 확인
            if (distance - prevRock < mid) {
                removedRocks++;
            }
            
            // 제거한 바위의 개수가 n보다 크면 mid 값을 감소시켜서 더 작은 거리로 확인
            if (removedRocks > n) {
                right = mid - 1;
            } else {
                // 제거한 바위의 개수가 n보다 작거나 같으면 mid 값을 증가시켜서 더 큰 거리로 확인
                left = mid + 1;
                answer = mid; // 현재까지의 최대 거리 갱신
            }
        }
       
        return answer;
    }
}

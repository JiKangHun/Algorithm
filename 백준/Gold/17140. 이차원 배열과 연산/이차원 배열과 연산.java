import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] arr;
	
	static class cntInfo implements Comparable<cntInfo>{
		int index;
		int count;
		cntInfo(int index, int count){
			this.index=index;
			this.count=count;
		}
		@Override
		public int compareTo(cntInfo o) {
			int a = this.count;
			int b = o.count;
			if(a!=b) {
				return Integer.compare(a, b);
			}else {
				return Integer.compare(this.index, o.index);
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());
		arr = new int[101][101];
		
		for(int i=1; i<=3; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=3; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if(arr[r][c] == k) {
			System.out.println(0);
			return;
		}else {
			int time = 1;
			while(time<=100) {
				if(cnt(r, c, k)) {
					System.out.println(time);
					return;
				}
				time++;
			}
			System.out.println(-1);
		}
	}

	private static boolean cnt(int r, int c, int k) {
		
		int rowMax = 0;
		int colMax = 0;
		for(int i=1; i<101; i++) {
			for(int j=1; j<101; j++) {
				if(arr[i][j]!=0 && rowMax<i) rowMax = i;
				if(arr[i][j]!=0 && colMax<j) colMax = j;
			}
		}//행-열 길이 비교 위한 연산
		
		int[][] arrCnt = new int[101][101];
		if(rowMax>=colMax) {
			for(int i=1; i<=rowMax; i++) {
				for(int j=1; j<=colMax; j++) {
					int num = arr[i][j];
					arrCnt[i][num]++;
				}
			}
			return Rsort(arrCnt, rowMax, colMax, r, c, k);
		}else {
			for(int i=1; i<=colMax; i++) {
				for(int j=1; j<=rowMax; j++) {
					int num = arr[j][i];
					arrCnt[num][i]++;
				}
			}
			return Csort(arrCnt, rowMax, colMax, r, c, k);
		}
	}

	private static boolean Csort(int[][] arrCnt, int rowMax, int colMax, int r, int c, int k) {
		
		int[][] arrTmp = new int[101][101];
		for(int i=1; i<=100; i++) {
			PriorityQueue<cntInfo> pq = new PriorityQueue<>();
			for(int j=1; j<=100; j++) {
				if(arrCnt[j][i]!=0) {
					pq.offer(new cntInfo(j,arrCnt[j][i]));
				}
			}
			int size = pq.size();
			for(int m=1; m<=size*2; m+=2) {
				if(m>100)break;
				cntInfo tmp = pq.poll();
				arrTmp[m][i] = tmp.index;
				arrTmp[m+1][i] = tmp.count;
			}
		}
		
		arr = arrTmp;
		if(arr[r][c] ==k) {
			return true;
		}else {
			return false;
		}
	}

	private static boolean Rsort(int[][] arrCnt, int rowMax, int colMax, int r, int c, int k) {
		
		int[][] arrTmp = new int[101][101];
		for(int i=1; i<=100; i++) {
			PriorityQueue<cntInfo> pq = new PriorityQueue<>();
			for(int j=1; j<=100; j++) {
				if(arrCnt[i][j]!=0) {
					pq.offer(new cntInfo(j,arrCnt[i][j]));
				}
			}
			int size = pq.size();
			for(int m=1; m<=size*2; m+=2) {
				if(m>100)break;
				cntInfo tmp = pq.poll();
				arrTmp[i][m] = tmp.index;
				arrTmp[i][m+1] = tmp.count;
			}
		}

		arr = arrTmp;
		
		if(arr[r][c] ==k) {
			return true;
		}else {
			return false;
		}
	}
}

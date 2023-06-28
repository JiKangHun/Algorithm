import java.awt.Point;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {
	
	static int N, M, D;
	static int[][] map;
	static int[] arrow;
	static int[] selected;
	static ArrayList<int[]> combiList = new ArrayList<>();
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		D = sc.nextInt();
		map = new int[N][M];
		arrow = new int[M];
		selected = new int[3];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		for(int i=0; i<M; i++) {
			arrow[i] = i;
		}
		
		combi(0,0); 
		
		int result = 0;
		
		for(int[] archers : combiList) { 
			
			int[][] tmpMap = new int[N][M];
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					tmpMap[i][j] = map[i][j];
				}
			}
			
			int cnt = 0;
			int turn = N;
			for(int i=0; i<N; i++) {
				ArrayList<Point> pList = new ArrayList<>();
				for(int loc : archers) { 
					Point p = findMin(loc, turn, tmpMap); 
					if(p!=null) {
						pList.add(p);
					}
				}
				
				for(Point p : pList) {
					if (tmpMap[p.y][p.x] == 1) {
						tmpMap[p.y][p.x] = 0; 
						cnt++;
					}
				}
				turn--; 
			}
			result = Math.max(result, cnt);		
		}
		System.out.println(result);
	}
	
	private static Point findMin(int loc, int turn, int[][] tmpMap) {
		Point p = null;
		int tmpDis = Integer.MAX_VALUE;
		for(int i=0; i<M; i++) {
			for(int j=0; j<turn; j++) {
				if(tmpMap[j][i]==1) {
					int dis = calDistance(j,i,turn,loc);
					if(dis<=D && dis < tmpDis) {
						tmpDis = dis;
						p = new Point(i,j);
					}
				}
			}
		}
		return p;
	}

	private static int calDistance(int j, int i, int turn, int loc) {
		return Math.abs(j-turn) + Math.abs(i-loc);
	}

	private static void combi(int cnt, int start) {
		
		if(cnt==3) {
			combiList.add(Arrays.copyOf(selected, 3));
			return;
		}
		
		for(int i=start; i<arrow.length; i++) {
			selected[cnt] = arrow[i];
			combi(cnt+1, i+1);
		}
	}
}

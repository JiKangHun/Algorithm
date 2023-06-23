import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	
	static int[][] originE, energyPlus;
	static PriorityQueue<Tree>[][] treeMap;
	static int N,M,K;
	
	static class Tree implements Comparable<Tree>{
		int age;
		
		public Tree(int age) {
			this.age = age;
		}

		@Override
		public int compareTo(Tree o) {
			return Integer.compare(this.age, o.age);
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		treeMap = new PriorityQueue[N+1][N+1];
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				treeMap[i][j] = new PriorityQueue<Tree>();
			}
		}
		originE = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			Arrays.fill(originE[i], 5);
		}
		
		energyPlus = new int[N+1][N+1];
		
		for(int i=1; i<=N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for(int j=1; j<=N; j++) {
				energyPlus[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int age = Integer.parseInt(st.nextToken());
			treeMap[r][c].add(new Tree(age));
		}
		//입력 완료
		
		while(K>0) {
			springSummer();
			fall();
			winter();
			K--;
		}
		
		int ans = 0;
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				ans += treeMap[i][j].size();
			}
		}
		System.out.println(ans);
	}

	private static void springSummer() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				PriorityQueue<Tree> pq = treeMap[i][j];
				PriorityQueue<Tree> newList = new PriorityQueue<>();
				int plusEnergy = 0;
				while(!pq.isEmpty()) {
					Tree tree = pq.poll();
					if(tree.age<=originE[i][j]) {
						originE[i][j] -= tree.age;
						tree.age += 1;
						newList.offer(tree);
					}else {
						plusEnergy += tree.age/2;
					}
				}
				
				treeMap[i][j] = newList;
				//여름 part
				originE[i][j] += plusEnergy;
			}
		}
	}
	
	static int[] dy = {-1,-1,-1,0,0,1,1,1};
	static int[] dx = {-1,0,1,-1,1,-1,0,1};
			
	
	private static void fall() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				PriorityQueue<Tree> pq = treeMap[i][j];
				PriorityQueue<Tree> newList = new PriorityQueue<>();
				while(!pq.isEmpty()) {
					Tree tree = pq.poll();
					if(tree.age%5 == 0) {
						int y = i;
						int x = j;
						for(int d=0; d<8; d++) {
							int ny = y + dy[d];
							int nx = x + dx[d];
							if(!check(ny,nx)) continue;
							treeMap[ny][nx].offer(new Tree(1));
						}
					}
					newList.offer(tree);
				}
				treeMap[i][j] = newList;
			}
		}
	}
	
	private static void winter() {
		for(int i=1; i<=N; i++) {
			for(int j=1; j<=N; j++) {
				originE[i][j] += energyPlus[i][j];
			}
		}
	}

	private static boolean check(int ny, int nx) {
		return ny >= 1 && ny <=N && nx >= 1 && nx <=N;
	}
	
}

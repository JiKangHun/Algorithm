import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		int N = Integer.parseInt(st.nextToken());
		int atk = Integer.parseInt(st.nextToken());
		
		int[][] room = new int[N][3];
		
		long left = 0;
		long right = 0;
		for(int i=0; i<N; i++) {
			
			st = new StringTokenizer(br.readLine()," ");
			int t = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int h = Integer.parseInt(st.nextToken());
			room[i][0] = t;
			room[i][1] = a;
			room[i][2] = h;
			if(t==1) {
				right += (long)a*h;
			}
		}
		
		long answer = right;
		while(left+1 < right) {
			
			long maxHP = (left + right) / 2;			
			long curHP = maxHP;
			long curATK = atk;
			
			for(int i=0; i<N; i++) {
				
				int type = room[i][0];
				int attack = room[i][1];
				int hp = room[i][2];
				// 몬스터인 경우
				if(type == 1) {
					
					long result = battle( curHP, curATK, attack, hp );
					// 패배한 경우
					if(result == 0) {
						left = maxHP;
						break;
					// 승리한 경우
					}else {
						curHP = result;
						if(i==N-1) {
							answer = maxHP;
							right = maxHP;
						}
					}
				// 포션인 경우
				}else {
					curATK += attack;
					curHP = curHP + hp > maxHP ? maxHP : curHP + hp;  
				}
			}
		}
		
		System.out.println(answer);
		
	}

	private static long battle(long soldierHP, long soldierATK, long monATK, long monHP) {
		
		long isWin = 0;
		
		int soldierCnt = (int) Math.ceil((double)monHP / soldierATK);
		int monCnt = (int) Math.ceil((double)soldierHP / monATK);
		isWin = soldierCnt <= monCnt ? soldierHP - (soldierCnt - 1) * monATK : 0;
		
		return isWin;
	}
}


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	static class Sector {
		boolean hasRobot;
		int durability;
	}

	public static void main(String[] args) throws IOException {
		ArrayList<Sector> beltUp = new ArrayList<>();
		ArrayList<Sector> beltDown = new ArrayList<>();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine(), " ");
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			Sector sector = new Sector();
			sector.durability = Integer.parseInt(st.nextToken());
			beltUp.add(sector);
		}

		for (int i = 0; i < N; i++) {
			Sector sector = new Sector();
			sector.durability = Integer.parseInt(st.nextToken());
			beltDown.add(0, sector);
		}
		
		int step = 0; // 단계
		int cnt = 0; // 내구성 0인 섹터의 개수
		while (cnt < K) {

			step++;
			rotate(beltUp, beltDown, N);
			cnt = move(beltUp, N, cnt);
			cnt = load(beltUp.get(0), cnt);
		}
		System.out.println(step);
	}
	
	private static void rotate(ArrayList<Sector> beltUp, ArrayList<Sector> beltDown, int N) {

		// 회전: up의 N-1번째 빼서 down에 add, down의 0번째 빼서 up 0번째에 넣기
		Sector tmpUp = beltUp.remove(N-1);
		beltDown.add(tmpUp);
		Sector tmpDown = beltDown.remove(0);
		beltUp.add(0, tmpDown);
		if(beltUp.get(N-1).hasRobot) {
			beltUp.get(N-1).hasRobot = false;
		}

	}

	private static int move(ArrayList<Sector> beltUp, int N, int cnt) {
		// upbelt의 뒤에서부터 순회하면서 로봇이 있다면 다음 칸에 로봇이 없는지, 내구성이 1 이상인지 확인하고 맞으면 옮김,
		// 옮긴 위치가 내리는 위치면 제거함, 내구성이 0이 됐다면 cnt++해줌
		for (int i = N - 2; i >= 0; i--) {
			Sector cur = beltUp.get(i);
			Sector next = beltUp.get(i + 1);
			if (cur.hasRobot && !next.hasRobot && next.durability > 0) {

				if (i != N - 2) {
					next.hasRobot = true;
				}

				cur.hasRobot = false;
				next.durability--;

				if (next.durability == 0) {
					cnt++;
				}
			}
		}
		return cnt;
	}
	
	private static int load(Sector first, int cnt) {
		// upbelt의 0번 인덱스의 내구도가 0이 아니면 로봇을 하나 더 올림 : 내구성이 1이 됐다면 cnt++해줌
		if(first.durability > 0) {
			first.hasRobot = true;
			first.durability--;
			if(first.durability==0) cnt++;
		}
		return cnt;
	}
}

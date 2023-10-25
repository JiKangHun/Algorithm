import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,-1,0,1};
    static char[][] map;
    static int[][] fireMap;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int t=0; t<T; t++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            int w = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            map = new char[h][w];
            fireMap = new int[h][w];

            int[] start = new int[3];

            ArrayList<int[]> fireList = new ArrayList<>();

            for(int i=0; i<h; i++){
                char[] row = br.readLine().toCharArray();
                for(int j=0; j<w; j++){
                    map[i][j] = row[j];
                    if(map[i][j] == '@'){
                        start[0] = i; start[1] = j; start[2] = 1;
                    }
                    if(map[i][j] == '*'){
                        int[] fire = new int[3];
                        fire[0] = i; fire[1] = j; fire[2] = 1;
                        fireList.add(fire);
                    }
                    if(map[i][j] == '#'){
                        fireMap[i][j] = -1;
                    }
                }
            }

            spreadFire(fireList, h ,w);

//            System.out.println("fireMap after fire Spread");
//            for(int i=0; i<h; i++){
//                for(int j=0; j<w; j++){
//                    System.out.print(fireMap[i][j]+" ");
//                }
//                System.out.println();
//            }

            int result = exit(start, h, w);

            if(result == -1){
                System.out.println("IMPOSSIBLE");
            }else{
                System.out.println(result-1);
            }
        }
    }

    private static int exit(int[] start, int h, int w) {

        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[h][w];
        visited[start[0]][start[1]] = true;
        q.add(start);

        while(!q.isEmpty()){

            int qSize = q.size();
            for(int i=0; i<qSize; i++){
                int[] cur = q.poll();
                int curR = cur[0];
                int curC = cur[1];
                int depth = cur[2];

                for(int d=0; d<4; d++){

                    int nr = curR + dr[d];
                    int nc = curC + dc[d];

                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) return depth+1 ;
                    if(visited[nr][nc]) continue;
                    if(fireMap[nr][nc] <= depth+1 && fireMap[nr][nc] != 0 ) continue;
//                    System.out.println("nr :"+ nr +" "+"nc :"+nc);
//                    System.out.println("depth :" + depth);
                    visited[nr][nc] = true;

//                    for(int k=0; k<h; k++){
//                        for(int t=0; t<w; t++){
//                            System.out.print(visited[k][t]+" ");
//                        }
//                        System.out.println();
//                    }
                    q.add(new int[] {nr,nc, depth+1});
                }
            }
        }

        return -1;
    }

    private static void spreadFire(ArrayList<int[]> fireList, int h, int w) {

        Queue<int[]> q = new LinkedList<>();

        for(int[] fire: fireList){
            fireMap[fire[0]][fire[1]] = 1;
            q.add(fire);
        }

        while(!q.isEmpty()){

            int qSize = q.size();

            for(int i=0; i<qSize; i++){
                int[] cur = q.poll();
                int curR = cur[0];
                int curC = cur[1];
                int depth = cur[2];
                for(int d=0; d<4; d++){
                    int nr = curR + dr[d];
                    int nc = curC + dc[d];

                    if(nr < 0 || nr >= h || nc < 0 || nc >= w) continue;
                    if(map[nr][nc] == '#') continue;
                    if(map[nr][nc] == '*') continue;

                    if(fireMap[nr][nc] > 0) continue;
                    fireMap[nr][nc] = depth + 1;
                    q.add(new int[] {nr,nc, depth +1});

//                    System.out.println("fireMap onGoing");
//                    for(int k=0; k<h; k++){
//                        for(int t=0; t<w; t++){
//                            System.out.print(fireMap[k][t]+" ");
//                        }
//                        System.out.println();
//                    }
//                    System.out.println("--------------------------------");
                }

            }
        }
    }
}

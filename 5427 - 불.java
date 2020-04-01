import java.util.*;
import java.io.*;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class solution2 {
	static int[] dx = { -1, 1, 0, 0 };
	static int[] dy = { 0, 0, -1, 1 };
	static int w, h;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());

			String[][] str = new String[h][w];
			int[][] dist = new int[h][w];
			int[][] time = new int[h][w];
			Queue<Pair> q = new LinkedList<Pair>(); // 상근이의 위치
			Queue<Pair> fire = new LinkedList<Pair>();
			boolean[][] check = new boolean[h][w];
			boolean[][] visited = new boolean[h][w];

			// 판을 입력 받는다
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				str[i] = st.nextToken().split("");
			}

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					dist[i][j] = 0;
					time[i][j] = 0;
				}
			}

			// 초기 셋팅
			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (str[i][j].equals("@")) {
						q.add(new Pair(i, j));// 상근이의 시작위치를 받는다
					}
					if (str[i][j].equals("#")) {
						check[i][j] = true;
						visited[i][j] = true;
					}
					if (str[i][j].equals("*")) {
						fire.add(new Pair(i, j));
					}
				}
			}

			moving_fire(fire, str, check, dist);
			int ans = moving(q,str, dist, time, visited);
			
			if(ans >=0 )// 탈출구를 찾았을 때
				 System.out.println(ans+1);
			 else if(ans == -1)
				 System.out.println("IMPOSSIBLE");
	
		}
	}

	public static void moving_fire(Queue<Pair> fire, String[][] str, boolean[][] check, int[][] dist) {
		while (!fire.isEmpty()) {
			Pair p = fire.poll();
			int x = p.x;
			int y = p.y;
			 check[x][y] = true;
			for (int k = 0; k < 4; k++) {//System.out.println(x + " " + y);
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
					if (check[nx][ny] == false && dist[nx][ny] == 0 && !str[nx][ny].equals("*")) {
						check[nx][ny] = true;
						dist[nx][ny] = dist[x][y] + 1;
						fire.add(new Pair(nx, ny));
					}
				}
			}

		}
	}

	public static int moving(Queue<Pair> q, String[][] str, int[][] dist, int[][] time, boolean[][] visited) {
		while (!q.isEmpty()) {
			Pair p = q.poll(); 
			int x = p.x;
			int y = p.y;
			if(x == 0 || x == h-1 || y == 0 || y == w-1) {
				return time[x][y];
			}
			visited[x][y] = true;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && ny >= 0 && nx < h && ny < w) {
					if (visited[nx][ny] == false && dist[nx][ny] > time[x][y]+1) {
						visited[nx][ny] = true;
						time[nx][ny] = time[x][y] +1;
						q.add(new Pair(nx, ny));
					}else if( visited[nx][ny] == false && dist[nx][ny] == 0) {
						visited[nx][ny] = true;
						time[nx][ny] = time[x][y] +1;
						q.add(new Pair(nx, ny));
					}

				}
			}
		}
		return -1;
	}
}

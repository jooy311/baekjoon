import java.util.*;
import java.io.*;

/*
 * https://www.acmicpc.net/problem/17141
 */

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class solution {
	// 방향 설정(상하좌우)
	static final int dx[] = { 0, 0, -1, 1 };
	static final int dy[] = { -1, 1, 0, 0 };
	static int n;
	static int m;
	static int ans;
	static int ans2 = 100000;
	static int[][] a;
	// static boolean[][] check;
	// static int[][] time;
	static Queue<Pair> q;
	static ArrayList<Pair> arr;
	static boolean[] virus;

	// 바이러스 m개 설정
	public static void bfs() {// 바이러스가 한곳이아닌 m개에서 동시다발적으로 한번에 시작
		ans = 0;
		int[][] time = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				time[i][j] = -1;
			}
		}

		for (int i = 0; i < virus.length; i++) {
			if (virus[i] == true) {
				q.add(arr.get(i));
				time[arr.get(i).x][arr.get(i).y] = 0;
			}
		}
		while (!q.isEmpty()) {
			Pair p = q.poll(); // 바이러스 위치 하나 꺼내서 확인
			for (int k = 0; k < 4; k++) {
				// 상하좌우 움직일 다음 위치 정해주기
				int nx = p.x + dx[k];
				int ny = p.y + dy[k];
				if (nx >= 0 && nx < n && ny >= 0 && ny < n) {
					// 벽이없는 칸이고, 들린적이 없는 칸이면 진행
					if (a[nx][ny] != 1 && time[nx][ny] == -1) {// 벽인 아닌 칸이고&&바이러스위치가 아닌곳(그리고 들린적이 없는 칸->들렸다면 0 이상일것.)
						q.add(new Pair(nx, ny));// 이동한 칸이 다시 또, 시작점이 되므로
						time[nx][ny] = time[p.x][p.y] + 1; // 한칸 진행된거니까 1초가 늘어남
						// times++;
					}
				}
			}
		} // while끝
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (ans < time[i][j]) { // 현재 계산한 총 시간보다 그 칸에 있는 (걸린)시간이 더 크다면 ans값 갱신
					ans = time[i][j];
				}
				System.out.print(time[i][j] + " ");
			}
			System.out.println("\n");
		}
		System.out.println("\n");
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (a[i][j] != 1 && time[i][j] == -1) {
					ans = 100000; // 모든 위의 과정을 거쳤는데도 못가는 칸이 있다면 -1을 반환하도록 함 (즉, 예외의 상황)
				}
			}
		}
		
		if (ans2 > ans)
			ans2 = ans;
	}

	public static void dfs(int start, int cnt) {
		if (cnt == m) {// m개 골라졌다면
			bfs();// 바로 bfs돌리기
			return;
		} else {
			for (int i = start; i < arr.size(); i++) {
				if (virus[i] == true)
					continue;
				virus[i] = true;// true이면 방문을 안함
				dfs(i + 1, cnt + 1);
				// q.poll();
				virus[i] = false;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // 사각형길이
		m = Integer.parseInt(st.nextToken());// 바이러스 개수

		// time = new int[n][n]; // 각 칸마다 바이러스가 도착하는 시간을 저장
		q = new LinkedList<Pair>();// 바이러스가 들어갈수있는 위치를 넣기위한 큐
		arr = new ArrayList<>();
		a = new int[n][n]; // 연구실 판 받기

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
				// time[i][j] = -1; // 일단 다 -1초로 셋팅
				if (a[i][j] == 2) {
					arr.add(new Pair(i, j));
					// time[i][j] = 0; // 바이러스를 넣을 수 있는 칸은 시작공간이 될 수 있으므로 0으로 시간을 셋팅
				}
			}
		}
		virus = new boolean[arr.size()];
		//ans = 0;// 최소시간
		dfs(0, 0);
		if(ans2 == 100000)
			ans2 = -1;
		System.out.println(ans2);

	}
}

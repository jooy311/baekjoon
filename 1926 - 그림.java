import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/1926

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static final int[] dx = { 0, 0, 1, -1 };
	public static final int[] dy = { 1, -1, 0, 0 };// 헐..이거 final변수에 순서까지 중요했어...?ㅅㅂ
	static int area;// 넓이를 계산
	static int cnt;// 그림의 개수를 계산
	static int n;// 세로
	static int m;// 가로
	static boolean[][] check;// 방문했는지 안했는지 탐색을 위한 check함수
	static int[][] a;

	public static void bfs(int x, int y, Queue<Pair> q) {
		q.add(new Pair(x, y)); // 받아온 현재의 위치를 큐에 저장
		while (!q.isEmpty()) {
			Pair p = q.remove();
			p.x = x;
			p.y = y;
			for (int i = 0; i < 4; i++) {// 4개의 방향이 돌동안
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (a[nx][ny] == 1 && check[nx][ny] == false) { // 그림이 그려져 있고, 들린적이 없는 곳이라면
						q.add(new Pair(nx, ny));
						check[nx][ny] = true;
						area++;// 세로1가로2일때 1 0 그림일때 0,0에 그림이있고 다음에 그림이 없다는 이유로 area가 카운트가 안됨
						bfs(nx, ny, q);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 그림판 세로
		m = Integer.parseInt(st.nextToken());// 그림판 가로
		a = new int[n][m];
		check = new boolean[n][m];
		Queue<Pair> q = new LinkedList<Pair>();
		ArrayList<Integer> arr = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				if (a[i][j] == 1 && check[i][j] == false) {
					area = 1;// 한개의 그림이 끝나고 다음차례의 그림의 넓이를 계산하려면 다시 1로 리셋
					bfs(i, j, q);
					cnt++; // 재귀가 다 끝나면 그림 한개가 끝났다는 것이므로 cnt값을 하나 올려준다
					if (area > 1)
						area = area - 1; //1이상의 area들은 주변 탐색을 하면서 1씩 값이 더 커지는 것을 방지하기 
					arr.add(area);
				}
			}
		}
		for (int i = 0; i < arr.size(); i++) {
			System.out.println(arr.get(i));
		}
		System.out.println(cnt);// 얘다시
		if (!arr.isEmpty())
			System.out.println(Collections.max(arr));// area
		else
			System.out.println("0");
	}
}

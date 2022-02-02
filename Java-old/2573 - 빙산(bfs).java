package exam;

import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/2573
/* 1. 빙산 입력받는다
 * 2. 숫자 주변에 0이 몇개인지 찾는다
 * 3. 0의 갯수만큼 숫자를 차감한다 * n번?? -> 덩이가 나눠질때까지 해야하니까 -> 그럼 한번하고 검사, 한번하고 검사?
 * ---#덩어리가 나눠지는지 아닌지를 중간에 계속 확인하는 코드를 넣어주어야 하나?
 * 4. 두덩이 이상으로 나눠질때의 년수를 구한다
 * 	4-1. 두덩이로 나뉘는지 어떻게 아냐? -> 방향을 옮겨서 가봤는데 아직도 false인 곳이 남아있다. -> 두덩이 이상
 * 	4-2. visited함수나 visited함수를 만들어서  방문체크를 해야함*/

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class solution3 {
	static int[][] zero;
	static int year;
	static boolean[][] visited;
	static int[][] a;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 행
		int m = Integer.parseInt(st.nextToken());// 열
		a = new int[n][m];
		Queue<Pair> q = new LinkedList<Pair>();

		// 1.빙산입력받음
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < m; j++) {
				a[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// q.add(new Pair(0, 0));// 처음 시작 위치
		/*
		 * for (int i = 0; i < n; i++) { for (int j = 0; j < m; j++) { if (a[i][j] != 0)
		 * { Pair ps = new Pair(i, j); q.add(ps); } if (q.size() == 1) break; } }
		 */

		solution(n, m, a, q);
	}

	public static void melting(Queue<Pair> q, int n, int m, int x1, int y1) {
		zero = new int[n][m];
		int cnt;
		q.add(new Pair(x1, y1));
		visited[x1][y1] = true;

		while (!q.isEmpty()) {
			cnt = 0;
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;
			for (int k = 0; k < 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if (nx >= 0 && nx < n && ny >= 0 && ny < m) {
					if (a[nx][ny] == 0) {
						cnt++;
						zero[x][y] = cnt;
					}
					
					if (a[nx][ny] != 0 && visited[nx][ny] == false) {
						visited[nx][ny] = true;
						q.add(new Pair(nx, ny));
					}
				}
			}

		} // while문끝
	}

	public static void solution(int n, int m, int[][] a, Queue<Pair> q) {
		int year = 0;
		while (true) {
			int lump = 0;
			visited = new boolean[n][m];
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					if (a[i][j] != 0 && visited[i][j] == false) {
						lump += 1;
						melting(q, n, m, i, j);// 한번 돌고오면 다 true일거니까 결국 2중 for문 빠져 나간다
					}
				}
			}
			
			if (lump >= 2) {
				System.out.println(year);
				break;
			}

			if (lump == 0) {
				System.out.println("0");
				break;
			}

			for (int i = 0; i < n; i++) {
				for (int j = 0; j < m; j++) {
					a[i][j] = a[i][j] - zero[i][j];

					if (a[i][j] < 0)
						a[i][j] = 0;
				}
			}
			year++;
		}
	}
}

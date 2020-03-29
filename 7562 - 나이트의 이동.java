package exam;

import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/7562

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class solution2 {
	// 방향 설정
	static int[] dx = { 1, 2, 2, 1, -1, -2, -2, -1 };
	static int[] dy = { 2, 1, -1, -2, -2, -1, 1, 2 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int t = Integer.parseInt(st.nextToken());// 테스트케이스

		while (t-- > 0) {
			Queue<Pair> s = new LinkedList<Pair>();

			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// 체스판의 크기를 받음

			int[][] count = new int[n][n];
			boolean[][] check = new boolean[n][n];

			// 현재 위치 받기
			st = new StringTokenizer(br.readLine());
			Pair start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			s.add(new Pair(start.x, start.y));

			// 목표위치 받기
			st = new StringTokenizer(br.readLine());
			Pair goal = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			move(n, check, s, goal, start, count);
		}
	}

	public static void move(int n, boolean[][] check, Queue<Pair> s, Pair goal, Pair start, int[][] count) {
		check[start.x][start.y] = true;
		
		while (!s.isEmpty()) {
			Pair now = s.poll();
			int x = now.x;
			int y = now.y;
	
			int cnt = count[x][y];//(처음을제외하고) 현재위치까지 몇번에 거쳐 이동했는지 cnt에 저장되어있음

			if (x == goal.x && y == goal.y) {// 탈출조건
				System.out.println(cnt);
			}

			for (int k = 0; k < 8; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
        
				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (check[nx][ny] == false) {
						check[nx][ny] = true;// 방문했으니까 바꿔주고
						count[nx][ny]++;//일단 이동한 위치 방법수 +1
						count[nx][ny] = cnt + count[nx][ny];// 전에 이동한 횟수 + 현재 한번 움직인 횟수로 갱신
						s.add(new Pair(nx, ny));
					}
				}
			}
		}
	}
}

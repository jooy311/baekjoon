package exam;

import java.util.*;
import java.lang.*;
import java.io.*;
//같은 칸에 말이 두개이상 있으면 어떻게 해야하는데?

class solution2 {

	static class Pair {// 말의 정보
		int x;
		int y;
		int dir;

		public Pair(int x, int y, int dir) {
			// this.mal = mal;
			this.x = x;
			this.y = y;
			this.dir = dir;
		}
	}

	static int[][] map;
	static Stack<Integer>[][] q;
	static int n;
	static int k;
	// static int[] dir = { 1, 2, 3, 4 };// 우,좌,상,하
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int[] dy = { 0, 1, -1, 0, 0 };// 남북좌우

	public static void main(String[] args) throws java.lang.Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 체스판 크기
		k = Integer.parseInt(st.nextToken());// 말의개수

		map = new int[n + 1][n + 1];
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		q = (Stack<Integer>[][]) new Stack[n + 1][n + 1];// 각 맵의 위치에서 말이 뭐가 있는지 알기 위한 큐
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				q[i][j] = new Stack<Integer>();
			}
		}

		// Deque<Pair> dq = new LinkedList<Pair>();
		Pair[] mal = new Pair[k + 1];
		
		for (int i = 1; i <= k; i++) {// 말이 있는 위치
			Stack<Integer> s = new Stack<Integer>();
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			q[x][y].add(i);// 맵에 몇번말이 들어가는지 표시
			// dq.add(new Pair(x, y , d));//1부터 k까지 말을 담는다
			mal[i] = new Pair(x, y, d);// 1부터 k까지의 말의 정보를 담는다
		}

		int cnt = 0;

		while (true) {// 말이 1,2,3...순서로 계속 움직임
			if (cnt > 1000) {
				System.out.println(-1);
				break;
			}
			cnt++;// 턴을 출력
			for (int i = 1; i <= k; i++) {
				Pair p = mal[i];
				 
				int kk = p.dir;
				int nx = p.x + dx[kk];
				int ny = p.y + dy[kk];

				// 말이 다음에 움직이는 다음 방향이 밑에와 같다면
				if (nx < 1 || ny < 1 || nx > n || ny > n || map[nx][ny] == 2) {// 범위밖으로 나가거나 파란색일때
					// 방향을 바꿔주고
					// System.out.println(ny);
					if (p.dir == 1)
						mal[i].dir = 2;
					else if (p.dir == 2)
						mal[i].dir = 1;
					else if (p.dir == 3)
						mal[i].dir = 4;
					else if (p.dir == 4)
						mal[i].dir = 3;
					// 위치 재 설정
					nx = p.x + dx[mal[i].dir];
					ny = p.y + dy[mal[i].dir];
				}

				if (nx >= 1 && ny >= 1 && nx <= n && ny <= n) {

					if (map[nx][ny] != 2) {// 범위 안이거나 파란색이 아닐때
						// 다음에 움직일 곳이 빨간색 ->일단 옮길 말의 위치를 빨강으로 다 이동시켜야 함
						// p(해당 말)과 그 위에 있는 말을 모두 옮기고자 해야함
						Deque<Integer> tmp = new LinkedList<Integer>();// 순서를 바꾸기 위한 임시 큐
						if (map[nx][ny] == 1) {
							while (!q[p.x][p.y].isEmpty()) {
								int tt = q[p.x][p.y].pop();
								tmp.addFirst(tt);
								if (tt == i) {
									break;
								}
							}
							while (!tmp.isEmpty()) {
								int tmp_p = tmp.pollLast();
								mal[tmp_p].x = nx;
								mal[tmp_p].y = ny;// 위치를 옮기고자 하는 곳으로 바꿔줌
								q[nx][ny].add(tmp_p);
							}

						}

						// 흰색일때
						else if (map[nx][ny] == 0) {
							while (!q[p.x][p.y].isEmpty()) {
								int tt = q[p.x][p.y].pop();
								tmp.addFirst(tt);
								if (tt == i) {
									break;
								}
							}

							while (!tmp.isEmpty()) {
								int tmp_p = tmp.pollFirst();
								//System.out.print(tmp_p + " ");
								mal[tmp_p].x = nx;
								mal[tmp_p].y = ny;// 위치를 옮기고자 하는 곳으로 바꿔줌
								q[nx][ny].add(tmp_p);
							}
							//System.out.println();

						}

						if (q[nx][ny].size() >= 4) {
							System.out.println(cnt);
							return;
						}

					}
				}
			}
		}

	}
}

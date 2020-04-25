package exam;

import java.util.*;
import java.lang.*;
import java.io.*;
//예제 4에서 자꾸 4가 남는 문제
//4번째 열과 1번째 열도 이어질 수 있다는걸 간과
//예제 5가 계속 안됐던 이유 - n과 m을 계속 착각해서 범위를 행n열n으로함^^ 이걸로 지금 몇시간 삽질한거임?

class solution2 {
	static class Pair {
		int x;
		int y;
		int val;

		public Pair(int x, int y, int val) {
			this.x = x;
			this.y = y;
			this.val = val;
		}
	}

	static int[][] arr;
	static int n;
	static int m;
	static int t;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 반지름=원판이 몇개 있는지 알려줌
		m = Integer.parseInt(st.nextToken());// m몇개의 정수가 한 판에 적혀있는지
		t = Integer.parseInt(st.nextToken());// 회전하는 횟수
		// 원판을 t번 회전시킨 후 원판에 적힌 수의 합을 출력한다
		arr = new int[n + 1][m + 1];// 각원판에 적힌 수를 담기 위한 배열
		int ans = 0;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= m; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		// 회전을 어떻게 시킬건지
		Queue<Integer> q = new LinkedList<Integer>();
		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());// 몇번째 배수의 원판을 돌리지
			int d = Integer.parseInt(st.nextToken());// 어느방향?
			int k = Integer.parseInt(st.nextToken());// 몇칸씩 돌릴건지=몇번돌릴건지
			for (int i = 1; i <= n; i++) {
				if (i % x == 0) {
					// System.out.println(i);
					q.add(i);// 몇번째 원판을 돌릴지 받는 큐
				}
			}

			while (!q.isEmpty()) {//해당배수만큼
				spin(q.poll(), d, k);// 해당 방향으로 k만큼 돌리고
			}

			nearby();

		} // while문 끝
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				ans += arr[i][j];
				//System.out.print(arr[i][j]);
			}
			//System.out.println();
		}
		System.out.println(ans);
	}

	public static void spin(int wh, int dir, int k) {// 원판을 돌리는 함수->해당하는 판을 매개변수로 보내주면 dir매개변수 방향으로 돌려주면됨
		Deque<Integer> dq = new LinkedList<Integer>();
		for (int i = 1; i <= m; i++) {
			dq.addFirst(arr[wh][i]);// 덱에 wh번째 원판에 있는 숫자를 넣는다
		}

		// 시계방향
		if (dir == 0) {
			while (k-- > 0) {
				dq.addLast(dq.pollFirst());
			}
			while (!dq.isEmpty()) {
				for (int i = 1; i <= m; i++) {
					arr[wh][i] = dq.pollLast();
				}
			}
		}
		// 반시계방향
		else if (dir == 1) {
			while (k-- > 0) {
				dq.addFirst(dq.pollLast());
			}
			while (!dq.isEmpty()) {
				for (int i = 1; i <= m; i++) {
					arr[wh][i] = dq.pollLast();
				}
			}
		}

	}
	public static void nearby() {// bfs로 인접한 같은 숫자 삭제
		Queue<Pair> q1 = new LinkedList<Pair>();
		boolean[][] check = new boolean[n + 1][m + 1];// 같아서 삭제가 되면 true바꿔서 못들리게
		boolean flag = false;
		int sum = 0;
		int cnt = 0;
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= m; j++) {
				if (arr[i][j] == 0)
						continue;// 0인애들은 걸러
				if(check[i][j] == true) 
					continue;
				q1.add(new Pair(i, j, arr[i][j]));
				sum += arr[i][j];
				cnt += 1;
				//check[i][j] = true;
				while (!q1.isEmpty()) {
					Pair p = q1.poll();
					int x = p.x;
					int y = p.y;
					int val = p.val;
					check[x][y] = true;
					//q1.add(new Pair(x,y,val));
					
					for (int k = 0; k < 4; k++) {
						int nx = x + dx[k];
						int ny = y + dy[k];
						// if(check[nx][ny] == true) continue;
						// if(nx < 0 || nx >= n) continue;

						if (nx > 0 && nx <= n) {// ny > 0 && ny <= m) {
							if (ny < 1)
								ny = m;
							if (ny > m )
								ny = 1;
							if (check[nx][ny] == false) {
								if (val == arr[nx][ny]) {
									check[nx][ny] = true;
									q1.add(new Pair(nx, ny, arr[nx][ny]));//큐에 넣는거를 여기 조건에서 넣어줘야함
									flag = true;
									arr[x][y] = 0;
									arr[nx][ny] = 0; // 0으로 삭제 시켜준다
								}
							}
						}
					}
				} // while끝
			}
		}
		//System.out.println(flag);
		if (flag == false) {// 한번도 같은 숫자를 찾지 못했다는 뜻
			for (int i = 1; i <= n; i++) {
				for (int j = 1; j <= m; j++) {
					if (arr[i][j] != 0) {
						if (arr[i][j] * cnt > sum)
							arr[i][j] -= 1;
						else if (arr[i][j] * cnt < sum)
							arr[i][j] += 1;
					}
				}
			}
		}
	}

}

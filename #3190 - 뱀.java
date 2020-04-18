package exam;

import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Pair {
	int x;
	int y;
	int time;
	int len;

	public Pair(int x, int y, int time, int len) {
		this.x = x;
		this.y = y;
		this.time = time;
		this.len = len;
	}
}
class tk{
	int time;
	//int k;
	String k;
	public tk(int time, String k) {
		this.time = time;
		this.k = k;
	}
}

class solution2 {
	static int n;
	static int len_snake;// 뱀의 길이
	static int time = 0;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };// 좌우상하
	static int[][] dist;
	static boolean[][] check;// 뱀길이를 t로 만들어서 게임이 끝나는지 아닌지 체크
	static ArrayList<tk> change_dir;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 보드의 크기

		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());// 사과의 개수

		ArrayList<Pair> apple_pos = new ArrayList<Pair>();// 사과의 위치를 받아옴
		change_dir = new ArrayList<tk>();

		map = new int[n + 1][n + 1];// 1행1열부터 시작하도록 만들자
		dist = new int[n + 1][n + 1];

		// 사과위치 받기
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;// 사과가 있는 곳은 1을 해놔서 1씩 뱀길이 증가할 수있도록
		}
		// 뱀의 방향 변환 횟수받기
		st = new StringTokenizer(br.readLine());
		int change = Integer.parseInt(st.nextToken());
		while (change-- > 0) {
			st = new StringTokenizer(br.readLine());
			int time = Integer.parseInt(st.nextToken());
			String dirr = st.nextToken(); int direction = 0;			
			change_dir.add(new tk(time, dirr));
		}

		int ans = 0;
		ans = bfs(1, 1, 0, 1, 1);// 뱀은 1.1에서 시작하고, 0초부터 길이가 1부터 시작, 동쪽(k=4)부터 시작
		System.out.println(ans);

	}

	public static int bfs(int a, int b, int time, int len, int dir) {
		Queue<Pair> q = new LinkedList<Pair>();
		Queue<Pair> qq = new LinkedList<Pair>();// 지나온 흔적을 큐에 다시 받는다

		check = new boolean[n + 1][n + 1];
		q.add(new Pair(a, b, time, len));// 뱀의 시작 위치를 받아 큐에 넣는다, 뱀이 처음시작 할때 시간도 0

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;
			int cur_time = p.time;
			int cur_len = p.len;
			check[x][y] = true;
			qq.add(new Pair(x, y, cur_time, cur_len));
			for (int i = 0; i < qq.size() - cur_len; i++) {
				Pair pp = qq.poll();
				check[pp.x][pp.y] = false;// 이건 뱀의 길이가 1일때 -> 2이상일때는? -> 뱀이 전에 위치했던 좌표를 알아야함
			}
			System.out.println("현재시간 " + cur_time);
			for(int k=0; k < change_dir.size(); k++) {
				if(cur_time  == change_dir.get(k).time) {
					if(change_dir.get(k).k.equals("D")) {
						if(dir == 0) 
							dir = 2;
						else if(dir == 1)
							dir = 3;
						else if(dir == 2)
							dir = 1;
						else if(dir == 3)
							dir = 0;
					}else {
						if(dir == 0)
							dir = 3;
						else if(dir == 1)
							dir = 2;
						else if(dir == 2)
							dir = 0;
						else if(dir == 3)
							dir = 1;
					}
				}
			}
		//	System.out.println("방향 " + dir);
			int nx = x + dx[dir];
			int ny = y + dy[dir];
		//	System.out.println("다음 진행방향 "+nx +", " + ny);
			int n_time = cur_time + 1;// 다음방향으로 옮기면 초가 1초늘어남
			int n_len = cur_len;
			if (nx > 0 && ny > 0 && nx < n && ny < n) {
				if (check[nx][ny] == false) {// 뱀이 차지하고 있는 칸이 아니라면
					if (map[nx][ny] == 1) {
						n_len = cur_len + 1;
						map[nx][ny] = 0;
					}
					q.add(new Pair(nx, ny, n_time, n_len));
				}else if(check[nx][ny] == true) {
					return n_time;
				}

			}else if(nx == n+1 || ny == n+1 || nx == 1 || ny ==1) {
				System.out.println("여기서 탈출");
				return n_time;
			}
		}
		return time;
	}

}

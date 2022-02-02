import java.util.*;
import java.lang.*;
import java.io.*;
//틀렸습니다, 런타임에러 문제 : 쓸떼없는 전역변수 너무 많이 선언, 벽(또는 뱀에 부딪힐때) 범위를 잘못설정

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
	String k;
	public tk(int time, String k) {
		this.time = time;
		this.k = k;
	}
}

class Main {
	static int n;
	static int n_time = 0;
	static int[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };// 좌우상하
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
		change_dir = new ArrayList<tk>();//방향이 바뀌는 지점을 저장하기 위함
		map = new int[n + 1][n + 1];// 1행1열부터 시작하도록 만들자

		// 사과위치 받기
		while (k-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			map[x][y] = 1;// 사과가 있는 곳은 1을 해놓기
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
		ans = bfs(1, 1, 0, 1, 1);// 뱀은 1.1에서 시작하고, 0초부터 길이가 1부터 시작, 동쪽(k=1)부터 시작
		System.out.println(ans);

	}

	public static int bfs(int a, int b, int time, int len, int dir) {
		Queue<Pair> q = new LinkedList<Pair>();
		Queue<Pair> qq = new LinkedList<Pair>();// 지나온 흔적을 큐에 다시 받는다

		check = new boolean[n + 1][n + 1];//뱀이 차지하고 있는 자리를 체크하기 위함
		q.add(new Pair(a, b, time, len));// 뱀의 시작 위치를 받아 큐에 넣는다, 뱀이 처음시작 할때 시간도 0

		while (!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;
			int cur_time = p.time;
			int cur_len = p.len;
			check[x][y] = true;
			qq.add(new Pair(x, y, cur_time, cur_len));
			for (int i = 0; i < qq.size() - cur_len; i++) {//뱀이 지나온 자리 - 현재 뱀의 길이. 만큼
				Pair pp = qq.poll();
				check[pp.x][pp.y] = false;// 이건 뱀의 길이가 1일때 -> 2이상일때는? -> 뱀이 전에 위치했던 좌표를 알아야함
				//뱀이 지나온자리(그리고 차지하고 있지않은자리)는 다시 false를 해준다
			}
			//뱀의 진행방향에 따라 우, 좌 방향이 달라지므로 설정해줌
			for(int j=0; j < change_dir.size(); j++) {
				if(cur_time  == change_dir.get(j).time) {
					if(change_dir.get(j).k.equals("D")) {
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
			//다음위치 설정
			int nx = x + dx[dir];
			int ny = y + dy[dir];
			n_time = cur_time + 1;// 다음방향으로 옮기면 초가 1초늘어남
			int n_len = cur_len;
			
			if (nx > 0 && ny > 0 && nx <= n && ny <= n) {//이 범위 안에서 움직일 수 있음
				if (check[nx][ny] == false) {// 뱀이 차지하고 있는 칸이 아니라면
					if (map[nx][ny] == 1) {//사과가 있는 자리라면
						n_len = cur_len + 1;//뱀의 길이가 증가
						map[nx][ny] = 0;//사과먹었으니까 삭제
					}
					q.add(new Pair(nx, ny, n_time, n_len));//뱀이 이동할 수 있도록 다음위치를 큐에 넣어준다
				}else if(check[nx][ny] == true) {//뱀이 차지하고 있는 칸이라면
					return n_time;//그때의 시간을 리턴
				}

			}else if(nx == n+1 || ny == n+1 || nx == 0 || ny == 0) {//움직이는 공간 밖에 닿으면
				return n_time;//그때의 시간 리턴
			}
		}
		return n_time;
	}
}

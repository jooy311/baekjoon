import java.util.*;
import java.lang.*;
import java.io.*;
//dfs를 이용했다가 스택 오버플로우 나서 실패
//아무생각없이 그냥 cnt++해서 실패
//아무 생각없이 그냥 p.cnt++한 값을 다음 nx의 cnt값으로 넣어버린실수(전에 p.cnt의 값은 변하면 안됨)

class Main {
	static class Pair {
		int x;
		int cnt;

		public Pair(int x, int cnt) {
			this.x = x;
			this.cnt = cnt;
		}
	}

	static boolean[] check;
	static Queue<Pair> q;
	static boolean flag = false;
	static int cnt = 0;
	static int min = 9999999;
	static int f, s, g, u, d;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());// 건물의 꼭대기층
		s = Integer.parseInt(st.nextToken());// 강호가 있는 층
		g = Integer.parseInt(st.nextToken());// 스타트링크가 있는 층
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		check = new boolean[f + 1];
		q = new LinkedList<Pair>();
		int[] dir = { u, d };

		q.add(new Pair(s, 0));
		bfs(dir);
		if (flag == false)
			System.out.println("use the stairs");//false로 끝났단건 안되는 경우
		else 
			System.out.println(min);
	}

	public static void bfs(int[] dir) {
		while (!q.isEmpty()) {
			Pair p = q.poll();
			if (p.x == g) {
				if(min > p.x)
					min = p.x;
				flag = true;//어쨌건 저쨌건 여기 들어왔다는건 목적지에 도착할 수 있는거니까 true
			}
			check[p.x] = true;
			for (int i = 0; i < 2; i++) {
				int nx = p.x + dir[i];
				if (i == 1)
					nx = p.x - dir[i];

				if (nx <= f && nx >= 1) {
					if (check[nx] == false) {
						int tmp = p.cnt + 1;
						q.add(new Pair(nx, tmp));
						check[nx] = true;
					}
				}
			}
		}
	}
}

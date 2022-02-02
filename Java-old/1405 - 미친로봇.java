import java.util.*;
import java.io.*;

public class solution {
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };// 남북서동
	static double[] cap;
	static double sum=0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 로봇이 몇번이동하는지

		// 동서남북 확률을 받는다
		cap = new double[4];
		boolean[][] visited = new boolean[n * 2+1][n * 2+1];// 로봇이 최대로 움직일 수 있는 공간을 만들어줌 -> 로봇위치는 가운데

		for (int i = 0; i < 4; i++) {// 0,25,50,75,100의 확률
			// st = new StringTokenizer(br.readLine());
			cap[i] = Integer.parseInt(st.nextToken()) * 0.01;// 차례로 동서남북 확률
			//System.out.println(cap[i]);
		}
		dfs(n, n, n, 0, visited, 1);System.out.println(sum);

	}

	public static void dfs(int x, int y, int n, int depth, boolean[][] visited, double capa) {
		visited[x][y] = true;
		if (depth == n) {
			// 확률을 구해
			sum += capa;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			if (nx >= 0 && ny >= 0 && nx <= 2 * n && ny <= 2 * n) {
				if (visited[nx][ny] == false) {
					visited[nx][ny] = true;// 한번 방문했으니까 true로 바꿔줌
					dfs(nx, ny, n, depth + 1, visited, capa * cap[k]);
					visited[nx][ny] = false;//백트랙킹
				}
			}
		}
	}
}// class끝

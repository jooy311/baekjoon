package exam;

import java.util.*;
import java.io.*;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class solution {
	static int[][] map = new int[5][5];
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	static Set<String> set = new HashSet<String>();//set이 중복을 제거하여 넣어주는 역할을 
	static int cnt = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		for (int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());// 판을 입력받음
			}
		}
		int len = 0;
		int[] arr = new int[6];

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				arr[i] = map[i][j];
				dfs(i, j, 1, map[i][j] + "");// 각 점이 시작점이 되고, 그 시작점부터 dfs시작할 수 있도록
			}
		}
	
		System.out.println(set.size());

	}

	public static void dfs(int a, int b, int len, String str) {

		ArrayList<String> check = new ArrayList<String>();

		if (len == 6) {
			set.add(str);
			//cnt++;
			return;
		}

		for (int k = 0; k < 4; k++) {
			int nx = a + dx[k];
			int ny = b + dy[k];
			if (nx >= 0 && ny >= 0 && nx < 5 && ny < 5) {
				dfs(nx, ny, len + 1, str + map[nx][ny]);// dfs를 돌게하면서 stack에 노드를 넣음
			}
		}
	}
}

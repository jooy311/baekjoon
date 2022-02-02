import java.util.*;
import java.io.*;

public class solution {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int[][] map;
	static int blue = 0, white = 0;

	public static void count_paper(int n, int x, int y) {
		int colored = map[x][y];// 0 또는 1
		//int white = 0;
		//int blue = 0;
		for (int i = x; i < n + x; i++) {
			for (int j = y; j < n + y; j++) {
				if (colored != map[i][j]) {
					count_paper(n/2, x, y);
					count_paper(n / 2, n / 2 + x, y);
					count_paper(n / 2, x, n / 2 + y);
					count_paper(n / 2, n / 2 + x, n / 2 + y);
					return;
				}
			}
		}
		// if(x == n && y == n)
		if (colored == 1)
			blue++;
		else
			white++;

	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		map = new int[n][n];

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken()); // 색종이를 받는다
			}
		}
		count_paper(n, 0, 0);
		System.out.println(white);
		System.out.println(blue);
		
	}
}// class끝

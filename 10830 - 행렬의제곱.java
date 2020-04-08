//출력값때문에...왜그러는거냐 대체...하..
import java.util.*;
import java.io.*;

public class solution2 {
	// static long[][] a;
	static int n;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 로봇이 몇번이동하는지
		long b = Long.parseLong(st.nextToken());

		long[][] a = new long[n][n];
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				a[i][j] = Long.parseLong(st.nextToken());
			}
		}
		long[][] result = multiple(b, a);

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				System.out.print(result[i][j]% 1000 + " ");
			}
			System.out.println("");
		}

	}

	public static long[][] multiple(long b, long[][] a) {
		// 왼쪽에 있는 행렬의 행을 계속 보내줘야함
		long[][] result = new long[n][n];
		long[][] next = new long[n][n];

		if (b == 1) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					next[i][j] = a[i][j];
				}
			}
			return next;
		}
		if (b % 2 == 0) {
			next = multiple(b / 2, a);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						result[i][j] += next[i][k] * next[k][j];
					}
					result[i][j] %= 1000;
				}

			}
			return result;

		} else {
			next = multiple(b - 1, a);
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					for (int k = 0; k < n; k++) {
						result[i][j] += next[i][k] * a[k][j];
					}
					result[i][j] %= 1000;
				}

			}
			return result;
		}

	}
}

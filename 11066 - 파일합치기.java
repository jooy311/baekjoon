import java.util.*;
import java.io.*;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());// 파일의 개수
			int[] a = new int[k];
			int[][] dp = new int[k][k];

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < k; i++) {
				a[i] = Integer.parseInt(st.nextToken());
			}
			
			for (int i = 0; i < k - 1; i++) {
				dp[i][i + 1] = a[i] + a[i + 1]; // 1,1 2,2 3,3...이외에 서로 더해진 값을 칸에 넣는 작업
			}

			int dist = 2; // 차이가 2씩남
			while (dist < k) {
				for (int i=0; i < k; i++) {
					int j = i + dist;
					 
					if (j >= k) {
						break;
					}
					int tmp = 100000000;
					for (int s = i; s < j; s++) {
						if (dp[i][s] + dp[s + 1][j] < tmp) {
							tmp = dp[i][s] + dp[s + 1][j];// 순회하면서 가장 최소값을 저장하게 함
						}
					}
					dp[i][j] = tmp; // 가장 작은값과 a의 값을 더해주는 과정

					for (int y = i; y <= j; y++) {
						dp[i][j] += a[y];
					}
				}

				dist++;// 차이를 하나씩 들려서 주 대각선에 위치되도록 함
			}
				System.out.println(dp[0][k - 1]); // 첫째행의 제일 마지막을 출력하면 됨

		} // while문 끝

	}

}

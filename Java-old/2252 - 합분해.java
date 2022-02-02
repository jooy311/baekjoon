import java.util.*;
import java.io.*;

public class solution2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken());// 더하기의 갯수는 k-1
		long[][] dp = new long[k + 1][n + 1];
		
		for (int j = 0; j <= n; j++) {
			dp[1][j] = 1;// k=1인경우엔 무조건 1가지 밖에 없으므로 초기 셋팅
		}

		for (int i = 2; i <= k; i++) {// __+__ 경우부터 점화식 시작
			for (int j = 0; j <= n; j++) {
				for (int t = 0; t <= j; t++)
					dp[i][j] = dp[i][j] + dp[i - 1][t];
				// 우리가 구하는값 = 우리가구하는값 + k-1번 더해서 t를 구하는 가지수
				dp[i][j] %= 1000000000;
			}
		}
		System.out.println(dp[k][n]);
	}
}

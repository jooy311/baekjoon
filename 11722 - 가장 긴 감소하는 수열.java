import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/11722

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());// 수열의 크기
		int[] arr = new int[n + 1];
		int[] dp = new int[n + 1];
		int[] test = new int[n + 1];

		int max = 1;//처음에 0으로 초기화해줬더니, 수열의 길이가 1일때 0으로 나오는 오류가 발생
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
			dp[i] = 1;
		}

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j < i; j++) {
				if (arr[i] < arr[j] && dp[i] < dp[j] + 1) {
					dp[i] = dp[i] + 1;
					test[i] = dp[i];
				}
				if (max < dp[i])
					max = dp[i];
			}
		}
		System.out.println(max);
	}
}

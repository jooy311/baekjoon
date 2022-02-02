import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		String[] arr = st.nextToken().split("");
		st = new StringTokenizer(br.readLine());
		String[] arr2 = st.nextToken().split("");
		int[][] dp = new int[1001][1001];
		LCS(arr, arr2, dp);	
		System.out.println(dp[arr.length][arr2.length]);
	}

	public static void LCS(String[] arr, String[] arr2, int[][] dp) {

		for (int i = 1; i <= arr.length; i++) {
			for (int j = 1; j <= arr2.length; j++) {
				if(arr[i-1].equals(arr2[j-1])){ //문자열이 같다면
					dp[i][j] = dp[i-1][j-1] + 1; //왼쪽대각선방향값에 +1
				}else {
					dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]); //같지 않다면 위쪽,왼쪽 값중(전에 가장 길었던 LCS길이)긴것의 값을 가져옴
				}
			}
		}
	}
}

import java.util.*;
import java.io.*;

public class solution {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());// 계단수
		int[] arr = new int[N + 1];
		int[] DP = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		DP[0] = 0;
		DP[1] = arr[1];// DP[계단층수]->계단이 한개일때
		if (N >= 2) {//계단이 1개일때
			DP[2] = Math.max(arr[1], arr[1] + arr[2]);
			if (N >= 3) {//계단이 2개일때 예외처리 못해서 계속 런타임 에러났었음...
				DP[3] = Math.max(arr[1] + arr[3], arr[2] + arr[3]);

				for (int i = 4; i <= N; i++) {
					DP[i] = Math.max(DP[i - 2] + arr[i], DP[i - 3] + arr[i - 1] + arr[i]);//3개연속으로 못오르기때문에 생긴 점화식....
				}
			}
		}
		System.out.println(DP[N]);
	}
}

//greedy 알고리즘을 사용

import java.util.*;
import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		int k = Integer.parseInt(st.nextToken()); // 동전 총 합
		int[] a = new int[n];
		int max = 0;

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			int coin = Integer.parseInt(st.nextToken());

			if (coin <= k) 
				max = i;
			
			a[i] = coin;
		}
    
		int ans = 0;
		for (int i = max; i >= 0; i--) {
			if (a[i] != 0) {
				int cnt = k / a[i];
				k %= a[i];
				ans += cnt;
			}
		}
		System.out.println(ans);
	}
}

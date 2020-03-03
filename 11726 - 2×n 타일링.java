import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class solution {
	static int[] nemo = new int[1001];

//다이나믹프로그래밍 - Top_down방식
	public static int ans(int n) {
		if (n <= 1)
			return 1;
		else {
			if (nemo[n] > 0) return nemo[n]; //메모라이제이션
			
			nemo[n] = ans(n-1) + ans(n-2);
			nemo[n]= nemo[n] % 10007; //여기서 10007의 나머지연산을 해야만
		}
		return nemo[n];
	}
  
  /*
  /* 다이나믹프로그래밍 - bottom_up방식(작은거부터 시작) */
	public static int ans2(int n) {
		nemo[0] = 1;
		if (n > 0)
			nemo[1] = 1; // 또는 nemo[1] =1;
		for (int i = 2; i <= n; i++) {
			nemo[i] = nemo[i - 1] + nemo[i - 2];
			nemo[i] = nemo[i] % 10007;
		}
		return nemo[n];
	}
  */

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 직사각형의 가로길이를 정하기위해
		System.out.println(ans(n));
	}
}

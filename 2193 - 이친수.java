import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/2193

public class Main {

	public static long dynamic(long[] dp, int i) {//long타입으로 해주어야 n=90일때 큰 값을 받아낼 수 있음
		dp[0] = 0;
		dp[1] = 1;
		dp[2] = 1;//1 1 2 3 5 8.......
		if(i < 2) {
			return dp[i];
		}
		else {//2보다 크면
			if(dp[i] > 0) return dp[i];
			dp[i] = dynamic(dp,i-1) + dynamic(dp,i-2);//앞에 것들 두개 더한게 현재 위치의 값이 됨
			return dp[i];
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// n개의 수

		long[] dp = new long[100];
		long cnt;
		cnt = dynamic(dp,n);
		System.out.println(cnt);		
	}
}

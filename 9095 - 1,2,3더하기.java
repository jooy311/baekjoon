import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/9095

public class Main {
	public static int sum(int ans, int goal) {
		if(ans > goal) return 0;
		if(ans == goal) return 1;
		int cnt =0;
		for(int i=1; i<4; i++) {
			cnt += sum(ans+i, goal);
		}
		return cnt;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());// 테스트 케이스

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());// 탑의 개수
			int cnt = sum(0,n);
			System.out.println(cnt);
		}
	}
}

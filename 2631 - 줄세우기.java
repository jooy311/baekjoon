import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/2631

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 아이들의 수
		
		int[] a = new int[n + 1];
		int[] d = new int[n + 1];
		int max = 0;
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			a[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 1; i <= n; i++) {
			d[i] = 1;
			for (int j = 1; j < i; j++) {
				if (a[j] < a[i] && d[i] < d[j] + 1) {
					d[i] ++;
				}
				if (max < d[i])
					max = d[i];
			}
		}
		System.out.println(n-max);
	}
}

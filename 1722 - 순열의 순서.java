import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

/*
 * https://www.acmicpc.net/problem/9466
 */

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// n개의 수
		long[] f = new long[21];//
		
		boolean[] check = new boolean[21]; //순열이 들어있는지 아닌지를 체크하기 위해
		
		st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		f[0] = 1;
		for(int i=1; i<21; i++) {
			f[i] = f[i-1] * i;
		}
		
		if(test == 1) {//k번재 수열을 구해
			long k = Long.parseLong(st.nextToken());
			int[] arr = new int[n];
			for(int i=0; i<n; i++) {
				for(int j=1; j<=n; j++) {
					if(check[j] == true) continue;
					if(f[n-i-1] < k) {
						k -= f[n-i-1];
					}
					else {
						arr[i] = j;
						check[j] = true;
						break;
					}
				}
			}
			for(int i=0; i<n; i++) {
				System.out.print(arr[i] + " ");
			}		
		}
		
		if(test == 2) {//이 수열이 몇번째 수열이니?
			int[] arr = new int[n];
			long cnt =0;
			for(int i=0; i<n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			
			for(int i=0; i<n; i++) {
				for(int j=1; j< arr[i]; j++) {
					if(check[j] == false) {
						cnt += f[n-i-1];
					}
				}
				check[arr[i]] = true;
			}
			System.out.println(cnt+1);
		}
				
	}
}

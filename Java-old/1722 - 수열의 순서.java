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
		long[] f = new long[21];//팩토리얼 값을 넣어놓고 사용할 수 있도록 배열을 만듦
		
		boolean[] check = new boolean[21]; //수열이 들어있는지 아닌지를 체크하기 위해
		
		st = new StringTokenizer(br.readLine());
		int test = Integer.parseInt(st.nextToken());
		
		f[0] = 1;//처음 시작을 일단 1부터 시작
		for(int i=1; i<21; i++) {//팩토리얼 값계산하도록 하기위해
			f[i] = f[i-1] * i;
		}
		
		if(test == 1) {//k번재 수열을 구해
			long k = Long.parseLong(st.nextToken());
			int[] arr = new int[n];//출력할 k번째 수열을 담기 위한 배열
			for(int i=0; i<n; i++) {
				for(int j=1; j<=n; j++) {
					
					if(check[j] == true) continue; //j가 수열에 들어있다면 pass
					//j가 수열에 들어있지 않는다면
					if(f[n-i-1] < k) {//만약 3번째 수열을 구한다하면 f[]
						k -= f[n-i-1];//n-(i+1)
					}
					else {//k값이 더 크다면
						arr[i] = j;//arr[0]에는 
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

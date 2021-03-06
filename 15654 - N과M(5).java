import java.util.*;
import java.io.*;

/*
 * https://www.acmicpc.net/problem/9466
 */

public class Main {

	public static void go(int start, int n, int m, int[] arr, boolean[] check, int[] a) {
		if (start == m) {//두개 골라졌다면
			//if (check[start])
			for(int i=0; i<m; i++)
				System.out.print(arr[i]+" ");
			System.out.println("");
			return;
		}

		for (int i =0 ; i < n; i++) {
			if (check[i] == true)
				continue;// true이면 이미 한번 기준이 되었던 애들이므로 pass
			check[i] = true;// true로 만들어 주고
			arr[start] = a[i];
			go(start + 1, n, m, arr, check,a);
			check[i] = false;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// n개의 수
		int m = Integer.parseInt(st.nextToken());// m개

		int[] arr = new int[n];//얘는 그냥 선택한 숫자를 m개담는 배열
		boolean[] check = new boolean[n];
		int[] a =  new int[n];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(a);
		go(0, n, m, arr, check,a);
	}
}

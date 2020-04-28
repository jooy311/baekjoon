import java.util.*;
import java.lang.*;
import java.io.*;
//1부터 n까지 자연수 중에서 중복없이 m개를 고름-오름차순

class Main {
	static int[] arr;
	static boolean[] check;
	static int n;
	static int m;
	static int[] dx = {};//좌우

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 총몇개
		m = Integer.parseInt(st.nextToken());// 몇개씩 짝궁
		//arr = new ArrayList<Integer>();
		check = new boolean[n+1];
		arr = new int[n+1];

		suyeol(1,0);
	}

	public static void suyeol(int a, int depth) {// 기준점을 받아온다
		//check[a] = true;
		if(depth == m) {
			for(int i=0; i<depth; i++) {
				System.out.print(arr[i]+" ");
			}
			System.out.println();
			return;
		}
		for (int i = a ; i <=n; i++) {
			if (check[i] == false) {
				check[i] = true;
				arr[depth] = i;
				suyeol(i,depth+1);
				check[i] = false;
			}
		}

	}
}

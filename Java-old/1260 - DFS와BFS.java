package exam;

import java.util.*;
import java.io.*;

public class solution2 {
	static int n, m, v;
	static boolean[] check;
	static boolean[] check2;
	//static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 정점의 개수
		m = Integer.parseInt(st.nextToken()); // 간선의 개수
		v = Integer.parseInt(st.nextToken()); // 시작할 정점의 번호

		check = new boolean[n + 1];
		check2 = new boolean[n + 1];

		ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[n + 1];

		for (int i = 1; i <= n; i++) {
			arr[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			arr[a].add(b);
			arr[b].add(a);
		}
		for (int i = 1; i <= n; i++) {
			Collections.sort(arr[i]);
		}

		dfs(v, arr);
		System.out.println("");

		Queue<Integer> q = new LinkedList<Integer>();
		bfs(v, arr, q);

	}

	public static void bfs(int a, ArrayList<Integer>[] arr, Queue<Integer> q) {
		q.add(a);
		while (!q.isEmpty()) {
			int next = q.poll();
			System.out.print(next + " ");
			check2[next] = true;
			for (int i = 0; i < arr[next].size(); i++) {
				if (check2[arr[next].get(i)] == false) {
					check2[arr[next].get(i)] = true;
					q.add(arr[next].get(i));// 시작점에 붙어있는 애들 다 넣어
				}
			}
		}
	}

	public static void dfs(int a, ArrayList<Integer>[] arr) {// 현재 노드를 받아옴
		int min = n;
		check[a] = true;
	
		System.out.print(a + " ");
		for (int i = 0; i < arr[a].size(); i++) {
			if (check[arr[a].get(i)] == false) {
				dfs(arr[a].get(i), arr);
			}
		}
	}

}

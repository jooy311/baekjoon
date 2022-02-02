import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/6603

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while (true) {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			int k = Integer.parseInt(st.nextToken());// 간선의 개수
			if (k == 0)
				break;
        
			int[] s = new int[k];
			for (int i = 0; i < k; i++) {
				s[i] = Integer.parseInt(st.nextToken());
			}
			int[] temp = new int[6];

			choose(k, s, 0, temp, 0);
			System.out.println("");
		}
	}

	public static void choose(int k, int[] s, int idx, int[] temp, int depth) {

		if (depth == 6) {
			for (int i = 0; i < 6; i++) {
				System.out.print(temp[i] + " ");
			}
			System.out.println("");
			return;
		}

		for (int i = idx; i < k; i++) {// 6고정이니까
			temp[depth] = s[i];
			choose(k, s, i + 1, temp, depth + 1);
		}
	}
}

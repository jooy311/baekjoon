import java.util.*;
import java.io.*;

public class solution {
	static boolean[] visited = new boolean[10];
	static ArrayList<String> list = new ArrayList<String>();
	static String[] arr;
	static int k;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		k = Integer.parseInt(st.nextToken());// 계단수

		arr = br.readLine().split(" ");// 여기에서 지금 >가 안들어가는 거지

		for (int i = 0; i <= 9; i++) {
			visited[i] = true;
			dfs(i, 0, i + "");// 전달할때 i를 앞에 이미 넣고 출발하는구나
		}
		System.out.println(list.get(list.size() - 1));// 최대(리스트의 뒤에서 프린트)
		System.out.println(list.get(0));// 최소(리스트의 앞에서 프린트)
	}
	// 두개의 수를 하나씩 비교하면서 진행
	public static void dfs(int v, int cnt, String str) {// 메인함수에서 보내준 i=v가 일단 기준
		if (cnt == k) {
			// success
			list.add(str);
			//return;   //리턴하면 잘못된 결과값 도출
		} else {
			for (int i = 0; i <= 9; i++) {// 0~9숫자를 차례로 검사
				if (visited[i] == false) {// 방문을 안했다면 진행
					if (arr[cnt].equals("<")) {
						if (v >= i)
							continue;// for문으로 돌아가서 i++이 진행됨
					} else if (arr[cnt].equals(">")) {
						if (v <= i)
							continue;
					}
					visited[i] = true;// 방문을 한거니까 true로 바꿔줌
					dfs(i, cnt + 1, str + i);//
				} // visited = true인 애들은 여기로 나와서 for문으로 돌아가서 i++이 됨
			} // for문 끝
		}
		// backtracking
		visited[v] = false;
	}
}

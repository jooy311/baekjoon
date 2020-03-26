import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/2252\

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 아이들의 수
		int m = Integer.parseInt(st.nextToken());// 키를 비교한 횟수
		ArrayList<Integer>[] student = (ArrayList<Integer>[]) new ArrayList[n + 1];// 배열리스트의 배열만 선언해준것
		int[] indegree = new int[n + 1];// 들어오는 화살표의 개수를 저장하기위한 배열		

		for (int i = 1; i <= n; i++) {
			indegree[i] = 0;// 0으로 초기 셋팅
		}

		for (int i = 1; i <= n; i++) {
			student[i] = new ArrayList<Integer>();// 배열리스트의 각각항목에 들어갈 리스트를 선언 꼭 해주어야 값이 들어감!!!!
		}
    
		while (m-- > 0) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());

			student[a].add(b);// 간선 만들어주기
			indegree[b]++;// a->b를 가리키고 있으니까 b의 해당되는 차수를 1증가 시킴 -> 차수가 설정됨
		}

		lineUp(n, indegree, student, qq);
	}

	public static void lineUp(int n, int[] indegree, ArrayList<Integer>[] student, Queue<Integer> qq) {
		Queue<Integer> q = new LinkedList<Integer>();

		for(int i=1; i<=n; i++) {
			if (indegree[i] == 0) // 차수가 0이면 아무도 걔를 선택하지 않은애들인데 걔네들이 결국 시작점이 되므로
					q.add(i);// 큐에 넣음
				// 결국 언젠가는 차수 많은것도 들어가게됨
		}

		// 큐에서 제거가 되면 차수도 끊어짐
		while (!q.isEmpty()) {
			int std = q.poll();
			for (int i : student[std]) {// std에 연결되어있던 모든 점들의 차수를 낮춰준다
				indegree[i]--;

				if (indegree[i] == 0)
					q.add(i);
			}
			System.out.print(std + " ");
		}
	}
}

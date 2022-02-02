import java.util.*;
import java.io.*;

public class Main {
	//전역 변수를 사용하기 위해....
	static int n; // 노드 갯수
	static int k; // 간선 갯수
	static int[] d; // 시간을 넣어주는 배열

	public static void acm(int goal, ArrayList<Integer>[] a, int[] indegree) { // n은 건물수
		Queue<Integer> q = new LinkedList<Integer>();// 큐를 하나 만들고
		int[] max = new int[n + 1];// 건물 완공시간의 누적시간을 여기다 저장할 것임.

		for (int i = 1; i <= n; i++) {// 건물은 0이아니라 1부터 시작하므로 변수를 1부터 시작하도록 만든다
			max[i] = d[i];
			
			if(indegree[i] == 0) 
				q.offer(i);
		}
		
		while(!q.isEmpty()) {
			int gunmul = q.poll();
			
			for(int i : a[gunmul]) {
				max[i] = Math.max(max[i], max[gunmul] + d[i]);
				indegree[i] --;
				
				if(indegree[i]==0)
					q.offer(i);
			}
		}
		System.out.println(max[goal]);

	}// 재귀함수 끝

	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null; // ★일단 객체 선언
		int t = Integer.parseInt(reader.readLine()); // 테스트케이스

		/* 테스트 케이스 시작 */
		while (t-- > 0) {
			// 한 줄에 int형 을 두개 받고 공백을 기준으로 n과 k를 나누고 싶음
			st = new StringTokenizer(reader.readLine());// 1.Tokenizer객체를 하나 받고
			n = Integer.parseInt(st.nextToken()); // 건물의 개수 - 차례로 토큰 하나씩 받아온다
			k = Integer.parseInt(st.nextToken());// 규칙의 개수

			int[] indegree = new int[n + 1];

			ArrayList<Integer>[] a = (ArrayList<Integer>[]) new ArrayList[n + 1];
			d = new int[n + 1]; // 건물 건설 시간 배열

			// 각 건물에 연결되어 있는(건설순서) 순서 정해주기위해
			for (int i = 1; i <= n; i++) {
				a[i] = new ArrayList<Integer>();
			}

			// 각 건물의 건설시간 받기
			st = new StringTokenizer(reader.readLine());
			for (int i = 1; i <= n; i++) {
				d[i] = Integer.parseInt(st.nextToken());
			}

			// x->y 규칙을 받기위해
			for (int i = 1; i <= k; i++) {
				st = new StringTokenizer(reader.readLine());
				int X = Integer.parseInt(st.nextToken());
				int Y = Integer.parseInt(st.nextToken());
				a[X].add(Y);
				indegree[Y]++; // ★★★차수 설정
			}

			st = new StringTokenizer(reader.readLine());
			int w = Integer.parseInt(st.nextToken()); // 목적지 건물을 적어주세영

			acm(w, a, indegree);
		}
	}// while문 끝
}

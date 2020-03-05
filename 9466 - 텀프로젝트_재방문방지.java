import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/9466

public class Main {
	static boolean[] check;// 단순히 방문여부를 체크하는 배열
	static int[] arr;
	static boolean[] finished;// 이미 방문한 노드에서 싸이클을 찾았음을 표시하기 위한 배열
	static int cnt;
	static int n;

	public static void team(int now) {
		// 넘어온 노드번호가 이미 방문한 노드라면 pass 검사 안함.
		if (check[now] == true)
			return;
		
		/*----여기는 다 방문 안했던 노드만 넘어옴----*/
		check[now] = true; // 현재 노드를 방문했으니까 true로 바꿔줌
		int temp = arr[now]; // 지금노드의 값이 다시 다음 노드의 번호가 됨

		//1. 다음 노드가 아직 방문한 노드가 아님
		if (check[temp] == false) 
			team(temp); // 재귀를 다시 도는데->이 노드를 방문한것이므로 위에서 check가 true가 됨
		
		//2. 다음 노드가 이미 방문한 노드
		else {
				// 노드의 탐색이 종료하려면 무.조.건. 싸이클을 거쳐야만 함
			if (finished[temp] == false) {// 아직 싸이클을 찾지 못한 경우라면
				cnt++;//혼자 팀결성인 학생이라면 여기서 +1만 됨.
				
				for (int i = temp; i != now; i = arr[i])//temp노드부터 시작해서, 결국 다음노드가 now노드번호랑 같아질때까지 arr을 탐색해라
					cnt++;//팀이 결성된 학생 수 만큼 카운트가 됨.
			}
		}
		finished[now] = true;//현재 노드의 상태를 다시는 못방문하게 true로 바꿔줌
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());// 테스트 케이스

		while (t-- > 0) {
			cnt = 0;
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());// 학생수
			check = new boolean[n + 1];
			finished = new boolean[n+1];

			arr = new int[n + 1];
			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[i] = Integer.parseInt(st.nextToken());// 값이 안들어가는 이유가 뭐야?
			}

			for (int i = 1; i <= n; i++) 
				team(i);
			
			System.out.println(n - (cnt));
		}
	}
}

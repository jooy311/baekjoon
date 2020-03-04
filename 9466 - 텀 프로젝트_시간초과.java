import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/9466

public class Main {
	static Stack<Integer> stack = new Stack<>();// 팀결성된 친구들 넣는 스택
	static int cnt;
	static int cnt2;
	static int cnt3;
	static int n;

	public static void team(Stack<Integer>[] arr, boolean[] check, int i, int n, Stack<Integer> s) {
		int temp = 0;
		if (stack.size() >= 1)
			check[stack.peek()] = true;

		if (i == arr[i].peek() && check[i] == false) {// 자기 자신을 선택하면
			stack.push(i); // 한명이 팀으로 묶임
			cnt++;
			check[i] = true;
		}
		if (check[i] == false) {
			check[i] = true;
			cnt2++;
			temp = arr[i].peek();
			team(arr, check, temp, n, s);
		}
		if (check[i] == true && s.peek() == arr[i].peek()) {// 팀이 결성된 경우->근데 팀인애를 어케 아냐
			cnt3 = cnt2;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());// 테스트 케이스
		Stack<Integer>[] arr;

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());// 학생수
			boolean[] check; // = new boolean[n + 1];;
			Stack<Integer> s = new Stack<>();

			arr = (Stack<Integer>[]) new Stack[n + 1];
			for (int i = 1; i <= n; i++) {
				arr[i] = new Stack<>();
			}

			st = new StringTokenizer(br.readLine());
			for (int i = 1; i <= n; i++) {
				arr[i].add(Integer.parseInt(st.nextToken()));
			}

			for (int i = 1; i <= n; i++) {
				s.push(i);
				check = new boolean[n + 1];
				cnt2 = 0;
				team(arr, check, i, n, s);
			}
		}
		System.out.println(n - (cnt + cnt3));

	}
}

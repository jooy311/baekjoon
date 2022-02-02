import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/1026

class Pair2 {
	int pos;
	int height;

	public Pair2(int pos, int height) {
		this.pos = pos;
		this.height = height;
	}
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 탑의 개수
		Stack<Pair2> s = new Stack<Pair2>();
		Pair2[] arr = new Pair2[n + 1];

		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			arr[i] = new Pair2(i, Integer.parseInt(st.nextToken()));
		}

		for (int i = 1; i <= n; i++) {
			if (i == 1) {
				System.out.print("0" + " ");
				s.push(arr[i]);// 첫번째 건물을 스택에 넣는다
				continue;// 다음 i로 가도록
			}

			if (s.peek().height <= arr[i].height) {// 나보다 작으면 pop한다
				while (!s.isEmpty()) {
					if (arr[i].height < s.peek().height)
						break;
					s.pop();//처음에 먼저pop해주고 조건문 걸었더니 emptystack오류 떠서 고생함 뒤에다 빼는게 맞음 먼저 확인하고->pop
				}

				if (s.isEmpty())// pop했는데 스택이 비어있다면 0출력
					System.out.print("0" + " ");
				else
					System.out.print(s.peek().pos + " ");
				s.push(arr[i]);// 그리고 나를 넣는다.
			} else {// 나보다 크다면 냅둔다.
				System.out.print(s.peek().pos + " ");
				s.push(arr[i]);// 그리고 나를 넣는다.
			}

		}
	}
}

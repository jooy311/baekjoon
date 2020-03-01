import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

public class Main {
	public static void vps(String str, boolean check) {
		Stack<Integer> stack = new Stack<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) == '(') {
				stack.push(i);
			} else if (str.charAt(i) == ')') { // 검사하다가 )를 만났어
				if (!stack.isEmpty()) {
					if (stack.peek() + 1 == i) { // 스택 top을 검사해보니 )가 (다음 바로 나왔어
						stack.pop(); // 그럼 (를 스택에서 빼줘
					} else {// 이중으로 감싸져있던 괄호
						if (!stack.isEmpty())// 스택이 비어있지 않다면 짝꿍이 있다는 얘기 이므로
							stack.pop();// 스택에서 없애준다
						else// 얘는 vps가 아님 짝꿍이 없는 경우이므로
							check = false;
					}
				} else // )의 짝궁을 찾아보려했는데 짝꿍이없고 스택이 비어있다 -> flase
					check = false;
			}
		}

		if (stack.isEmpty() == false)
			check = false;

		if (check == false)
			System.out.println("NO");
		else
			System.out.println("YES");
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());// 테스트케이스

		boolean check = true;

		while (t-- > 0) {
			st = new StringTokenizer(br.readLine());
			String str = st.nextToken();
			vps(str, check);
		}
	}
}

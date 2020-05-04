import java.util.*;
import java.lang.*;
import java.io.*;
//전체 문자열의 길이를 파악
//닫는거 먼저 시작하면 고쳐야됨
//여는 거 먼저시작하면 다음에 뭐 오는지 봐야됨
//만약 닫는게 바로오면 다행
//만약 또 여는게 오면 전체길이가 짝수라 했으니 (만약 길이가 4라면) 그다음은 무조건 닫는게 와야함 - 그게아니라면 고쳐

class Main {

	static boolean flag = false;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int cnt = 0;
		while (flag != true) {// -를 만날때까지
			cnt++; // 출력시 번호를 매기기 위한 변수
			int change = 0;
			Stack<Integer> stack = new Stack<Integer>();
			String line = br.readLine();// 한줄을 받아온다
			if (line.contains("-")) {
				flag = true;
				break;
			}
			
			String[] s = line.split("");
			for (int i = 0; i < line.length(); i++) {
				String str = s[i];
				if (str.equals("{"))
					stack.push(i); // 여는걸로 시작한다면 해당 위치번호를 넣어주고
				else if (str.equals("}")) {// 검사하려는 게 닫는 괄호면
					if (!stack.isEmpty()) {// 스택이 비어있지 않다면
						stack.pop();// 짝결성
					} else {// 스택이 비어있다 -> 닫는거먼저 들어간거니까 바꿔야함
						stack.push(i);// {로 바꿨다는 가정하에 스택에 넣는거임
						change++;// 바꾼걸로 가정한거니까 change를 올린다
					}
				}

			} 
			change += stack.size() / 2;
			if (change < 0)
				change = 0;
			System.out.println(cnt + ". " + change);

		}
	}

}

import java.util.*;
import java.io.*;
//https://www.acmicpc.net/problem/10799
public class Solution {
	public static int solution(String arrangement) {
		int answer = 0;
		Stack<Integer> st = new Stack<>(); //스택생성
		for (int i=0; i < arrangement.length(); i++) { //받은 스트링의 길이만큼 돌때까지
			if(arrangement.charAt(i) == '(') { // (문자라면
				st.push(i);//해당 문자가 아니라 ★i(도는 순서)★를 스택에 넣어준다
			}else if(arrangement.charAt(i)==')') { /// )문자 라면
				if(st.peek() + 1 == i) { //바로 다음 순번이 )문자라면
					st.pop();//빼준다(왜? 레이저이니까)
					answer += st.size(); //스택의 사이즈만큼 막대기가 생기므로 더해줌
				}else {//레이저가 아니고 막대기의 끝을 의미하는 것
					st.pop();//얘도 끝)을 만났으니까 없애준다.
					answer += 1;//레이저 바로 다음이니까 |---, ---| <-얘 반으로끊긴애만 세어주기위해 +1임
				}
			}
		}
		return answer;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer;
		answer = solution(st.nextToken());
		System.out.println(answer);
	}

}

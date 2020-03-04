package exam;
import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Keyroker{
	 
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(reader.readLine());//테스트케이스
			
		while(t-- >0) {
			Stack l_stack = new Stack<>();
			Stack r_stack = new Stack<>();	
			
			//문자열을 받아옴
			StringBuilder str = new StringBuilder(reader.readLine());
			
			for(int i=0; i<str.length(); i++) {
				char c = str.charAt(i);//받아온 문자열을 char형으로 뜯는다
				switch(c) {
				case '<':
					if(!l_stack.isEmpty()) {	//null이면 false 즉, null이 아닐때
						r_stack.add(l_stack.pop());
					}
					break;
			
				case '>':
					if(!r_stack.isEmpty()) {
						l_stack.add(r_stack.pop());
					}
					break;
			
				case '-':
					if(!l_stack.isEmpty()) {
						l_stack.pop();
					}
					break;
				 default: //세가지 타입이 아니라면 여기
              l_stack.add(c); //여기 빼먹음 주의
				}
			}
			while(!l_stack.isEmpty())
				r_stack.add(l_stack.pop());
			while(!r_stack.isEmpty())
				System.out.print(r_stack.pop());
		}//while문 끝	
	}
}

import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Keyroker_deque{
	 
	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int t = Integer.parseInt(reader.readLine());//테스트케이스
		//문자열을 받아옴
		
		
		while(t-- >0) {
			String s = reader.readLine();
			Deque<Character> left = new ArrayDeque<>();
			//커서가 이 두개의 덱 사이에 있다고 생각하면 됨
			Deque<Character> right = new ArrayDeque<>();
	
			for(int i=0; i<s.length(); i++) {
				char c = s.charAt(i);
				
				switch(c) {
				case '<':
					if(!left.isEmpty()) {	//null이면 false 즉, null이 아닐때
						right.addFirst(left.pollLast());
						//즉 왼쪽덱에있는 문자를 오른쪽 덱첫번째로 옮겨놓는다는 뜻
					}
					break;
			
				case '>':
					if(!right.isEmpty()) {
						left.addLast(right.pollFirst());
					}
					break;
			
				case '-':
					if(!left.isEmpty()) {
						left.pollLast();
					}
					break;
				 default:
                     left.add(c);
				}
			}
            /*오른쪽 왼쪽 다 덱이 비워져야 원했던 패스워드를 얻을 수 있으므로 두개의 덱 모두 while문을 돌려주어야 함*/
            
			//왼쪽 덱이 null이 아닐때 까지, 스트링빌더에 왼쪽덱을 앞에서부터 꺼내서 이어붙여라
			while (!left.isEmpty()) sb.append(left.pollFirst());
			//오른족 덱이 null이 아닐때 까지, 스트링빌더에 오른족 덱을 앞에서 부터 꺼내 이어붙여라
            while (!right.isEmpty()) sb.append(right.pollFirst());
            
            sb.append("\n"); //테스트케이스마다 한줄씩 띄워야 하므로
        }
        System.out.println(sb);
	}
}

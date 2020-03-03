import java.util.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Editor{
	
	public static void main(String args[]) throws IOException {
	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	StringBuilder str = new StringBuilder(reader.readLine());
	int n = Integer.parseInt(reader.readLine());
	
	Stack lefts = new Stack<>();
	Stack rights = new Stack<>();
	for(int i=0; i<str.length(); i++) {
		lefts.add(str.charAt(i)); //왼쪽방향 스택에 받은 문자열을 다 넣어준다
	}
	
	while(n-- > 0) {
		String ed = reader.readLine();//명령을 하나씩 받음
		
		if(ed.contains("P")) {//P일때
			lefts.add(ed.charAt(2));//왼쪽스택의 두번째에 넣어줌
		}else {
			switch(ed) {
			case "L":
				if(!lefts.isEmpty())//왼쪽스택이 비어있지 않다면
					rights.add(lefts.pop()); //오른족 스택에 왼쪽 젤 위에껄 넣어라
			break;
			case "D":
				if(!rights.isEmpty())//오른쪽 스택이 비어있지 않다면
					lefts.add(rights.pop());//왼쪽스택에 오른쪽 젤 위에껄 넣어라
			break;
			case "B":
				if(!lefts.isEmpty())//왼쪽 스택이 비어있지 않다면
					lefts.pop();//왼쪽 스택의 젤 위에껄 삭제해라
			break;
			}
		}
		
	}//while문 끝
	while(!lefts.isEmpty())//왼쪽 스택이 비어있지 않을때 까지
		rights.add(lefts.pop());//오른쪽 스택에 왼쪽꺼를 넣어라
	while(!rights.isEmpty())
		System.out.print(rights.pop());

	
	}//main함수 끝
}

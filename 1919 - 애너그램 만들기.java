package exam;
import java.util.*;
import java.io.*;


public class solution{

	public static void main(String args[]) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		String s1 = reader.readLine();
		String s2 = reader.readLine();
		
		boolean[] b_check = new boolean[s2.length()];
		
		boolean c = false; //해당 배열원소가 몇번 check를 T로 바꿨는지 확인하기 윈한 변수
		int cnt =0;
		
		char[] a = new char[s1.length()];
		char[] b = new char[s2.length()];
		
		//배열에 원소를 넣기위한 과정
		for(int i=0; i<s1.length(); i++) a[i] = s1.charAt(i);
		
		for(int i=0; i< s2.length(); i++) b[i] = s2.charAt(i); //여기 때문에 틀린거였음=>s1이랑s2따로 배열해줘야됐는데..
		
		
		/*--------------------탐색 시작----------------------*/
		for(int i=0; i<s1.length(); i++) {
			for(int j=0; j<s2.length(); j++) {
				if(a[i] == b[j] && b_check[j] == false) {
					b_check[j] = true;//한번 들려서 체크를 했다는 것을 표시해주고
					//c = true;//이미 a[i]번째 배열이 b[j]번재 원소를 확인했다는것을 의미함
					break;//i의 숫자를 바꿔주기 위한 두번재 for문 나가도록 하는 것
				}
			}
		}//for문 끝
		
		
		for(int i=0; i< b_check.length; i++) {
			if(b_check[i] == true) {
				cnt += 1;//겹치는 문자의 갯수
			}
		}
		System.out.println(a.length+b.length - (cnt*2));
	}
}

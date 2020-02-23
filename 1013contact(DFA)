package exam;
import java.util.*;


public class Contact{
	 
	final int[][] dfa = {
			{5,1},
			{2,9},
			{3,9},
			{3,4},
			{5,7},
			{9,6},
			{5,1},
			{8,7},
			{3,6},
			{9,9}
	};
	
	
	public static void main(String args[]){
		
	Scanner sc = new Scanner(System.in);
	int[][] dfa = new int[10][2];
	int t = sc.nextInt(); //테스트케이스 받기
	sc.nextLine();
	int state =0;
	boolean ans =true;
	//ArrayList<String>[] a = (ArrayList<String>[]) new ArrayList[9];
	for(int i=0; i<t; i++) {//테스트케이스 돌동안
		System.out.println("입력해");
		String str = sc.nextLine();//사용자한테 string을 받고
		//System.out.println(str);
		//a[i].add(str); //그걸 배열에 넣어준다
		
		for(int j=0; j<str.length(); i++) {
			if(dfa[state][str.charAt(j)-'0'] == 4 || 
					dfa[state][str.charAt(j)-'0'] == 6 ||
					dfa[state][str.charAt(j)-'0'] == 7) {
				//ans = true;
				System.out.println("YES");
				//System.exit(0);
			}else {
				//ans = false;
				System.out.println("NO");
				//System.exit(0);
		}
			}
		//System.out.println(ans);
	}
		
	}
}

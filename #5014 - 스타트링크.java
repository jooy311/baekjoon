package exam;

import java.util.*;
import java.lang.*;
import java.io.*;
//dfs를 이용하지만 방향을 계속 체크하는게 아니라 가는길에 간선이 있는지 없는지 기준으로
//다음 지점이 f보다 

class Main {
	static boolean[] check;
	static boolean flag = false;
	static boolean ok = false;
	static int f,s,g,u,d;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		f = Integer.parseInt(st.nextToken());//건물의 꼭대기층
		s = Integer.parseInt(st.nextToken());//강호가 있는 층
		g = Integer.parseInt(st.nextToken());//스타트링크가 있는 층
		u = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		check = new boolean[f+1];
		
		dfs(s,0);
		if(ok == false)
			System.out.println("use the stairs");
	}
	
	public static void dfs(int a, int depth) {
		if(ok) return;
		int nx = a + u;	
	
		if(nx > g) {
			//flag = true;
			nx = a - d;
		}
	//System.out.println("depth : " + depth + " a : " + a +" nx : "+ nx + " flag : "+ flag);
		
		if(a == g) {
			ok = true;
			System.out.println(depth);
			return;
		}
		
		for(int i=a; i<=f; i++) {
			if(check[nx] == false) {
				check[nx] = true;
				dfs(nx, depth + 1);//depth가 결국 버튼 누른 횟수가 됨
				check[nx] = false;//백트래킹
				//flag = false;
			}
		}
	}
}

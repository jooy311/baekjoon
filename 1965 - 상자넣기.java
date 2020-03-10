package exam;

import java.util.*;
import java.io.*;

/*
 * 어떻게 상자를 골라야 최대로 박스를 많이 넣을 수 있느냐가 문제임
 * https://www.acmicpc.net/problem/1965
 */

public class solution2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//상자의 개수
		
		int[] box = new int[n];
		int[] dp = new int[n];
		
		for(int i=0; i<n; i++) {
			dp[i] = 1;//초기값을 1로 세팅
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0; i<n; i++) {
			box[i] = Integer.parseInt(st.nextToken());
		}
		//Arrays.sort(box); //상자를 재배열하면 안됨. 주어진 순서대로 박스를 고정해둬야함
		
		int cnt = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<i; j++) {//j가 i만큼만 돌아야됨 -> 즉 지금 차례 상자 바로 전까지만 서로 비교를 하도록.
			if(box[i] > box[j] && dp[i] < dp[j] + 1) {
				  dp[i] = dp[j] + 1;
				}
			}
			if(cnt < dp[i])  cnt = dp[i];
		}
		System.out.println(cnt);
	}
}

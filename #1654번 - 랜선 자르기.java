package exam;

import java.util.*;
import java.io.*;

public class solution {
	static long max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		long n = Long.parseLong(st.nextToken());
		
		//System.out.println("min " + min);

		//꼭 모든 랜선을 사용하지 않아도됨...->그래서 틀림
		long[] youngsik = new long[k]; // 영식이가 가지고 있는 랜선의 개수
		for (int i = 0; i < youngsik.length; i++) {
			st = new StringTokenizer(br.readLine());
			youngsik[i] = Long.parseLong(st.nextToken());
		}
		Arrays.sort(youngsik);
		if(n/k > 0) {
			long min = n / k;// 가져야 하는 최소개수
			count_landsun(youngsik,0, min, n, youngsik[0] / min );
		}
		else {
			count_landsun(youngsik,0, min, n, youngsik[0] / min );//다시한번해보기..<-<-<--------------------------
		}
		
		System.out.println(max);
		
	}
	
	public static void count_landsun(long[] youngsik, int start,long min, long n, long min_len) {
		//long min_len = youngsik[0] / min;
		System.out.println("가장 작은숫자 % 최소개수 : " + min_len);
		if(min_len == 0)
			min_len = youngsik[start+1]/min;
		long cnt = min;
		if(start == youngsik.length-1)
			return;
		for (int i = 1; i < youngsik.length-1; i++) {
			cnt = cnt + youngsik[i] / min_len;
			System.out.println("cnt " + cnt);
		}
		if (cnt + youngsik[youngsik.length-1]/min_len < n) {
			// 길이를 더 줄여야함
			long tmp = n - cnt;
			System.out.println("tmp : "+tmp);
			min_len = youngsik[youngsik.length - 1] / tmp;
		}else {//다 던한게 필요한 랜선수보다 넘을때 -> 조금 더 긴 길이로 가능할 수 도 있단얘기
			count_landsun(youngsik, start+1 ,min, n, youngsik[start+1]/min);
		}
		if(max < min_len) max = min_len;		
	}
}// class끝

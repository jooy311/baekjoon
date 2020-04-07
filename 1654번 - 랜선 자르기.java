import java.util.*;
import java.io.*;

public class solution3 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int k = Integer.parseInt(st.nextToken());
		int n = Integer.parseInt(st.nextToken());
		//꼭 모든 랜선을 사용하지 않아도됨...->그래서 틀림
		int[] youngsik = new int[k]; // 영식이가 가지고 있는 랜선의 개수
		for (int i = 0; i < k; i++) {
			st = new StringTokenizer(br.readLine());
			youngsik[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(youngsik);
		long min = 1;
		long max = youngsik[youngsik.length-1];
		long mid = 0; long sum; //int tmp=0;
		while(min <= max) {
			sum = 0;
			mid = (min + max) / 2;
			for(int i=0; i<k; i++) {
				sum += youngsik[i] / mid;
			}
			if(sum < n)
				max = mid -1;
			else {
				min = mid +1;
			}
		}
		System.out.println(max);
	}
}// class끝

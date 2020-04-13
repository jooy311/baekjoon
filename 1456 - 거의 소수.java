import java.util.*;
import java.lang.*;
import java.io.*;

class solution2 {
	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		long start = Long.parseLong(st.nextToken());// 시작점
		long end = Long.parseLong(st.nextToken());// 끝점
		long cnt = 0;
		long[] arr = new long[(int)1e+7 + 1];
		long idx = 0;
		boolean[] pr = new boolean[(int)1e+7 + 1];//100000000000000+1
	
		// 에레테네스의체
		for (long i = 2; i*i <= end; i++) {//제곱이상부터 검사하면 되기때문에 i*i로 검사함
			if (pr[(int) i] == false) {// 소수인애들
				for (long j = i + i; j*j <= end; j += i)
					pr[(int) j] = true;// 소수가 아닌애들
				arr[(int) idx++] = i;//소수인애들을 arr배열에 넣어준다
			}
		}

		for (int i = 0; i < idx; i++) {
			long num = arr[i];
			while (arr[i] <= end / num) {// end를 그대로 받아오면 long 완전 넘겨버리기 때문에 num으로 나눠준다.
				if (arr[i] * num >= start) {//num을 곱한게 start보다 크다면
					cnt++;
				}
				num *= arr[i];
			}
		}
		System.out.println(cnt);
	}
}

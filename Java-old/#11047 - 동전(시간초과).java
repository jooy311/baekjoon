import java.util.*;
import java.io.*;

class Pair {
	int cnt;// 어떤 동전 사용
	int mon;// 남은돈

	public Pair(int cnt, int mon) {
		this.cnt = cnt;
		this.mon = mon;
	}
}

public class Main {

	static int ans = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 부등호 개수
		int k = Integer.parseInt(st.nextToken()); // 동전 총 합

		int[] arr = new int[n];// 동전 종류 받기

		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			arr[i] = Integer.parseInt(st.nextToken());
		}
		Pair[] p = new Pair[n];
		int temp = k;
		while (temp > 0) {
			temp = greedy(arr,temp, p);
			if(temp == 0)
				break;
		}

		System.out.println(ans);
	}

	public static int greedy(int[] arr, int temp, Pair[] p) {

		for (int i = 0; i < arr.length; i++) {
			p[i] = new Pair(clac(temp, arr[i]), temp - (arr[i] * clac(temp, arr[i])));
		}

		int min_cnt = temp;
		for (int i = 0; i < arr.length; i++) {
			if (min_cnt > p[i].cnt && p[i].cnt != 0) {
				min_cnt = p[i].cnt;// 가장 적은 횟수를 고른다
				temp = p[i].mon;
			}
		}
		ans += min_cnt;// 최소 동전개수를 구하기 위한 변수
		return temp;
	}

	public static int clac(int k, int coin) {// 몇개 사용하는지 구하는 함수
		int result = k;
		int cnt = 0;

		while (result > 0) {
			result = result - coin;
			if (result < 0)
				break;
			cnt++;
		}
		return cnt;
	}
}

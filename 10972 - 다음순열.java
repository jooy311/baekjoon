import java.util.*;
import java.io.*;

public class solution2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());// 정점의 개수
		int[] a = new int[n];
		boolean[] check = new boolean[n + 1]; // 첫번째 자리부터 1234...가 사용됐는지 아닌지 체크하기 위해

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			a[i] = Integer.parseInt(st.nextToken());
		}

		// 뒤에서 부터 내림차순끊기는 지점 찾기
		int start = n - 1;// 뒤에서부터
		while (start > 0 && a[start - 1] > a[start]) // 끊지는 지점보다 그 뒤에서구 더 커야함
			start--;// start를 하나 줄인다

		if (start == 0) {// 이미 제일 끝에 있는 수열이라
			System.out.println("-1"); // -1출력
		} else if (start > 0) {

			int near = 10001;// 일단 나올수 있는 수중에 가장 큰수를 셋팅
			int index = 0;
			for (int j = start; j < n; j++) {// 끊기는 지점부터 돌기 시작함
				if (start != 0 && a[start - 1] < a[j] && a[j] < near) {
					// 끊기는 지점 앞 < 끊기는 지점 && 끊기는 지점 < 근처에서 가장 큰수
					near = a[j];// near에 끊기는 지점을 넣음
					index = j;// 끊지는 그 지점의 인덱스를 설정
				}
			}
			int tmp = a[start - 1];
			a[start - 1] = a[index];
			a[index] = tmp;
			 Arrays.sort(a, start, n);
			for (int i = 0; i < n; i++) {
				System.out.print(a[i] + " ");
			}
		}
	}
}

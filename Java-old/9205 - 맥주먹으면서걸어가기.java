import java.util.*;
import java.math.*;
import java.lang.*;
import java.io.*;
//문제점
//이차원배열을 꼭 x,y로 양분해서 받을 필요가 없구나.. 활용도를 더 높이는 방법을 깨달음

class Main {
	static int[][] arr;// 서로서로의 거리
	static boolean[] check;// 각 지점을 방문했는지의 여부
	static int[][] pos;// 각 지점의 x y좌표 저장
	static int n;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());// 테스트케이스

		while (t-- > 0) {
			arr = new int[102][102];// 편의점 최대 100개와 상근이네, 목적지 까지 총 102개
			check = new boolean[102];
			pos = new int[102][2];

			st = new StringTokenizer(br.readLine());
			n = Integer.parseInt(st.nextToken());// 맥주를 파는 편의점개수

			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			pos[0][0] = x;
			pos[0][1] = y;// 상근이네 위치 저장

			for (int i = 1; i <= n; i++) {// 편의점 위치 저장
				st = new StringTokenizer(br.readLine());
				int xx = Integer.parseInt(st.nextToken());
				int yy = Integer.parseInt(st.nextToken());
				pos[i][0] = xx;
				pos[i][1] = yy;
			}

			st = new StringTokenizer(br.readLine());
			int xxx = Integer.parseInt(st.nextToken());
			int yyy = Integer.parseInt(st.nextToken());
			pos[n + 1][0] = xxx;
			pos[n + 1][1] = yyy;// 목적지 위치 저장

			for (int i = 0; i < n + 2; i++) {
				for (int j = i + 1; j < n + 2; j++) {
					arr[j][i] = Math.abs(pos[i][0] - pos[j][0]) + Math.abs(pos[i][1] - pos[j][1]);// 현재지점과 다음지점 차이 구해서
					arr[i][j] = arr[j][i]; // 저장
				}
			}

			go(0);// 상근이네 부터 들리기 시작
			if (check[n + 1] == true)
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}

	public static void go(int a) {
		check[a] = true;// 들렸으니까 트루
		for (int i = 1; i <= n + 1; i++) {
			if (check[i] == false) {
				if (arr[a][i] <= 1000)// 맥주는 최대 20개 -> 50 * 20 = 1000 이상은 못걸어감
					go(i);// 1000이상이되면 결국 n+1 이전에 for문이 끝날것임 = 목적지까지 못갔다.
			}
		}
	}
}

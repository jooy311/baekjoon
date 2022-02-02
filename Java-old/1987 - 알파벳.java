import java.util.*;
import java.io.*;

public class solution {
	static int r, c;
	static char[][] map;
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static boolean[] check;
	static int max = 0;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());

		map = new char[r][c];
		check = new boolean[26];//하..첨에 check[][]도 이런 2차배열로 사용해서 엄청 꼬임(확인도 여러번해야되고)

		for (int i = 0; i < r; i++) {
			String str = br.readLine();
			for (int j = 0; j < c; j++)
				map[i][j] =str.charAt(j);//이것도 첨에 String으로 받아와서 check를 아스키코드를 변화하는게 어려워서 헤멤
		}

		check[(int) map[0][0] - 65] = true;
		dfs(0, 0, 1); // 항상 0,0에서 시작
		System.out.println(max);
	}

	public static void dfs(int a, int b, int len) {// 최대로 나올수 있는건 c개의 알파벳
		int end = 0;

		for (int k = 0; k < 4; k++) {
			int nx = a + dx[k];
			int ny = b + dy[k];
			if (nx >= 0 && ny >= 0 && nx < r && ny < c) {
				if (check[(int) map[nx][ny]- 65] == false) {
					check[(int) map[nx][ny] - 65] = true;
					dfs(nx, ny, len + 1);
					check[(int) map[nx][ny] - 65] = false;//백트래킹!!!!부모노드로 다시 돌아갈 수 있도록 false처리를 해준다.

				} else 
					  end++;//상하좌우 움직였는데 true라 못갔다면 end값 올려주기
			}else
				end++;//판 범위 밖을 벗어났다면 end값 올려주기
			if (end == 4) {//이렇게해서 상하좌우 다 막혀있다면
				if (max < len) {//최장길이(dfs의 깊이=차수) 를 갱신함
					max = len;
				}
			}
		}
		len--;//부모노드로 돌아가기 때문에 올라간 차수를 다시 하나 줄여준다
	}
}

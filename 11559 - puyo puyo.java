import java.util.*;
import java.lang.*;
import java.io.*;

class Pair {
	int x;
	int y;

	public Pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

class solution2 {
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		String[][] map = new String[12][6];// 뿌요뿌요 맵

		for (int i = 0; i < 12; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().split("");
		}
		// ----------------------------------
		int ans = 0;
		while (true) {
			boolean[][] visited = new boolean[12][6];
			boolean flag = false;
			
			for (int i = 0; i < 12; i++) {//맵을 돌면서
				for (int j = 0; j < 6; j++) {
					if (!map[i][j].equals(".") && visited[i][j] == false) {//빈공간이면서 간적이없는 칸
						Stack<Pair> s = new Stack<Pair>();//터질수있는(4개이상)블럭의 좌표를 받는다
						dfs(i, j, map[i][j], map, visited, s);//dfs돌게하고
            
						if (s.size() >= 4) {// 스택의 사이즈가 4이상이면
							flag = true;
							while (!s.isEmpty()) {// 스택이 빌때까지
								Pair p = s.pop();
								int x = p.x;
								int y = p.y;
								map[x][y] = ".";// 맵을돌면서 .로바꿔준다
								visited[x][y] = false;//빈공간으로 바꿔주면서 들렸던 공간이 아니게 만들어줌
							}
						}
					}
				}
			}
			if(flag == false)//이경우는 맵에 이제 하나도 터질게 없는 경우
				break;//while문을 나가도록 함
			else//하나이상 터지는게 있다면
				ans++;//연쇄작용을 하나 카운트
			
			is_empty(map);//여기를 왔다는것은, 터진 블록이 있다는 것이므로 맵을 다시  
		}

		System.out.println(ans);
	}

	public static void dfs(int a, int b, String str, String[][] map, boolean[][] visited, Stack<Pair> s) {
		// if(s.size() >)//여기서 탈출조건을 만들어주면 5개이상인데 4개부터 걸려서 나오는 경우가 생길 수 있음
		for (int k = 0; k < 4; k++) {
			int nx = a + dx[k];
			int ny = b + dy[k];
			if (nx >= 0 && ny >= 0 && nx < 12 && ny < 6) {
				// if (bomb < 4) {// 이거 할필요없나...?
				if (visited[nx][ny] == false && map[nx][ny].equals(str)) {
					// 방문한적이 없고, 시작했던 지점이랑 색이 같다면
					visited[nx][ny] = true;// 방문했음을 체크
					s.push(new Pair(nx, ny));
					dfs(nx, ny, str, map, visited, s);
				}

			}
		}

	}

	public static void is_empty(String[][] map) {// 내밑에 빈공간인지 확인하는 함수

		for (int i = 0; i < 6; i++) {
			for (int j = 10; j >= 0; j--) {
				for (int k = 11; k > j; k--) {
					// if (visited[a][b] == false && map[a][b].equals(".")) {}
					if (map[k][i].equals(".") && !map[j][i].equals(".")) {
						map[k][i] = map[j][i];
						map[j][i] = ".";
						break;
					}
				}
			}
		}
	}
}

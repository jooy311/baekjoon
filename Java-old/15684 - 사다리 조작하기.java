package exam;
import java.util.*;
import java.lang.*;
import java.io.*;
//dfs를 이용하지만 방향을 계속 체크하는게 아니라 가는길에 간선이 있는지 없는지 기준으로

class Main {
	static int[][] check;
	static boolean flag = false;
	static int max;
	static int n;
	static int m;
	static int h;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 세로선의 개수
		m = Integer.parseInt(st.nextToken());// 가로선의 개수
		h = Integer.parseInt(st.nextToken());// 세로선마다 가로선을 놓을 수 있는 위치개수
		check = new int[h + 1][n + 1];

		int tmp = m;
		while (tmp-- > 0) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());// 행의 위치(몇번째 가로선?0
			int y = Integer.parseInt(st.nextToken());
			check[x][y] = 1;// 1이면 동그라미(가능)
			check[x][y + 1] = 2; //끝나는 지점을 2로 지정
		}
		
		for (int i = 0; i <= 3; i++) {
			max = i;//케이스가 어차피 0,1,2,3인 경우밖에 없으므로 나올 수 있는 결과값을 설정하고 들어간다
			dfs(1, 0);
			if (flag == true)//이미 모든 사다리가 자기자신으로 돌아오는 경우이므로
				break;//break건다
		}
		
		if (flag == true)
			System.out.println(max);
		else
			System.out.println(-1);//flase로 for문을 나왔다면 안되는 경우이므로 -1출력
	}

	public static void dfs(int a, int depth) {//가로선위치 시작점과 depth만 매개변수로
		if (flag==true)//true면 모든 사다리가 자기자신으로 돌아오는 경우이므로 리턴
			return;

		if (depth == max) {//정해놓은 최대값만큼 재귀를 돌았는데
			if (istrue())//체크를 해보니 값이 true가 나왔다 = 각 사다리가 자기자신으로 리턴이 됐다
				flag = true;//flage를 true로 바꿔줌
			return;
		}

		for (int i = a; i <= h; i++) {
			for (int k = 1; k < n; k++) {
				if (check[i][k] == 0 && check[i][k+1] == 0) {//한번도 들린적없는 곳이라면
					check[i][k] = 1;//간선 시작점 추가
					check[i][k+1] = 2;//간선 마지막점 추가
					dfs(i, depth + 1);
					check[i][k] = 0;//안되는 경우이므로 백트래킹해서 다시 0으로 만든다
					check[i][k+1] = 0;
					//즉, 간선을 추가해서 내려가봤는데 flag가 계속 false이니까 안되는 경우이므로 0으로 다시 리셋
				}
			}

		}
	}
	public static boolean istrue() {
		for(int i=1; i<=n; i++) {
			int line = 1;//가로선
      int row = i;//사다리
			for(int j=0; j<h; j++) {/h번만큼 for문 돌아감
				if(check[line][row] == 1) row++;//간선의 시작점이면 사다리를 오른쪽으로 옮겨간다- 간선이 있다면 바로 아래 else문으로 내려감
				else if(check[line][row] == 2) row--;//간선의 끝점이면 사다리를 왼쪽으로 옮겨간다
				line++;//다음 가로선 검사
			}
			if(row != i) return false; //만약 자기자신으로 돌아오는 사다리타기라면 row값은 i값이랑 
		}//최종for문 종료
		return true;
	}
}

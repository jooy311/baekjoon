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
}//x,y한번에 저장할수 있는 클래스

class Main
{
	static int[] dx = { 0, 0, -1, 1 };
	static int[] dy = { -1, 1, 0, 0 };
	static int w;
	static int h;
	static String[][] map;
	static ArrayList<Pair> arr;//로봇의 위치를 저장하는 어레이
	static boolean[][] visited;
	static int[][] dump_dist;
	static int[][] dist;
	
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		while (true) { 
			
			st = new StringTokenizer(br.readLine());
			w = Integer.parseInt(st.nextToken());// 가로(렬)
			h = Integer.parseInt(st.nextToken());// 세로(행)
			
			if( w==0 && h == 0) break;
			map = new String[h][w];
			arr = new ArrayList<Pair>();// 쓰레기 위치를 저장하기위한 큐
			dump_dist = new int[12][12];
			int dump = 0;
			int x = 0, y = 0;
			boolean flag = true;
			
			//맵을 입력받는다
			for (int i = 0; i < h; i++) {
				st = new StringTokenizer(br.readLine());
				map[i] = st.nextToken().split("");
			}
			

			for (int i = 0; i < h; i++) {
				for (int j = 0; j < w; j++) {
					if (map[i][j].equals("o")) {
						x = i;
						y = j;// 로봇위치 일단 저장
					}
					if (map[i][j].equals("*")) {
						dump++;// 쓰레기 갯수파악
						arr.add(new Pair(i, j));//쓰레기 위치 저장
					}	
				}
			}
			
			for(int i = 0; i< dump; i++) {
				int dump_x = arr.get(i).x;
				int dump_y = arr.get(i).y;
				
				int tmp = bfs(x, y, dump_x, dump_y);//로봇과 각 쓰레기 간의 거리를 받아오게 됨
				//System.out.println(tmp);
				
				if(tmp == 0) {//tmp==0된다는것은 로봇이 쓰레기 위치에 갔다는 것.
					flag = false;
					break;//로봇이 쓰레기 위치로 갔다는 것이니까 이제그만~~
				}else {
					dump_dist[11][i] = tmp;//로봇과 1번째 쓰레기 거리, 2번재 쓰레기...를 차례로 저장
				}
				
				for(int j = i+1; j < dump; j++) {
					if( i != j) {
						int a = arr.get(i).x;//1번쓰레기 좌표
						int b = arr.get(i).y;
						int na = arr.get(j).x;//2번쓰레기 좌표
						int nb = arr.get(j).y;
						
						int tmp2 = bfs(a,b,na,nb);
						
						dump_dist[i][j] = tmp2;
						dump_dist[j][i] = tmp2;
						
					}
				}
			}
			
			//순열
			//ArrayList<Integer> seq = new ArrayList<Integer>();
			int[] seq = new int[dump];
			for(int i=0; i< dump; i++)
				seq[i] = i;
				//seq.add(i);
			int min = -1;
			
			do {
				int a = x;
				int b = y;
				
				int ans = 0;
				if(flag == false || dump == 0)
					break;
				
				ans += dump_dist[11][seq[0]];
				
				for(int i=0; i < dump -1; i++) {
					ans += dump_dist[seq[i]][seq[i+1]];
				}
				
				if(min > ans || min == -1) {
					min = ans;
				}
				
			}while(next_permutation(seq));
			
			if(dump == 0) {
				System.out.println("0");
			}else {
				if(flag == false || min == 0) {
					System.out.println("-1");
				}else
					System.out.println(min);
			}
		}
	}
	
	public static int bfs(int a, int b, int d_x, int d_y) {
		Queue<Pair> q = new LinkedList<Pair>();
		visited = new boolean[h][w];
		dist = new int[h][w];
		
		q.add(new Pair(a,b));
		visited[a][b] = true;//방문체크
		while(!q.isEmpty()) {
			Pair p = q.poll();
			int x = p.x;
			int y = p.y;
			for(int k = 0; k< 4; k++) {
				int nx = x + dx[k];
				int ny = y + dy[k];
				if(nx >= 0 && ny >= 0 && nx < h && ny < w) {
					if(visited[nx][ny] == false && !map[nx][ny].equals("x")) {
						visited[nx][ny] = true;
						dist[nx][ny] = dist[x][y] + 1; //a,b를 기준점으로 해서 거리차이를 구한다.
						q.add(new Pair(nx, ny));
					}
				}
			}
		}
		return dist[d_x][d_y];//시작지점 a,b에서 목표지점 d_x, d_y거리를 return한ㄴ다
	}
	
	public static boolean next_permutation(int[] seq) {
		int i = seq.length -1;
		while( i> 0 &&  seq[i-1] >= seq[i]) {
			i -= 1;
		}
		
		if(i <= 0) return false;
		
		int j = seq.length -1;
		while(seq[j] <= seq[i-1]) j -= 1;
		
		int tmp = seq[i-1];
		seq[i-1] = seq[j];
		seq[j] = tmp;
		
		j = seq.length -1;
		while(i <j) {
			tmp = seq[i];
			seq[i] = seq[j];
			seq[j] = tmp;
			i += 1;
			j -= 1;
		}
		return true;
	}
}

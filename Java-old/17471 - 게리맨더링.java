import java.util.*;
import java.lang.*;
import java.io.*;
//1.각 지역의 인구수는 어떻게 저장해야하는가
//2.주어지는 간선정보는 어떻게 저장해야 하는가?
//1..선거구로 나누는 방법으로 어떤 알고리즘을 사용해야하나? (탐색기법은 맞는데 어떻게?)
//빨간색을 먼저 정하고,
//파란색이 연결이 되는지 확인해야하나?
//모든 경우의수를 확인하고 -> 그게 연결되어있는지 확인하면 됨
//c++의 경우 next_permutation이용하면 되지만 자바는 없기 때문에 dfs->bfs를 이용하도록한다.

class solution2 {
	static int n;// 구역의 개수
	static int k,sum, tmp_sum;
	static int[] people;// 사람인구수를 받는다
	static boolean[] check;
	static boolean[] visited;
	static int[] temp;
	static ArrayList<Integer>[] nearby;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());

		// 인구수 저장
		people = new int[n + 1];
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= n; i++) {
			people[i] = Integer.parseInt(st.nextToken());
		}

		// 간선 저장
		nearby = (ArrayList<Integer>[]) new ArrayList[n + 1];// 간선저장
		for (int i = 1; i <= n; i++) {
			nearby[i] = new ArrayList<Integer>();
		}

		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			int near = Integer.parseInt(st.nextToken());// 개수
			for (int j = 1; j <= near; j++) {
				int tmp = Integer.parseInt(st.nextToken());
				nearby[i].add(tmp);
			}
		}
      //조합을 구한다. -> 6C4 = 6C2이므로 n/2만 확인
		for(int i=1; i<=n/2; i++) {
			k=i;//깊이가 1일때,2일때, 3일때(즉,지역이 6개일때 최대깊이가 3임)
         //5개가 선택될수있지 않나? -> 블루쪽에서 보면 깊이가 1인거임
			visited = new boolean[n + 1];
			dfs(1,0);//1부터 시작
		}
		
		if (min == Integer.MAX_VALUE)
			System.out.println(-1);
		else
			System.out.println(min); // 인구의 차이를 추렭
	}

	public static void dfs(int a, int depth) {// 1. 구역나누기
		if(depth == k) {//깊이가 k일때
			sum = Integer.MAX_VALUE;
			temp = new int[n+1];//빨간색이냐, 블루냐 체크하기위함
			for(int i=1; i<=n; i++) {
				if(visited[i] == true) {//들린적있는 지역이면
					temp[i] = 1;//빨간색
				}
				else {//들린적없으면
					temp[i] = 0;//블루
				}
			}
			totalsum();//빨간색과 블루의 인구 수를 계산해온다
			min = Math.min(min, sum);
			return;
		}
		for(int i=a; i<=n; i++) {//백트래킹으로 추적
			visited[i] = true;
			dfs(i+1, depth+1);//나 자신 다음부터 체크하기 위해 i+1
			visited[i] = false;
		}

	}
	public static void totalsum() {
		check = new boolean[n+1];
		int red = 0;
		int blue = 0;
		for(int i=1; i<=n; i++) {
			if(temp[i] == 1 && check[i] == false) {
				tmp_sum = 0;
				isConnect(i);
				red = tmp_sum;
				break;
			}
		}
		
		for(int i=1; i<=n; i++) {
			if(temp[i] == 0 && check[i] == false) {
				tmp_sum = 0;
				isConnect(i);//빨간색 지역 인구수를 다 더해온다
				blue = tmp_sum;
				break;
			}
		}
		for(int i=1; i<=n; i++) {
			if(check[i] == false) {
				return;
			}
		}
		//System.out.println(red + " , "+blue);
		sum = Math.abs(red - blue);
		//return sum;
	}

	public static void isConnect(int blue) {
		check[blue] = true;
		tmp_sum = tmp_sum + people[blue];
		for(int i=1; i<=n; i++) {
      //들린적없고              같은색끼리이며           너와내가 연결되어있다면
			if(check[i] == false && temp[i] == temp[blue] && nearby[i].contains(blue)==true) {
				isConnect(i);
			}
		}
		
	}
}

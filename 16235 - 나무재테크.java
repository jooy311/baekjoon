package exam;

import java.util.*;
import java.lang.*;
import java.io.*;
//문제점 해결
//The method sort(List<T>) in the type Collections is not applicable for the arguments (ArrayList<Pair>)
//위의 문제를 해결 하기 위해서는 Pair클래스에 Comparable을 구현해야함

//시간초과
//LinkedList사용할때 get을 이용하면 순차적으로 탐색하기 때문에 시간복잡도가 생김. literaror로 접근해야한다고 함
//덱은 배열기반이니, 그냥 데이터 넎고 인덱스 가감만 하면 빠르지만(즉, 앞뒤에서의 추가삭제가 빠른것임)
//리스트는ㅌ 메모리잡고 포인터 연결하고 작업을 해야하므로 시간이 더 걸릴수있음(즉, 얖뒤를 포함한 어느 위치에서든 추가 삭제가 빠름)

//계속 오류
//nx ny 범위값^^

class solution2 {
	static class Pair {
		int x, y;
		int z;
		boolean life;

		public Pair(int x, int y, int z, boolean life) {
			super();
			this.x = x;
			this.y = y;
			this.z = z;
			this.life = life;
		}
	}

	static int n;
	static int m;
	static int k;
	static LinkedList<Pair> tree;// 나무가 심어지는 리스트 -> 이거를 리스트로 하지말고 덱으로
	static LinkedList<Pair> arr;
	static int[][] a;// 로봇이 매년 겨울에 흩뿌리는 양분을 받는 배열
	static int[][] feed;// 매년 양분이 얼마나 남아있는지 체크하기 위한 배열
	static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
	static int[] dy = { -1, 1, 0, 0, -1, 1, 1, -1 };// 8방향응로 해야함

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());// 땅의 크기
		m = Integer.parseInt(st.nextToken());// m개의 나무
		k = Integer.parseInt(st.nextToken());// k년이 지난후
		
		feed = new int[n + 1][n + 1];
		a = new int[n + 1][n + 1];// 로봇이 계속 추가하는 양분(입력받기위함)
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= n; j++) {
				feed[i][j] = 5;// 5로 초기셋팅
				a[i][j] = Integer.parseInt(st.nextToken());// 겨울마다 계속 얘만큼 추가됨
			}
		}
		
		tree = new LinkedList<Pair>();// 심어지는 나무를 받기 위한 리스트 생성
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());// 나이
			tree.add(new Pair(x, y, z, true));
		}
		
		count_tree();
	}

	public static void count_tree() {
		while (k-- > 0) {
			spring();
			summer();
			fall();
			winter();
		}
		System.out.println(tree.size());// 최종적으로 심어져있는 나무의 개수 = 리스트의 사이즈
	}

	public static void spring() {
		// 만약 나무가 한 위치에 여러개라면 나이가 적은애부터 양분을 먹으면됨-
		for (Pair p : tree) {
			// 심어진 나무의 나이 <= 깔려진 양분이 더 많다면-> 해당 나무 나이만큼 양분을 먹고, 나무나이+1
			if (p.z <= feed[p.x][p.y]) {
				feed[p.x][p.y] = feed[p.x][p.y] - p.z;
				p.z += 1;
			} else {// 없으면, 죽음->죽은나무 카운트+1
				p.life = false;// 죽었음
			}
		}
	}

	public static void summer() {
		for (Iterator<Pair> itt = tree.iterator(); itt.hasNext();) {
			Pair p = itt.next();
			//System.out.println(p.x);
			if (p.life == false) { //죽은 나무라면
				//System.out.println(p.x);
				feed[p.x][p.y] += (p.z / 2);//양분이됨
				itt.remove();
			}
		}
	}

	public static void fall() {
		// 5의 배수의 나이를 가진 나무가 있는지 확인
		arr =  new LinkedList<Pair>();
		for (Pair p : tree) {
			if (p.z % 5 == 0) {
				for (int k = 0; k < 8; k++) {
					int nx = p.x + dx[k];
					int ny = p.y + dy[k];
					// 땅의 크기는 1부터 시작이므로 좌표0은 들어갈 수 없음 - 범위설정을해야함
					if (nx > 0 && ny > 0 && nx <= n && ny <= n) {
						arr.add(new Pair(nx, ny, 1, true));
					}
				}
			}
		}
		tree.addAll(0, arr);//새로 파생되는 어린나무를 기존 나무 리스트 앞에 붙여서 어린나무부터 먹을 수 있도록 설정
	}

	public static void winter() {
		// 그냥 무조건 양분추가 a와 feed를 행렬에 맞게 더해주면됨
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				feed[i][j] = feed[i][j] + a[i][j];
			}
		}
	}
}

import java.util.*;
import java.lang.*;
import java.io.*;

class Main {
   static class Pair {
      int x;
      int y;
      public Pair(int x, int y){
      	this.x = x;
      	this.y = y;
      }
   }

   static int[][] arr;
   static int n;
   static int m;
   static int t;
   static int[] dx = {0,0,-1,1};
   static int[] dy = {-1,1,0,0};

   public static void main(String[] args) throws java.lang.Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());// 반지름=원판이 몇개 있는지 알려줌
      m = Integer.parseInt(st.nextToken());// m몇개의 정수가 한 판에 적혀있는지
      t = Integer.parseInt(st.nextToken());// 회전하는 횟수
      // 원판을 t번 회전시킨 후 원판에 적힌 수의 합을 출력한다
      arr = new int[n + 1][m + 1];// 각원판에 적힌 수를 담기 위한 배열
      for (int i = 1; i <= n; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= n; j++) {
            arr[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      // 회전을 어떻게 시킬건지
      Queue<Integer> q = new LinkedList<Integer>();
      while (t-- > 0) {
         st = new StringTokenizer(br.readLine());
         int x = Integer.parseInt(st.nextToken());// 몇번째 배수의 원판을 돌리지
         int d = Integer.parseInt(st.nextToken());// 어느방향?
         int k = Integer.parseInt(st.nextToken());// 몇칸씩 돌릴건지=몇번돌릴건지
         for (int i = 1; i <= n; i++) {
            if (i % x == 0) {
               //System.out.println(i);
               q.add(i);// 몇번째 원판을 돌릴지 받는 큐
            }
         }

         while (!q.isEmpty()) {
            spin(q.poll(), d, k);// 해당 방향으로 k만큼 돌리고
         }
        // Queue<Pair> qq = new LinkedList<Pair>();
        
		nearby(1,1);
			
		
        // nearby();// 인접한것을 찾는다
         
      }//while문 끝
      
      for(int i=1; i<=n; i++) {
         for(int j=1; j<=n; j++) {
            System.out.print(arr[i][j]);
         }
         System.out.println();
      }
      
   }

   public static void spin(int wh, int dir, int k) {// 원판을 돌리는 함수->해당하는 판을 매개변수로 보내주면 dir매개변수 방향으로 돌려주면됨
      // 한번도는 것을 기준으로 만든다(1번 이상은 main함수에서 작성)
      Deque<Integer> dq = new LinkedList<Integer>();
      for (int i = 1; i <= m; i++) {
         //System.out.println(arr[wh][i]);
         dq.addFirst(arr[wh][i]);// 덱에 wh번째 원판에 있는 숫자를 넣는다
      }
      
      // 시계방향
      if (dir == 0) {
         while (k-- > 0) {
            dq.addLast(dq.pollFirst());
         }
         while (!dq.isEmpty()) {
            for (int i = 1; i <= n; i++) {
               arr[wh][i] = dq.pollLast();
               //System.out.print(dq.pollFirst());
            }
         }
      }
      // 반시계방향
      else if (dir == 1) {
         while (k-- > 0) {
            dq.addFirst(dq.pollLast());
         }
         while (!dq.isEmpty()) {
            for (int i = 1; i <= n; i++) {
               arr[wh][i] = dq.pollLast();
            }
         }
      }

   }

   public static void nearby(int a, int b) {//bfs로 인접한 같은 숫자 삭제
	Queue<Pair> q1 = new LinkedList<Pair>();
	boolean[][] check = new boolean[n+1][m+1];//같아서 삭제가 되면 true바꿔서 못들리게
	q1.add(new Pair(a,b));
	while(!q1.isEmpty()){
		Pair p = q1.poll();
		int x = p.x;
		int y = p.y;
		for(int k=0; k<4; k++){
			int nx = x + dx[k];
			int ny = y + dy[k];
			if(nx >0 && ny > 0  && nx <= n && ny <= m){
				if(arr[x][y] == arr[nx][ny]){
					arr[x][y] = 0;
					arr[nx][ny] = 0; //0으로 삭제 시켜준다
				}else
					q1.add(new Pair(nx,ny));
			}
		}
	}
   }

}

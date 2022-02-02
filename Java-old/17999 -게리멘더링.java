/* package whatever; // don't place package name! */
package exam;
import java.util.*;
import java.lang.*;
import java.io.*;

//세번째 테스트케이스가 자꾸 잘못나오는 이유
//아마 섹션5에서 중복된 지점까지 값을 더해서 잘못나오는 듯
//+ 범위 잘못설정
//+ 섹션 5 내부색칠을 안함
class solution2 {
   static int n;
   static int[][] map;
   static int[][] check;
   static int ans = Integer.MAX_VALUE;
   static int[] dx = { 0, 0, -1, 1 };
   static int[] dy = { -1, 1, 0, 0 };

   public static void main(String[] args) throws java.lang.Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());
      map = new int[n + 1][n + 1];

      for (int i = 1; i <= n; i++) {
         st = new StringTokenizer(br.readLine());
         for (int j = 1; j <= n; j++) {
            map[i][j] = Integer.parseInt(st.nextToken());
         }
      }
      
      for (int i = 1; i <= n; i++) {// x좌표
         for (int j = 1; j <= n; j++) {// y좌표 - x,y는 모든 좌표를 다 해봐야함
            for (int d1 = 1; d1 <= n; d1++) {// d1길이 ->범위가 어디까지인가?
               for (int d2 = 1; d2 <= n; d2++) {// d2길이
                  if (i + d1 + d2 <= n && 1 <= j - d1 && j + d2 <= n) { // 범위에 맞는 x,yd1,d2가 걸러지면
                     check = new int[n + 1][n + 1];
                     
                     section1(i, j, d1, d2);
                     section2(i, j, d1, d2);
                     section3(i, j, d1, d2);
                     section4(i, j, d1, d2);
                     section5(i, j, d1, d2);
                     // 이거 한바퀴를 돌고오면 한세트(한 게리멘더링이) 완성되는거임
                     sum();
                  }
               }
            }
         }
      }
      System.out.println(ans);
   }

   public static void sum() {
      int max = 0;
      int min = Integer.MAX_VALUE;
      int sum[] = new int[6];

      for (int i = 1; i <= n; i++) {
         for (int j = 1; j <= n; j++) {
            sum[check[i][j]] += map[i][j];
         }
      }
      for (int i = 1; i <= 5; i++) {
         max = Math.max(max, sum[i]);
         min = Math.min(min, sum[i]);
      }
      ans = Math.min(ans, max - min);
   }

   static public void inner5(int x, int y) {
      check[x][y] = 5;
      for (int k = 0; k < 4; k++) {
         int nx = x + dx[k];
         int ny = y + dy[k];
         if (nx > 0 && nx <= n && ny > 0 && ny <= n) {
            if (check[nx][ny] != 5)
               inner5(nx, ny);
         }
      }
   }

   static public void section5(int x, int y, int d1, int d2) {// 5번 선거구
      // 5번 선거구에 해당되는 칸을 걸러내서 인구를 더해야함 - return으로 인구스를 반환하는게 나을듯
      // 1번 경계선
      //int sum = 0;
      for (int i = 0; i <= d1; i++) {
         int tmpX = x + i;
         int tmpY = y - i;
         int tmpX1 = x + d2 + i;// 3번 경계선
         int tmpY1 = y + d2 - i;
         check[tmpX][tmpY] = 5;
         check[tmpX1][tmpY1] = 5;
      }
      // 2번 경계선
      for (int i = 0; i <= d2; i++) {
         int tmpX = x + i;
         int tmpY = y + i;
         int tmpX1 = x + d1 + i;// 4번 경계선
         int tmpY1 = y - d1 + i;
         check[tmpX][tmpY] = 5;
         check[tmpX1][tmpY1] = 5;
      }

      for (int i = 0; i < d1; i++) {
         inner5(x + i + 1, y - i);
      }
      for (int i = 0; i < d2; i++) {
         inner5(x + i + 1, y + i);
      }
   }

   static public void section1(int x, int y, int d1, int d2) {
      int sum = 0;
      for (int i = 1; i < x + d1; i++) {
         for (int j = 1; j <= y; j++) {
            check[i][j] = 1;
            // sum += map[i][j];
         }
      }

   }

   static public void section2(int x, int y, int d1, int d2) {
      for (int i = 1; i <= x + d2; i++) {
         for (int j = y + 1; j <= n; j++) {
            check[i][j] = 2;
            // sum += map[i][j];
         }
      }

   }

   static public void section3(int x, int y, int d1, int d2) {
      int sum = 0;
      for (int i = x + d1; i <= n; i++) {
         for (int j = 1; j < y - d1 + d2; j++) {
            check[i][j] = 3;
            // sum += map[i][j];
         }
      }

   }

   static public void section4(int x, int y, int d1, int d2) {
      int sum = 0;
      for (int i = x + d2 + 1; i <= n; i++) {
         for (int j = y - d1 + d2; j <= n; j++) {
            check[i][j] = 4;
            // sum += map[i][j];
         }
      }

   }
}

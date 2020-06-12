#include <iostream>
#include <queue>
#include <cstring>
using namespace std;
int dp[3][100001][3];
int n;
struct Pos {
   int x, y;
};

int dx[] = { 1,0,-1 };
int dy[] = { 1,1,1 };
int dir[3][2] = { {1,1}, {0,1},{-1,1} };//오른쪽 대각선, 왼쪽대각선, 아래

void bfs(int a, int b, int **arr) {
   queue<Pos> q;
   q.push({ a,b });
   while (!q.empty()) {
      int x = q.front().x;
      int y = q.front().y;
      q.pop();
   
      int nx, ny;
      if (y == 0) {
         for (int k = 0; k < 2; k++) {
            nx = x + dx[k];
            ny = y + dy[k];
      
            if (nx < 0 || ny < 0 || nx >= n || ny >= 3) continue;

         //   if (dp[x][nx][ny] == 0) {
               q.push({ nx, ny });
               dp[y][nx][ny] = dp[y][x][y] + arr[nx][ny];
         //   }
         }
      }
      else if (y == 1) {
         for (int k = 0; k < 3; k++) {
            nx = x + dx[k];
            ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= n || ny >= 3) continue;

            //if (dp[x][nx][ny] == 0) {
               q.push({ nx, ny });
               dp[y][nx][ny] = dp[y][x][y] + arr[nx][ny];
         //   }
         }
      }
      else {
         for (int k = 1; k < 3; k++) {
            nx = x + dx[k];
            ny = y + dy[k];
            if (nx < 0 || ny < 0 || nx >= n || ny >= 3) continue;

         //   if (dp[x][nx][ny] == 0) {
               q.push({ nx, ny });
               dp[y][nx][ny] = dp[y][x][y] + arr[nx][ny];
         //   }
         }
      }


   }
}

int main() {
   cin >> n;
   int ** arr = new int *[n];
   memset(arr, 0, sizeof(n));
   for (int i = 0; i < n; i++) {
      arr[i] = new int[3];
      memset(arr, 0, sizeof(int) * 3);
   }

   for (int j = 0; j < n; j++) {
      for (int i = 0; i < 3; i++) {
         bfs(j, i, arr);
      }
   }

   for (int j = 0; j < n; j++) {
      for (int i = 0; i < 3; i++) {
         cin >> arr[j][i];
      }
   }


   int maxim, minim;
   for (int k = 0; k < 3; k++) {
      for (int j = 0; j < n; j++) {
         for (int i = 0; i < 3; i++) {
            cout << dp[k][j][i] << " ";
         }cout << endl;
      }cout << endl;
   }

   return 0;
}

#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;

struct Pos {
    int x;
    int y;
};
int n;
int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };
int sum = 0;

void bfs( int** arr, int** dist, bool** check) {
    queue<Pos> q;//준규가 움직이는 위치를 담는 큐
    Pos pos = { 0,0 }; //준규 시작 위치
    q.push(pos);

    dist[0][0] = arr[0][0];

    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;

        check[x][y] = true; //미리 방문체크
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                if(check[nx][ny] == false){
                	check[nx][ny] = true;
                	dist[nx][ny] = dist[x][y] + arr[nx][ny];
                    q.push({ nx,ny });
                }
                else if (dist[x][y] + arr[nx][ny] < dist[nx][ny]) {//들렸는데 이전에 갖고있던 값보다 작다면
                    dist[nx][ny] = dist[x][y] + arr[nx][ny];
                    dist[nx][ny] = min(dist[nx][ny], dist[x][y] + arr[nx][ny]);
                    //cout << nx << " " << ny << " " << dist[nx][ny] << endl;
                    //check[nx][ny] = true;
                    q.push({ nx,ny });
                }
            }
        }
    }
}

int main() {
	int cnt = 0;
    while (true) {
        cin >> n;
        cnt++;
        if (n == 0) break;

        int** arr = new int* [n];//사탕이 있는 맵
        memset(arr, 0, sizeof(n));

        bool** check = new bool* [n];//들렸는지 안들렸는지 방문체크
        memset(check, false, sizeof(n));

        int** dist = new int* [n];//최대 사탕수를 누적시키는 배열
        memset(dist, 0, sizeof(n));



        for (int i = 0; i < n; i++) {
            arr[i] = new int[n];
            dist[i] = new int[n];
            check[i] = new bool[n];
            memset(check[i], false, sizeof(bool) * n);
            memset(arr[i], 0, sizeof(int) * n);
            memset(dist[i], 0, sizeof(int) * n);

        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++)
                cin >> arr[i][j];
        }
       
        bfs( arr, dist, check);
             
        cout << "Problem " << cnt << ": " <<dist[n - 1][n - 1] << "\n";
    }
    return 0;
}


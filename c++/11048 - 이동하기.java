#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;

struct Pos {
    int x;
    int y;
};
int n, m;
int dx[] = { 0,1 };//오른쪽으로 한칸, 아래로 한칸, 대각선으로 밖에 못감
int dy[] = { 1,0 };
int sum = 0;

void bfs(queue<Pos> q, int** arr, int** dist, bool** check) {
    while (!q.empty()) {
        int x = q.front().x;
        int y = q.front().y;

        check[x][y] = true; //미리 방문체크
        q.pop();

        for (int i = 0; i < 2; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
            if (check[nx][ny] == false) { //들렸던 곳이 아니면
                    check[nx][ny] = true;
                    dist[nx][ny] = dist[x][y] + arr[nx][ny];
                    q.push({ nx,ny });
                }
                else if (dist[nx][ny] < dist[x][y] + arr[nx][ny]) {//들렸는데 이전에 갖고있던 값보다 작다면
                    dist[nx][ny] = dist[x][y] + arr[nx][ny];
                    q.push({ nx,ny });
                }
            }
        }
    }
}

int main() {

    cin >> n >> m;
    int** arr = new int* [n];//사탕이 있는 맵
    memset(arr, 0, sizeof(n));

    int** dist = new int* [n];//최대 사탕수를 누적시키는 배열
    memset(dist, 0, sizeof(n));

    bool** check = new bool* [n];//들렸는지 안들렸는지 방문체크
    memset(check, false, sizeof(n));

    for (int i = 0; i < n; i++) {
        arr[i] = new int[m];
        dist[i] = new int[m];
        check[i] = new bool[m];
        memset(arr[i], 0, sizeof(int) * m);
        memset(dist[i], 0, sizeof(int) * m);
        memset(check[i], false, sizeof(bool) * m);
    }

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++)
            cin >> arr[i][j];
    }
    queue<Pos> q;//준규가 움직이는 위치를 담는 큐
    Pos pos = { 0,0 }; //준규 시작 위치
    q.push(pos);

    dist[0][0] = arr[0][0];
    bfs(q, arr, dist, check);
    cout << dist[n - 1][m - 1];
    return 0;
}

#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>

using namespace std;
int n;

struct Pos {
	int x;
	int y;
	int dir;//가로(1),세로(2),대각선(3)인지 기억하기 위해
	//int cnt;
};

int ddir[3][2] = { { 1, 0 }, { 0 , 1 }, { 1, 1 } };//가로, 세로로 이동할지 안할지의 여부(가로,세로,대각선)
int dx[] = { 0, 1, 1 };
int dy[] = { 1, 0, 1 };

long long int dp[3][32][32];

//1.진행방향은 아래를 향해야함
//2.파이프 주변이 빈공간인지 확인해야함
//->가로 세로는 그냥 true false로 확인하면되지만
//->대각선은 왼쪽,위에도 false인지 확인해야함

//방법의 개수이니까, 최단거리 보다는 모든 노드를 검사하는 dfs가 나을거라 생각했는데
//이동할때 빈공간인지 확인해야하는거보면 bfs가 더 나을거같기두..
//뭔가 dp라기엔..전에껄 굳이 기억하고 넘겨할 부분이 없음

void bfs(int** map) {

	for (int x = 0; x < n; x++) {
		for (int y = 0; y < n; y++) {
			for (int dir = 0; dir < 3; dir++) {
				for (int i = 0; i < 2; i++) {
					if (ddir[dir][i] == 1) {
						int nx = x + dx[i];
						int ny = y + dy[i];
						if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
							if (map[nx][ny] != 1) {
								dp[i][nx][ny] += dp[dir][x][y];
							}
						}
					}
				}

				int nx = x + dx[2];
				int ny = y + dy[2];
				if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
					if (map[nx][ny] != 1 && map[nx - 1][ny] == 0 && map[nx][ny - 1] == 0 && map[nx][ny] == 0) {
						dp[2][nx][ny] += dp[dir][x][y];
					}
				}
			}
		}
	}
}

int main() {
	cin >> n;

	int** map = new int*[n];
	memset(map, 0, sizeof(n));

	for (int i = 0; i < n; i++) {
		map[i] = new int[n];
		memset(map[i], 0, sizeof(int) * n);
	}
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}

	dp[0][0][1] = 1;//끝점 1로 초기화
	bfs(map);

	cout << dp[0][n-1][n-1] + dp[1][n - 1][n - 1] + dp[2][n - 1][n - 1] << endl;

	return 0;
}

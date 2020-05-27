#include <iostream>
#include <string>
#include <queue>
using namespace std;

struct Pos {
	int x;
	int y;
	int color;
};

int n;//map의 크기
char map[101][101];
int map2[101][101];
int dp[2][101][101];//적록색약이 아닌사람 : 0, 적록색약인사람 1

int dx[] = { 0,0,-1,1 };
int dy[] = { -1,1,0,0 };

int area_cnt;
queue<Pos> q;

void bfs(int x, int y, int color, int k) {
	q.push({ x, y, color });
	dp[k][x][y] = area_cnt;

	while (!q.empty()) {
		x = q.front().x;
		y = q.front().y;
		color = q.front().color;
		q.pop();

		for (int i = 0; i < 4; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			int nColor = map2[nx][ny];

			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if (k == 0 && dp[0][nx][ny] == 0 && nColor == color) {
					q.push({ nx, ny, nColor });
					dp[0][nx][ny] = area_cnt;
				}
				else if (k == 1 && dp[1][nx][ny] == 0) {
					if ((color == nColor) || (color == 0 && nColor == 1) || (color == 1 && nColor == 0)) {
						q.push({ nx, ny, nColor });
						dp[1][nx][ny] = area_cnt;
					}
				}
			}
		}
	}
}


int main() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> map[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (map[i][j] == 'R') {
				map2[i][j] = 0;
			}
			else if (map[i][j] == 'G') {
				map2[i][j] = 1;
			}
			else if (map[i][j] == 'B') {
				map2[i][j] = 2;
			}
		}
	}

	for (int k = 0; k < 2; k++) {
		area_cnt = 1;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (dp[k][i][j] == 0) {
					bfs(i, j, map2[i][j], k);
					area_cnt++;
				}
			}
		}
	}
	int max = 0; int max2 = 0;
	for (int i = 0; i < n; i++) {
		if (max < dp[0][n - 1][i]) max = dp[0][n - 1][i];
		if (max2 < dp[1][n - 1][i]) max2 = dp[1][n - 1][i];
	}
	cout << max << " " << max2;
	
	return 0;
}

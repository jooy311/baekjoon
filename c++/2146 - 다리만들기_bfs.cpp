#include <iostream>
#include <queue>
#include <cstring>
using namespace std;

struct Pos {
	int x, y, cnt;
};

int n;
int arr[101][101];
bool visited[101][101];

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

int result = 123456789;

bool bfs(int a, int b) {
	for (int k = 0; k < 4; k++) {
		int na = a + dx[k];
		int nb = b + dy[k];

		if (na >= 0 && nb >= 0 && na < n && nb < n) {
			if (arr[na][nb] == 0) return true;
		}
	}
	return false;
}

void bfs2(int x, int y) {
	queue<Pos> q;
	q.push({ x,y,0 });
	visited[x][y] = true;

	while (!q.empty()) {
		int cx = q.front().x;
		int cy = q.front().y;
		q.pop();

		for (int k = 0; k < 4; k++) {
			int nx = cx + dx[k];
			int ny = cy + dy[k];

			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if (arr[nx][ny] == 1 && visited[nx][ny] == false) {
					visited[nx][ny] = true;
					q.push({ nx,ny,0 });
				}
			}
		}
	}

	q.push({ x,y,0 });

	while (!q.empty()) {
		int cx = q.front().x;
		int cy = q.front().y;
		int cnt = q.front().cnt;
		q.pop();

		if ((x != cx || y != cy) && arr[cx][cy] == 1) {
			if (result > cnt) result = cnt;
			break;
		}

		for (int k = 0; k < 4; k++) {
			int nx = cx + dx[k];
			int ny = cy + dy[k];

			if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
				if (visited[nx][ny] == false) {
					visited[nx][ny] = true;
					q.push({ nx,ny, cnt + 1 });
				}
			}
		}
	}
}

int main() {
	cin >> n;

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			cin >> arr[i][j];
		}
	}

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (arr[i][j] == 1) {
				if (bfs(i, j)) {
					memset(visited, false, sizeof(visited));
					bfs2(i, j);
				}
			}
		}
	}

	cout << result -1 << "\n";

	return 0;
	}

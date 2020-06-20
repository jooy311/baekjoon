#include <iostream>
#include <queue>
#include <vector>
using namespace std;

struct Pos {
	int y, x;
};

int n;
char arr[7][7];
vector<Pos> vt, vx;

int dx[] = { -1,1,0,0 };
int dy[] = { 0,0,-1,1 };

bool check(Pos ps) {
	for (int i = 0; i < 4; i++) {
		int y = ps.y;
		int x = ps.x;
		while (0 <= y + dy[i] && y + dy[i] < n && 0 <= x + dx[i] && x + dx[i] < n) {
			y += dy[i];
			x += dx[i];
			if (arr[y][x] == 'O') break;
			else if (arr[y][x] == 'S') return true;
		}
	}
	return false;
}

void setWall(int depth, int start) {
	if (depth == 3) {
		for (Pos ps : vt) {
			if (check(ps)) return;
		}
		cout << "YES\n";
		exit(0);
	}

	for (int i = start; i < vx.size(); i++) {
		arr[vx[i].y][vx[i].x] = 'O';
		setWall(depth + 1, i + 1);
		arr[vx[i].y][vx[i].x] = 'X';
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
			if (arr[i][j] == 'T') {
				vt.push_back({ i,j });
			}
			else if (arr[i][j] == 'X') {
				vx.push_back({ i,j });
			}

		}
	}

	setWall(0, 0);
	cout << "NO\n";

	return 0;
}

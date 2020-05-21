#include <iostream>
#include <cstring>
#include <queue>
using namespace std;
struct Pos {
	int x;
	int y;
	int cnt;//이동한 횟수
	int hor; //말처럼이동할 수 있는 횟수
};

int dx[] = {2,2,-2,-2,1,1,-1,-1,  0,0,-1,1}; //8~11까지는 말이 아닌 원숭이가 가는 방법
int dy[] = {-1,1,-1,1,2,-2,2,-2,  1,-1,0,0};//말로 이동할 수 있는 경우의 수

int k, w, h;
int result = 123594387;

int bfs(queue<Pos> q, bool check[200][200][32], int ** map) {
	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		int c = q.front().cnt;
		int horse = q.front().hor;
		if (x == w-1 && y == h-1) {
			//if (result > c) result = c;
			return c;
		}
		q.pop();
		//[x][y] = true;
		
		//말처럼 이동하는 경우
		if(horse < k) {
			for (int i = 0; i < 8; i++) { //이 방향으로 가는것을 k번만큼만 한다.
				int nx = x + dx[i];
				int ny = y + dy[i];
				if (nx >= 0 && ny >= 0 && nx < w && ny < h) {
					if (check[nx][ny][horse + 1] == false && map[nx][ny] == 0) {
						check[nx][ny][horse + 1] = true;
						q.push({ nx, ny, c + 1 , horse + 1 });
					}
				}
			}
		}
		//원숭이로 이동하는 경우
		for (int i = 8; i < 12; i++) {
			int nx = x + dx[i];
			int ny = y + dy[i];
			if (nx >= 0 && ny >= 0 && nx < w && ny < h) {
				if (check[nx][ny][horse] == false && map[nx][ny] == 0) {
					check[nx][ny][horse] = true;
					q.push({ nx, ny, c + 1 , horse });
				}
			}
		}
	}
	//result = -1;
	return -1; //못가는 경우
}

int main() {
	cin >> k;//원숭이가 말처럼 움직일 수 있는 횟수
	cin >> w >> h; //가로, 세로

	queue<Pos> q; //원숭이가 이동할 수 있도록 하는 큐
	Pos pos = {0,0,0};//원숭이의 처음 시작점은 0,0 이동횟수는 0
	q.push(pos);

	int** map = new int* [w];
	memset(map, 0, sizeof(w));

	bool check[200][200][32];

	for (int i = 0; i < w; i++) {	
		map[i] = new int[h];
		memset(map[i], 0, sizeof(int) * h);
	}

	for (int i = 0; i < h; i++) {
		for (int j = 0; j < w; j++) {
			cin >> map[j][i];
		}
	}

	cout << bfs(q, check, map) << "\n";
	return 0;
}

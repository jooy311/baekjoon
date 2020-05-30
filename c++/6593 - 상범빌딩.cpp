#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;

//다음층에서 
struct Pos {
	int z;//floor
	int y;
	int x;
	int s;
};

int L, R, C;

int dx[] = { -1, 0, 0, 1, 0, 0 };
int dy[] = { 0, 1, 0, 0, 0, -1 };
int dz[] = { 0, 0, -1, 0, 1, 0 };//가만히 있거나, 윗층으로 올라가거나, 아래층으로 내려가거나


int bfs(queue<Pos> q, bool visited[31][31][31], char jail[31][31][31], int ex, int ey, int ez) {
	while (!q.empty()) {
		int x = q.front().x;
		int y = q.front().y;
		int z = q.front().z;
		int second = q.front().s;
		q.pop();

		if (x == ex && y == ey && z == ez) {
		//	while (!q.empty()) q.pop();
			return second;
		}

		for (int k = 0; k < 6; k++) {
			int nx = x + dx[k];
			int ny = y + dy[k];
			int nz = z + dz[k];

			if (nx >= 1 && ny >= 1 && nz >= 1 && nx <= C && ny <= R && nz <= L) {
				if (visited[nz][ny][nx] == false && jail[nz][ny][nx] != '#') {
				//	cout << "nz : " << nz << " ny : " << ny << " nx : " << nx << endl;
					visited[nz][ny][nx] = true;
					q.push({ nz, ny, nx, second + 1 });
				}

			}
		}
	}
	return -1;

}

int main() {

	while (true) {
		cin >> L >> R >> C; //L :층수, R행 C열
		if (L == 0 && R == 0 && C == 0) break;
		queue<Pos> q;

		bool visited[31][31][31];
		char jail[31][31][31];//층,행,열
		int ex, ey, ez;
		
    //bool 초기화 안해줘서 계속 틀렸던거임 ㅎ...
		for (int z = 1; z <= L; z++) {//층수=z
			for (int y = 1; y <= R; y++) {//행=y
				for (int x = 1; x <= C; x++) {//열=x
					visited[z][y][x] = false;
				}
			}
		}

		//입력받는다
		for (int z = 1; z <= L; z++) {//층수=z
			for (int y = 1; y <= R; y++) {//행=y
				for (int x = 1; x <= C; x++) {//열=x
					char tmp;
					cin >> tmp;
					jail[z][y][x] = tmp;

					if (tmp == 'S') {
						//S의 위치를 큐에 넣는다.
						q.push({ z, y, x ,0 });//y
						visited[z][y][x] = true;
					}
					else if (tmp == 'E') {
						ex = x;
						ey = y;
						ez = z;
					}
				}
			}
		}

		int result = bfs(q, visited, jail, ex, ey, ez);

		if (result == -1)
			cout << "Trapped!\n";
		else {
			cout << "Escaped in " << result << " minute(s).\n";
		}

	}//프로그램끝

	return 0;
}

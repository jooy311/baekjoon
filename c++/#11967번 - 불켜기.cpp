#include <iostream>
#include <vector>
#include <queue>
#include <cstring>
using namespace std;

int n, m;
bool check[101][101];//찐방문체크
bool isLightOn[101][101];
bool flag;

struct Pos {
	int x, y;
};

int dx[] = { 0,0,-1,1 };
int dy[] = { -1,1,0,0 };
int cnt = 0;

bool TurnOn( bool flag ,int cx, int cy, vector<vector<queue<Pos>>> room) {
	
		while (!room[cx][cy].empty()) {
				int tmpX = room[cx][cy].front().x;
				int tmpY = room[cx][cy].front().y;
				room[cx][cy].pop();
				if (isLightOn[tmpX][tmpY] == false) {
					isLightOn[tmpX][tmpY] = true;//불이 켜져있는지의 여부
					flag = true;
				}
				
		}
	
	return flag;

}

bool bfs(bool flag , vector<vector<queue<Pos>>> room) {
	queue<Pos> qq; //움직임을 받을 큐
	qq.push({ 0,0 }); //시작은 0,0
	
	while (!qq.empty()) {
		int cx = qq.front().x;
		int cy = qq.front().y;
		qq.pop();
		if(room[cx][cy].size() >=1){
			flag = TurnOn(flag, cx, cy, room);
		}

		for (int k = 0; k < 4; k++) {
				int nx = cx + dx[k];
				int ny = cy + dy[k];
				if (nx<0 || nx>n - 1 || ny<0 || ny>n - 1)continue;
				 {
					if (isLightOn[nx][ny] == true && check[nx][ny] == false) {
						//cout  << nx+1 << " " << ny+1 << endl;
						qq.push({ nx,ny });
						//cnt++;
						check[nx][ny] = true;
					}
				}
		}
		//check[cx][cy] = false;
	}
	return flag;
}

int main() {
	cin >> n >> m;
	vector<queue<Pos> > v(n);
	vector< vector < queue <Pos> > > room(n, v);

	for (int i = 0; i < m; i++) {
		int s_x, s_y, e_x, e_y;
		cin >> s_x >> s_y >> e_x >> e_y;
		room[s_x - 1][s_y - 1].push({ e_x - 1, e_y - 1 });
	}


	while (true) {
		memset(check, false, sizeof(check));
		isLightOn[0][0] = true;//처음위치는 당연히 불이 켜져있음
		check[0][0] = true;
		flag = false;
		flag = bfs(flag, room);
		//if(flag) cout << "true지롱" <<endl;
		if (!flag) break;
	}
	

	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n; j++) {
			if (isLightOn[i][j]) {
				cnt++;
				//cout << i+1 << " " << j+1 << endl ;
			}
		}
	}
	cout << cnt << "\n";

	return 0;
}

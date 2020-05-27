#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;
int n, m, k;
struct Pos {
    int x; int y;
};
int map[101][101];
int group[101][101];

int dx[] = { 0,0,-1,1 };
int dy[] = { -1,1,0,0 };

queue<Pos> q;//bfs돌때 각 영역(서로 접해져있는 곳까지의 영역)을 탐색하기 위해 좌표를 넣기위한 큐
vector<int> v_ans;//영역의 갯수와, 영역의 넓이를 받아놓기 위한 벡터

void bfs(int x, int y) {
    int cnt = 1; //시작을 1로 해주어야, (0.0)만 map==0 일때 1로 셀 수 있음
    q.push({ x,y });
    group[x][y] = cnt; //main함수에서 이중포문으로 받아온 x,y 위치를 해당 영역의 몇번째 칸인지 cnt로 구분.

    while (!q.empty()) {
        x = q.front().x;
        y = q.front().y;
        q.pop();

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < m && ny < n) {
                if (map[nx][ny] == 0 && group[nx][ny] == 0) {
                    q.push({ nx, ny });
                    cnt++;
                    group[nx][ny] = cnt;//cnt로 영역을 센ㄷㅏ.
                }
            }
        }
    }
    v_ans.push_back(cnt);//while문을 나오게되면 cnt의 값이 결국 한 영역의 넓이값이 된다.
}

int main() {
    cin >> m >> n >> k; //행과 열

    for (int i = 0; i < k; i++) { //사각형 영역을 1로 주기.

        int startX, startY, endX, endY;
        cin >> startX >> startY >> endX >> endY;

        for (int j = startX; j < endX; j++) {
            for (int t = startY; t < endY; t++) {
                map[t][j] = 1;//주어진 사각형을 1로 만들어준다.
            }
        }
    }

    //bfs로 영역 구하기 -> 영역을 구하려면 어쩔수없이 모든 map을 다 돌아봐야함
    for (int i = 0; i < m; i++) {
        for (int j = 0; j < n; j++) {
            if (map[i][j] == 0 && group[i][j] == 0) { //group이 방문체크의 역할을 한다.
                bfs(i, j);
            }
        }
    }
    sort(v_ans.begin(), v_ans.end());// 정렬해서 출력하라고 했으므로
    
    cout << v_ans.size() << "\n";//벡터의 사이즈 = 영역의 갯수
    
    for (int i = 0; i < v_ans.size(); i++) {
        cout << v_ans[i] << " ";
    }
    return 0;
}

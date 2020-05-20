#include <iostream>
#include <cstring>
using namespace std;
//bfs문제인줄 알았는데 dp문제...
//=>점화식을 세워야함
//bfs와 dp를 보고 딱 알 수 있는 방법
//1.bfs는 무언가의 최단거리를 찾는 문제
//2.dp는 작은것을 반복해서 큰것을 얻을 수 있을때

int main() {
	int n, m;
	cin >> n >> m;
	int ** arr = new int *[n + 1]; //동적할당할때 row부분먼저 할당
	memset(arr, 0, sizeof(n + 1));

	int ** dist = new int *[n + 1];
	memset(dist, 0, sizeof(n + 1));

	for (int i = 0; i <= n; i++) {//1부터 인덱스를 시작해도 할당은 0~n까지 할당해주어야함
		arr[i] = new int[m + 1];
		dist[i] = new int[m + 1];
		memset(arr[i], 0, sizeof(int) * (m + 1));
		memset(dist[i], 0, sizeof(int) * (m + 1));
	}

	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++)
			cin >> arr[i][j];
	}


	for (int i = 1; i <= n; i++) {
		for (int j = 1; j <= m; j++) {
			dist[i][j] += max(dist[i - 1][j], dist[i][j - 1]) + arr[i][j]; //대각선은 굳이 보지 않아도 되기 때문
      //위 아래 부분에서 최대값에 + 전의값 을 더한값을 dist에 저장시키면 됨
		}
	}
	
	cout << dist[n][m] << endl;
	return 0;
}

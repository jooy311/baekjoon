#include <iostream>
#include <vector>
#include <cstring>
#include <queue>
using namespace std;

#define INF 987654321
struct Pos {
	int cur, w;
};

int V, E, start;

int main() {
	cin >> V >> E;
	cin >> start;

	vector<vector<Pos>> vec(V);

	vector<int> d; // 방문 여부, 최단 경로
	d.assign(V, INF);

	while (E--) {
		int u, v, w;
		cin >> u >> v >> w;
		vec[u - 1].push_back({ v,w });
	}

	d[start - 1] = 0; //자기자신까지 가는 거리는 0 이므로

	//--우선순위 큐
	priority_queue<pair<int, int>> pq;
	pq.push({0 , start });

	while (!pq.empty()) {
		int cur = pq.top().second - 1;
		int w = pq.top().first * (-1);
		pq.pop();

		for (int i = 0; i < vec[cur].size(); i++) {
			int nextW = d[cur] + vec[cur][i].w;
			int nowW = d[vec[cur][i].cur - 1];

			if (nextW < nowW) {
				d[vec[cur][i].cur - 1] = nextW;
				pq.push({(-1) * nextW ,vec[cur][i].cur });
			}
		}
	}


	//--------출력-------

	for (int i = 0; i < d.size(); i++) {
		if (d[i] == INF)
			cout << "INF" << "\n";
		else
			cout << d[i] << "\n";
	}
	return 0;
}

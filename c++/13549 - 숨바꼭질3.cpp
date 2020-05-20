#include <iostream>
#include <cstring>
#include <deque>
using namespace std;
struct Pos {
	int x;
	int cnt;
};
int n, k;
int result = 1000001;
bool check[100001];

void bfs(deque<Pos> q) {
	while (!q.empty()) {

		int subin = q.front().x;
		int sec = q.front().cnt;
		if (subin == k) {
			if(result > sec) result = sec;
			cout << sec;
		}
		q.pop_front();
		int nx;

		nx = 2 * subin;
		if (nx >= 0 && nx <= 100000) {
			if (check[nx] == false) {
				q.push_front({ nx , sec });
				check[nx] = true;
			}
		}

		nx = subin - 1;
		if (nx >= 0 && nx <= 100000) {
			if (check[nx] == false) {
				q.push_back({ nx, sec + 1 });
				check[nx] = true;
			}
		}

		nx = subin + 1;
		if (nx >= 0 && nx <= 100000) {
			if (check[nx] == false) {
				q.push_back({ nx , sec + 1 });
				check[nx] = true;
			}
		}
	}
	return;
}

int main() {
	cin >> n >> k;
	deque<Pos> q;
	Pos pos = { n,0 };
	q.push_back(pos);
	check[n] = true;
	bfs(q);
	return 0;
}

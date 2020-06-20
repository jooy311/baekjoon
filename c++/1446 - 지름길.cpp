#include <iostream>
#include <algorithm>
using namespace std;

struct Pos {
	int start, end, dist; //시작위치, 끝나는위치, 걸리는 거리
};

int dp[10001];
Pos arr[10001];

int main() {
	int N, D;
	cin >> N >> D;

	int start, end, dist;

	for (int i = 0; i < N; i++) {
		cin >> start >> end >> dist;
		arr[i] = { start, end, dist }; //주어진 정보를 배열에 넣는다.
	}


	//int cnt = 0; //지름길의 갯수
	//int nowPos = 0;

	for (int i = 1; i <= D; i++) {
		dp[i] = dp[i - 1] + 1;//지름길이 없는 경우라면 기본적으로 일보전진
		for (int j = 0; j <= N; j++) {
			if (arr[j].end < D || arr[j].end - arr[j].start > arr[j].dist) {//조건해당해야 진행
				if (arr[j].end == i) {//지름길로 이동했을때의 끝점과 현재 이동한 위치i가 같다면
					dp[i] = min(dp[i], dp[arr[j].start] + arr[j].dist);//지금 위치한곳의 값 vs 지름길로 이동해오기 전의 위치에서의 그동안의값 + 그 위치의 값
				}
			}
		}
	}

	cout << dp[D];

	return 0;
}

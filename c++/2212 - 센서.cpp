#include <iostream>
#include <algorithm>
#include <vector>
using namespace std;
int N, K;//센서의 개수, 집중국의 개수
int sensor[10001];//센서의 좌표를 저장하는 배열

int main() {
	cin >> N;
	cin >> K;
	
	for(int i = 0; i < N; i++){
		cin >> sensor[i];
	}
	
	sort(sensor, sensor+N); // 기지국들의 위치 정렬
	
	vector<int> dist(N-1,0);//초기화해주기 귀찮아서 벡터로 받았다 배열로해도 상관없음
	
	for(int i = 0; i < N-1; i++){
		dist[i] = sensor[i+1] - sensor[i];
	}
	
	sort(dist.begin(), dist.end());
	
	int ans = 0;
	
	for(int i = 0; i< N-K; i++)
		ans += dist[i];
		
	cout << ans;
	
	return 0;
}

#include <iostream>
#include <algorithm>
using namespace std;
int t,w;
int ans = 0;
int dp[3][1001][32];//기회는 0,1,2이기때문에 배열크기3을 주어야함
int pos[1001];//자두의 위치
	
int main() {
	
	cin >> t >> w;

	for(int i = 1; i<= t; i++){//자두가 움직이는 위치를 받는다
		cin >> pos[i];
	}
	
	for(int i = 1; i <= t; i++){
		for(int j = 1; j <= w+1; j++){//w=2인 경우, 기회는 0,1,2이기때문.
			if(pos[i] == 1){//자두가 나무1에서 떨어질때
			//=>1로 움직임 : 자두 획득 -> dp값+1
			//=>2로 움직임 : 자두 미획득 -> dp값.
				//자두가 나무1로 왔다면, (1에서 왔을때 + 1)와 (2에서 왔을때 + 1)중에 max값
				dp[1][i][j] = max(dp[1][i - 1][j] +1, dp[2][i - 1][j - 1]+1);
				//다음 위치가 2라면, 1에서왔을때와 2에서 왔을때
				dp[2][i][j] = max(dp[1][i - 1][j - 1], dp[2][i - 1][j]);
			}
			else {//자두가 나무2에서 떨어지는 경우
				if(i==1 && j==1) continue;
				//자두가 1초에 2번에 있는 자두를 먹기 위해 움직이는것은 위치바꾼것에 해당이 되지 않음 
				
				dp[1][i][j] = max(dp[2][i - 1][j-1], dp[1][i - 1][j]);
				dp[2][i][j] = max(dp[1][i - 1][j - 1] + 1, dp[2][i - 1][j] + 1);
			}
		}
	}
	
	for(int i = 1; i<=w+1; i++){
		ans = max(ans, max(dp[1][t][i],dp[2][t][i]));
	}
	cout << ans;
	return 0;
}

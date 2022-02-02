#include <iostream>
#include <vector>
#include <string>
using namespace std;

//int arr[1001][1001];
//int dp[1001][1001];
string str;
int result;

int main() {
	int N, M;
	cin >> N >> M;
	
	vector<vector<int>> arr(N, vector<int>(M,0));
	vector<vector<int>> dp(N, vector<int>(M,0));

	for(int n = 0; n < N; n++){//행
		cin >> str;
		for(int m = 0; m < M; m++){//열
			arr[n][m] = str[m] -48;
			if(arr[n][m] == 1) {
				dp[n][m] = 1;
				result = 1;
			}
		}
	}
	
	//int result = 1;
	
	for(int n = 1; n < N; n++){
		for(int m = 1; m < M; m++){
			if(arr[n][m-1] == 1 && arr[n-1][m] == 1 && arr[n-1][m-1] == 1){//다 1이여야함
				dp[n][m] = min(dp[n-1][m], min(dp[n][m-1] , dp[n-1][m-1])) + 1;
				//가장 최소의 길이를 구해야 정사각형이 형성되므로
			}
			result = max(result, dp[n][m]);
		}
	}

	cout << result * result << endl;
	return 0;
}

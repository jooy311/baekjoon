#include <iostream>
#include <cstring>
#include <queue>
#include <algorithm>
using namespace std;
int n;

int main() {
	cin >> n;
	int** dp = new int*[n + 1];
	memset(dp, 0, sizeof(n + 1));

	for (int i = 0; i < n + 1; i++) {
		dp[i] = new int[9];
		memset(dp[i], 0, sizeof(int) * 9);
	}

	for (int i = 0; i <= 9; i++) {
		dp[0][i] = 1;
		dp[1][i] = 1;
	}

	for (int i = 2; i <= n; i++) {
		for (int j = 0; j <= 9; j++) {
			for (int k = j; k <= 9; k++) {
				dp[i][j] += dp[i - 1][k];
				dp[i][j] %= 10007;
			}
		}
	}
	int sum = 0;
	for (int i = 0; i <= 9; i++) {
		sum += dp[n][i];
	}
	cout << sum % 10007 << "\n";
	
	return 0;
}

#include <iostream>
using namespace std;
int memo[1001];

void dp(int n) {
	for (int i = 2; i <= n; i++) {
		//홀수인경우
		if (i % 2 != 0) {
			memo[i] = ((memo[i - 1] * 2) - 1) % 10007;
		}
		else {//짝수인경우
			memo[i] = ((memo[i - 1] * 2) + 1) % 10007;
		}
	}
}

int main() {
	int n;
	cin >> n;
	memo[1] = 1; //1가지밖에 없음

	dp(n);
	cout << memo[n] % 10007;

	return 0;
}

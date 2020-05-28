#include <iostream>
#include <string>
#include <cstring>
#include <stack>
using namespace std;

int main() {
	int n;
	cin >> n; //빌딩의 개수

	int * arr = new int[n];
	memset(arr, 0, sizeof(n));
	
	for (int i = 0; i < n; i++) {
		cin >> arr[i];
	}
	
	stack<int> st;

	long long int result = 0;
	
	for (int i = 0; i < n; i++) {
		while (!st.empty() && st.top() <= arr[i]) {
				st.pop();
		}
		st.push(arr[i]);
		result += st.size() - 1;
	}

	cout << result << endl;
	return 0;
}

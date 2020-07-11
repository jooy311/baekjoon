#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int asize;//수열의 크기
int x;

int main() {
	
	cin >> asize;
	
	vector<int> dp;

	for(int i = 0; i < asize; i++){
		cin >> x;
		if(dp.empty() || dp.back() < x)
			dp.push_back(x);
		else{
			auto iter = lower_bound(dp.begin(), dp.end(), x);
			*iter = x;
		}
	}
	
	cout << dp.size();
	
	return 0;
}

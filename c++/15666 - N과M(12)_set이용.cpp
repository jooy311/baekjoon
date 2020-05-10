#include <iostream>
#include <vector>
#include <set>
#include <cstring>
#include <algorithm>

using namespace std;

set<vector<int>> s;

void dfs(int idx, int depth, int n, int m, int *tmp, int *arr){
	if(depth == m){
		vector<int> v;
		for(int i=0; i<depth; i++){
			v.push_back(tmp[i]);
		}
		s.insert(v);
		v.clear();

		return;
	}
	
	for(int i=idx; i<n; i++){
		tmp[depth] = arr[i];
		dfs(i, depth + 1, n, m, tmp, arr);
	}
	
}
int main() {
	int n, m;
	cin >> n >> m;
	
	int *arr = new int[n];
	memset(arr, 0, sizeof(n));
	
	int *tmp = new int[n];
	memset(tmp, 0, sizeof(n));
	
	for(int i = 0; i < n; i++){
		cin >> arr[i];
	}
	sort(arr, arr+n);
	dfs(0,0,n,m,tmp,arr);
	
	for(auto a : s){
		for(int i=0; i<a.size(); i++){
			cout << a[i] << " ";
		}
		cout << "\n";
	}
	return 0;
}

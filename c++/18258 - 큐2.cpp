#include <iostream>
#include <string>
#include <queue>
#include <vector>
 
using namespace std;
 
int main() {
	cin.tie(NULL);
	ios::sync_with_stdio(false);
 
	int n;
	cin >> n; //명령의 개수
	queue<int> q;
 
	for (int i = 0; i < n; i++) {
		string str;
		vector<int> result;
 
		cin >> str;
		if (str == "push") {
			int tmp;
			cin >> tmp;
			q.push(tmp);
		}
		else if (str == "front") {
			if (q.empty()) result.push_back(-1);//cout << -1 << "\n";
			else result.push_back(q.front());//cout << q.front() << "\n";
 
		}
		else if (str == "pop") {
			if (q.empty()) result.push_back(-1);//cout << -1 << "\n";
			else {
 
				result.push_back(q.front());//cout << q.front() << "\n";
				q.pop();
			}
		}
		else if (str == "back") {
			if (q.empty()) result.push_back(-1);//cout << -1 << "\n";
			else result.push_back(q.back());
		}
		else if (str == "size") {
			result.push_back(q.size());
			//cout << q.size() << "\n";
		}
		else if (str == "empty") {
			if (q.empty()) result.push_back(1);// cout << 1 << "\n";
			else result.push_back(0);// cout << 0 << "\n";
		}
 
		for (int i = 0; i < result.size(); i++)
		{
			cout << result[i] << "\n";
		}
	}
	return 0;
}

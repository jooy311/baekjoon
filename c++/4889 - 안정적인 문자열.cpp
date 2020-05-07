#include <iostream>
#include <string>
#include <stack>
using namespace std;

bool flag = false;

int main() {
	int cnt = 0;
	while(flag != true){
		cnt++;
		int change = 0;
		stack<int> stack;
		string s;
		cin >> s;
		int len = s.length();
		if(len > 0  && s[0] == '-') break;//하나라도 - 를 가지고있으면 종료
		for(int i = 0; i< len; i++){
			if(s[i] == '{')
				stack.push(i);
			else if(s[i]=='}'){
				if(!stack.empty()){
					stack.pop();
				}else{
					stack.push(i);
					change++;
				}
			}
		}
		change += stack.size() / 2;
		if(change < 0)
			change = 0;
		cout << cnt << ". " << change << endl;
	}
	return 0;
}

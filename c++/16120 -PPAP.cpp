#include <iostream>
#include <string>
using namespace std;

int main() {
	string str;
	cin >> str;
	
	int one = 0;
	
	for(int i = 0; i < str.length(); i++){
		if(str[i] == 'P'){
			one++;
			continue; //esle안쓰고 -> 시간 줄이기 위해
		}
		
		if(one >=2 && str[i+1] == 'P'){
			one--;
			i++;
		}
		
		else{
			cout << "NP\n";
			return 0;
		}
	}
	
	if(one == 1) cout << "PPAP\n";
	else cout << "NP\n";
	return 0;
}

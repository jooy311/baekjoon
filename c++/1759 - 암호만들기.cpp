#include <iostream>
#include <algorithm>
#include <cstring>
using namespace std;

int l, c;//l개의 알파벳, c개들의 문자
char pwd[16];//주어진 알파벳을 담기위한 배열

//char real[16];//구성되어진 암호후보를 담을 배열

//최소 한개 모음, 최소 두개 자음
//증가 순서
//주어진 알파벳은 중복 없음

void check(char * real) {
	int jaa = 0;
	int moo = 0;
	for (int i = 0; i < l; i++) {
		if (real[i] == 'a' || real[i] == 'i' || real[i] == 'o' || real[i] == 'u' || real[i] == 'e') {
			moo++;
		}
		else{
			jaa++;
		}
	}
	if (jaa >= 2 && moo >= 1) {
		for(int i = 0; i< l; i++){
			cout << real[i];
		}cout << "\n";
	}

	return;
}

void dfs(int depth, int start, char * real) {
	if (depth == l) { //l개의 알파벳이 골라졌다면ㄴ
		//자음모음 갯수맞는지 확인
		check(real);
		return;
	}

	//백트래킹
	//=>중복이 없어야하며, 사전순이어야 한다.
	for (int i = start; i < c; i++) {
		real[depth] = pwd[i];
		dfs(depth + 1, i + 1, real);
	}
}

int main() {

	cin >> l >> c;
	
	char * real = new char[l];
	memset(real, ' ', sizeof(l));
	
	//주어진 알파벳을 저장한다.
	for (int i = 0; i < c; i++) {
		cin >> pwd[i];
	}
	
	sort(pwd, pwd+c);//사전순으로 정렬

	//백트래킹(dfs)
	dfs(0,0,real);

	return 0;
}

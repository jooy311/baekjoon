#include <iostream>
#include <string>
#include <stack>
#include <cstring>
using namespace std;

int d, k;
int A, B;
//int[] tmp;
//int[] sum;

int count_a(int day, int tmp[], int sum[]);

int main() {
	cin >> d >> k;
	
	//선언
	int tmp[d+1];
	int sum[d+1];

//초기화
	memset(tmp, 0 , sizeof(d+1));
	memset(sum, 0, sizeof(d+1));
	
	tmp[1] = 1; tmp[2] = 1;
	
	int b = count_a(d-1,tmp,sum);
	int a = tmp[d-2];
	
	for(int i=1; i<=k; i++){
		int j = k - (a*i);
		if(j<=0) continue;
		if(j%b != 0 || i > j/b) continue;
		B = j/b;
		A = i;
	}
	cout << A << endl;
	cout << B << endl;
	return 0;
}
int count_a(int day, int tmp[], int sum[]){
	if(day == 1 || day == 2){
		tmp[day] = 1;
		return 1;
	}
	if(tmp[day] != 1){
		tmp[day] = count_a(day-1,tmp,sum) + count_a(day-2,tmp,sum);
	}
	return tmp[day];
}

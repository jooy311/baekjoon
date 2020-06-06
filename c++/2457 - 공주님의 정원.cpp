#include <iostream>
#include <algorithm>
using namespace std;

pair<int, int> flower[10000001];

int main() {
	int n;
	cin >> n;//꽃의 종류의 개수
	for (int i = 0; i < n; i++) {
		int a, b, c, d;
		cin >> a >> b >> c >> d;
		flower[i].first = a * 100 + b;
		flower[i].second = c * 100 + d;
	}

	sort(flower, flower + n);
	
	//3월 1일부터 11월 30일까지 매일 한가지 꽃이상(최소)
	bool flag = false;
	int temp = 0;
	int ans = 0;
	
	for (int i = 0, date = 301; i < n, date <= 1130; i++) {
		flag = false;
		for (int j = i; j < n; j++) {//꽃이 지는 시기를 알아보기 위함
			//피는 시기가 전에 검사했던거보다 크면 텀이나기때문에 break한다.
			if (flower[j].first > date){ 
					break;
			}//피는 날짜가 기준날짜보다 크다면 break;
			
			if (temp < flower[j].second) {
				temp = flower[j].second;//지는 시기가 제일 최대인걸 temp에 저장
				flag = true;
				i=j;
			}
		}	
		if (flag) {//최대값이 저장됐으면, date값 갱신
				date = temp;
				ans++;
			}
		else {
			cout << 0 << endl;
			return 0;
		}
	}
	cout << ans << endl;
	return 0;
}

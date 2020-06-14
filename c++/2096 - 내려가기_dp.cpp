#include <iostream>
using namespace std;



int main() {
	int n;
	cin >> n;
	
	int dp1[3][2];//min, max저장
	int dp2[3][2];
	
	for(int i = 0; i<3; i++){
		cin >> dp1[i][0];
		dp1[i][1] = dp1[i][0];
	}
	
	for(int i = 1; i < n; i++){
		for(int j = 0 ; j<3; j++){
			int tmp;
			cin >> tmp;
			
			if(j == 0){
				dp2[j][0] = min(dp1[j][0], dp1[j+1][0]) + tmp; //최소값
				dp2[j][1] = max(dp1[j][1] , dp1[j+1][1]) + tmp;
			}else if(j==1){
				dp2[j][0] = min(dp1[j-1][0], min(dp1[j][0], dp1[j+1][0])) + tmp; //최소값
				dp2[j][1] = max(dp1[j-1][1], max(dp1[j][1], dp1[j+1][1])) + tmp;
			}else if(j==2){
				dp2[j][0] = min(dp1[j][0], dp1[j-1][0]) + tmp; //최소값
				dp2[j][1] = max(dp1[j][1] , dp1[j-1][1]) + tmp;
			}
			
		}
		
		for(int j = 0; j < 3; j++){
			dp1[j][0] = dp2[j][0];
			dp1[j][1] = dp2[j][1];
		}
	}
	
	cout << max(dp1[0][1], max(dp1[1][1], dp1[2][1])) << " " << min(dp1[0][0], min(dp1[1][0], dp1[2][0]));
	return 0;
}

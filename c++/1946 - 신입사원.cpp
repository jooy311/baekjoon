#include <iostream>
#include <cstring>
#include <string>
#include <algorithm>
#include <utility>
#include <vector>
using namespace std;

int main() {
   int t, n;
   cin >> t;
   
   while(t--){
      cin >> n;//지원자 수를 받아온다
      int resume; int interview;
      int *arr = new int[n+1];
      memset(arr, 0, sizeof(n+1));
      
      for(int i=1; i<=n; i++){
    	cin >> resume >> interview;
    	arr[resume] = interview;
    	//cout << arr[resume] << " ";
      }
      int cnt = n;
      for(int i=n; i>1; i--){
      	for(int j=i-1; j>=1; j--){
    		if(arr[i] < arr[j]){//기준학생이 비교대상보다 우선순위일때
      			continue;
      		}else{
      			cnt--;
      			break;
      		
      		}
      	}
      }
      cout << cnt << "\n";
   }
   return 0;
}

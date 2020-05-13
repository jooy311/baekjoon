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
      vector<vector<int>> v;
      
      for(int i=0; i<n; i++){
      	vector<int> vv;
      	int s,m;
      	cin >> s >> m;
      	//한 지원자의 서류,면접의 순위를 벡터에 담는다
      	vv.push_back(s);
      	vv.push_back(m);
      	v.push_back(vv);
      	//vv.clear();
      }
     
     sort(v.begin(), v.end());
	 int cnt = n;
      for(int i=n-1; i >=0; i--){
      //	cout << v[i] << " ";
     	for(int j=i-1; j >=0; j--){
     		//cout << v[i].at(1);
     		if(j==i) continue;
     		if(v[i][1] > v[j][1]){//기준점의 인터뷰순위가 더 낮아서 -> 탈락
     			cnt--;
     			i--;
     		} else if(v[i][1] < v[j][1]){//기준점의 인터뷰순위가 더 높은경우
     			//cout << i << endl;
     			continue;
     		}
     	}
     }
      cout << cnt << "\n";
   }
   return 0;
}

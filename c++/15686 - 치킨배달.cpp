#include <iostream>
#include <cstring>
#include <vector>
#include <utility>
#include <queue>
#include <algorithm>

using namespace std;

int n, m;
int ans =1234567;
pair<int, int> p;
vector<pair<int, int>> home;

//dfs에서 골라지는 치킨집의 조합을 고른다
//->골라진 치킨집에서 bfs함수를 돌린다.
//->각 집에서 골라진 치킨집의 거리 중 최소값을 기억하고 있다가 다 더한다.
void bfs(bool *check, vector<pair<int, int>> v, int minn){
   int sum = 0;
   for(int k=0; k<home.size(); k++){
   	  int min_dist = 12345678;
      int x = home[k].first;
      int y = home[k].second;
      
      for(int i=0; i<v.size(); i++){//팝해진 한집에서 골라진 치킨집의 거리차이구하기
    	if(check[i] == true) {
      	int distance = abs(x-v[i].first) + abs(y-v[i].second);
      	min_dist = min(min_dist, distance);//골라진 치킨집과의 거리중 최소 거리선택
      	}
      }
      sum += min_dist;//그 최소거리들의 합
   }
   ans = min(sum, ans);
}
void dfs(int depth, int idx , vector<pair<int, int>> v, int *tmp, bool *check,int minn){
    if(idx > v.size()) return;
    
    if(depth == m){//치킨집이 m개골라지는 조합을 구하기 위한 재귀
       bfs(check,v,minn);//여기에 골라진 치킨의 조합이 들은 tmp배열을 넘겨준다
      return ;
   }
  
  	check[idx] = true;
   	dfs(depth +1, idx+1 , v,tmp,check,minn);//선택한경우
   	check[idx] = false;
   	dfs(depth, idx+1 , v,tmp,check,minn);	//선택하지 않은경우
}

int main() {
   int minn = 1234567;//최소값 출력하기 위한 변수

   cin >> n >> m;
   int **map = new int *[n];//각 장소의 위치를 받아오기 위해 2차원배열선언
   memset(map,0,sizeof(n));
   
   bool *check = new bool[n];
   memset(check, false, sizeof(n));
   
   for(int i=0; i<n; i++){
      map[i] = new int[n];
      memset(map[i],0,sizeof(int) *n);
   }
   
   vector<pair<int, int>> v;
   
   for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
         cin >>    map[i][j];
         if(map[i][j] == 1){
             home.push_back(make_pair(i,j));
         }
         if(map[i][j] == 2){//치킨집의 좌표를 벡터에 저장
            v.push_back(make_pair(i,j));
         }
      }
   }
   int *tmp = new int[v.size()];
   memset(tmp, 0, sizeof(v.size()));
   
   dfs(0,0,v,tmp,check, minn);
   cout << ans <<"\n";
   return 0;
}

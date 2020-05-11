#include <iostream>
#include <cstring>
using namespace std;

int cnt = 0;
int *tmp;

void dfs(int depth, int sum, int idx ,int n, int s, int *arr, bool *check){
   if(depth <= n){
      if(sum == s){
         cnt++;
      }
      //return;
   }
   if(depth > n) return;
   
   for(int i=idx; i<n; i++){//조합을 구하는 거니까 i의 시작은 idx
      if(check[i] == false){
         check[i] = true;
         dfs(depth +1, sum + arr[i], i, n,s,arr,check);
         check[i] = false;//백트래킹
      }
   }
   return;
}

int main() {
   int n, s;
   cin >> n >> s;
   
   int *arr = new int[n];
   memset(arr, 0, sizeof(n));
   
   bool *check = new bool[n];
   memset(check, false, sizeof(n));
   
   tmp = new int[n];
   memset(tmp, 0, sizeof(n));
   
   for(int i=0; i<n; i++){
      cin >> arr[i];
   }
   dfs(0,0,0,n,s,arr,check);
   if(s==0) cnt-=1;/////////////////////이거때문에 시간잡아먹음(공집합)
   cout << cnt << "\n";
   return 0;
}

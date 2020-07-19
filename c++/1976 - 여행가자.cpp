#include <iostream>
#include <vector>
using namespace std;

vector<int> parent;//부모노드를 받을 벡터
vector<int> path;

int arr[201][201];

//find 연산
int find(int node){
	if(node == parent[node]) return node; //자기자신이 부모노드라면 그 노드 반환
	else {
		int other = find(parent[node]);//node의 부모노드를 찾아서 other에 저장
		parent[node] = other; //노드를 부모노드로 바꿔줌
		return other;
	}
	return parent[node];
}

///유니온 연산
void union_xy(int x, int y){
	x = find(x);//find연산으로 각각의 root를 찾아준 후
	y = find(y);
	
	if(x == y) return;
	
	if(x < y) parent[y] = x;
	else parent[x] = y;
}
	
int main() {
	int N,M;
	cin >> N >> M;
	
	parent = vector<int> (N+1,0);
	path = vector<int> (M+1,0);
	
	for(int i = 1; i <= N; i++)
		parent[i] = i; //일단 부모노드에 자기자신 저장
	
	for(int i = 1; i <= N; i++){
		for(int j = 1; j <= N; j++){
			cin >> arr[i][j];
			
			if(arr[i][j]== 1) union_xy(i,j);
		}
	}
	
	for(int i = 1; i <= M; i++) cin >> path[i];
	
	bool check = true;
	for(int i = 1; i < M; i++){
		if(find(path[i]) != find(path[i+1])){
			check = false;
			break;
		}
	}
	
	if(check) cout << "YES\n";
	else cout << "NO\n";
	
	return 0;
}

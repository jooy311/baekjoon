import java.util.*;
import java.lang.*;
import java.io.*;
import java.math.*;

class Ideone
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int m = Integer.parseInt(st.nextToken());
		
		int[][] next = new int[n+1][n+1];//거리를 저장하는 배열
		int[][] dist = new int[n+1][n+1];//최소 비용을 저장
		
		//초기화
		for(int i =1; i<=n; i++){
			for(int j=1; j<=n; j++){
				next[i][j] = -1;//-1로 초기화
				if(i == j) continue;//시작지점과 도착지점이 같은 경우는 없으므로
				dist[i][j] = 100000;//최대값으로 일단 초기화(최소값을 받아야하므로)
			}
		}
		
		//버스 정보를 받아온다
		for(int i=0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			next[s][e] = s;
			dist[s][e] = Math.min(dist[s][e] , c);//원래있던 값 vs c 중 최소값을 저장한다
		}
		
		//중간지점 갱신
		//중간지점
		for(int k=1; k<=n; k++){
			//시작지점
			for(int i=1; i<=n; i++){
				//마지막지점
				for(int j = 1; j<=n; j++){
					if(dist[i][j] > dist[i][k] + dist[k][j]){
						dist[i][j] = dist[i][k] + dist[k][j]; //최소비용으로 갱신
						next[i][j] = next[k][j];//중간지점 갱신
					}
				}
			}
		}
		
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				System.out.print(dist[i][j] + " ");
			}
			System.out.println();
		}
		
		for(int i=1; i<=n; i++){
			for(int j=1; j<=n; j++){
				//i에서 j로 갈수 없는 경우
				if(i==j || next[i][j] == 0) System.out.println(0);//0을출력하고
        
				else{//갈 수 있는 경우에는 그 경로를 출력하면됨
					Stack<Integer> path = new Stack<>();//스택을 이용
					int pre = j;
					path.push(j);//끝점을 스택에 일단 푸쉬
					while(i != next[i][pre]){//결국 시작지점과 같아질 때 까지
						pre = next[i][pre];//끝점에 들어있는 값이 중간지점이 되므로 그 지점을 pre로 받아옴
						path.push(pre);//그 중간지점도 스택에 푸쉬
					}
					System.out.print(path.size()+1 + " ");//시작지점을 빼고 스택에 푸쉬했으므로 +1을 한 값을 출력
					System.out.print(i + " ");//시작점 먼저 출력
					while(!path.isEmpty())//스택이 비어있을 때까지
						System.out.print(path.pop() + " ");//팝을 하면서 출력
					System.out.println();
				}
			}
		}		
	}
}

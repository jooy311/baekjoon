import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	static ArrayList<Integer> seq = new ArrayList<Integer>();
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//가수의 수
		int m = Integer.parseInt(st.nextToken());//피디의 수
		
		int[] indegree = new int[n+1];//차수 설정을 위한 배열
		ArrayList<Integer>[] arr = (ArrayList<Integer>[]) new ArrayList[n+1];//간선을 만들기 위한 배열
		ArrayList<Integer> seq = new ArrayList<Integer>();
		
		for(int i=1; i<n+1; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		for(int i=1; i<=m; i++) {
			Queue<Integer> q = new LinkedList<Integer>();//한피디가 맡은 가수의 명수를 몰라서 큐를 일단 만들고
			st = new StringTokenizer(br.readLine());
			int size = Integer.parseInt(st.nextToken());
			while(size-- >0)
				q.add(Integer.parseInt(st.nextToken()));//여기 큐에 가수명수 만큼 들어가나?
			
			while(!q.isEmpty()) {
				if(q.size()==1) break;
				int cur_singer = q.poll();
				int next_singer = q.peek();
				arr[cur_singer].add(next_singer);
				indegree[next_singer]++;//차수 설정(화살표 받은애)
			}
		}
		
		seqq(n,indegree,arr);
	}
	
	public static void seqq(int n, int[] indegree, ArrayList<Integer>[] arr) {
		Queue<Integer> qq = new LinkedList<Integer>();
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0) {//피디가 골라오지 않은 가수들도 어쨌든 차수가 0이기 때문에 큐에 들어가게 된다.
				qq.add(i);//0인애들을 일단 다 큐에 넣는다
			}
		}
		
		while(!qq.isEmpty()) {//q가 빌때가지
			int cur = qq.poll(); //큐에있는 첫번째 차수가0인애
			seq.add(cur);
			for(int i : arr[cur]) {
				indegree[i]--;//차수가 0인 가수와 연결되어있는 가수 다 차수 감소시킴
				
				if(indegree[i] == 0) //끊어내고 또 차수 0인애들 있으면
					qq.add(i);//큐에 넣는다
			}
		}//큐가 빌때까지 반복
		if(seq.size() != n) System.out.println("0");//seq의 사이즈가 가수 명수보다 작으면 0출력(안되는경우는 어찌됐든 qq에 들어가지 못하므로
		else{//그게아니면 순서대로 출력~
			for(int i=0; i<seq.size(); i++){
				System.out.println(seq.get(i));
			}
		}
	}
}

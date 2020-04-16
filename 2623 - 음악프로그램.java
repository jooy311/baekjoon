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
		//ArrayList<Integer> seq = new ArrayList<Integer>();
		
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
				//System.out.println(cur_singer + ", " + next_singer);
				arr[cur_singer].add(next_singer);
				indegree[next_singer]++;//차수 설정(화살표 받은애)
			}
		}
		
		//while(seq.size() <= n) {
		
		String str = seqq(n,indegree,arr);
		if(str.equals("0"))
			System.out.println(str);
		else {
			String[] s = str.split("");
			for(int i=0; i<s.length; i++)
				System.out.print(s[i] + "\n");
		}
	}
	
	public static String seqq(int n, int[] indegree, ArrayList<Integer>[] arr) {
		String str ="";
		Queue<Integer> qq = new LinkedList<Integer>();
		for(int i=1; i<=n; i++) {
			if(indegree[i] == 0) {
				qq.add(i);//0인애들을 일단 다 큐에 넣는다
			}
		}
		
		while(!qq.isEmpty()) {//q가 빌때가지
			int cur = qq.poll(); //큐에있는 첫번째 차수가0인애
			//System.out.println(cur);
			//seq.add(cur);
			str = str + cur;
			for(int i : arr[cur]) {
				indegree[i]--;
				
				if(indegree[i] == 0) 
					qq.add(i);				
			}
			
		}
		
		for(int i=0; i<=n; i++) {
			if(indegree[i] != 0) {
				str = "0";
			}
		}
		return str;
	}
}

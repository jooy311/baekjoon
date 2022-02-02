import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{	static class Pair implements Comparable<Pair> {
		int num;
		int cnt;
		boolean flag = false;
		
		public Pair(int num, int cnt){
			this.num = num;
			this.cnt = cnt;
			this.flag = flag;
		}
		public int getNum() {
			return this.num;
		}
		@Override
		public int compareTo(Pair p) {
			if(this.num < p.getNum()) {
				return -1;
			}else if(this.num > p.getNum()) {
				return 1;
			}
			return 0;
		}
	}
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int n = Integer.parseInt(st.nextToken());//수의 개수
		int m = Integer.parseInt(st.nextToken());
		
		int tmp = n;
		boolean flag = false;
		
		ArrayList<Pair> arr = new ArrayList<Pair>();
		
		st = new StringTokenizer(br.readLine());
		while(tmp-- > 0){
			int k = Integer.parseInt(st.nextToken());
			if(arr.size() == 0)
				arr.add(new Pair(k, 1));
			else{
				for(int i=0; i<arr.size(); i++){
					if(arr.size() == n) break;
					if(arr.get(i).num != k)//arr사이즈 만큼 도는데 이전에 없던값이면 넣고
						flag = true;
					else if(arr.get(i).num == k){//이전에 있던 값이면
						flag = false;
						int c = arr.get(i).cnt;
						c++;
						arr.get(i).cnt = c;
						break;
					}
				}
				if(flag == true){
					arr.add(new Pair(k, 1));
				}
			}
		}
	
		Collections.sort(arr);
		dfs(0,0,n,arr,m,"");
		
	}
	public static void dfs(int idx, int depth, int n, ArrayList<Pair> arr, int m, String str){
		if(depth == m){
			System.out.print(str);
			System.out.println();
			return;
		}
		
		for(int i = idx; i<arr.size(); i++){
			if(arr.get(i).cnt >= -1){
				arr.get(i).cnt -= 1;
				dfs(i, depth + 1, n, arr, m, str + arr.get(i).num + " ");
				arr.get(i).cnt += 1;
			//	if(arr.get(i).cnt > 0) arr.get(i).cnt += 2;
			}
		}
	}
}

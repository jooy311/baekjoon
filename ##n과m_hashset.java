//문제점 : 자바에서는 set이 자동정렬이 안됨.
// C++로 한다면 set과 벡터를 이용해서 가능
import java.util.*;
import java.lang.*;
import java.io.*;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone
{
	static int[] tmp;	
	static Set<ArrayList<Integer>> hs;
	public static void main (String[] args) throws java.lang.Exception
	{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());//수의 개수
      int m = Integer.parseInt(st.nextToken());
      
      int[] arr = new int[n];
      tmp = new int[n];
      hs = new HashSet<>();
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++){
      	int tmp = Integer.parseInt(st.nextToken());
      	arr[i] = tmp;
      }
      Arrays.sort(arr);
      dfs(0,0,n,m,arr);
      
      for(ArrayList a : hs){
      //	System.out.println(a.size());
      	for(int i = 0; i<a.size(); i++)
    		System.out.print(a.get(i) + " ");
    	System.out.println();
      }
	}
	
	public static void dfs(int idx, int depth, int n, int m, int[] arr){
		if(depth == m){
			ArrayList<Integer> a = new ArrayList<Integer>();
			for(int i=0; i<depth; i++){
				a.add(tmp[i]);
			}
			hs.add(a);
			return;
		}
		
		for(int i = idx; i < n; i++){
			tmp[depth] = arr[i];
			dfs(i, depth + 1, n, m, arr);
		}
	}
}

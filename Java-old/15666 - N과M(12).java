import java.util.*;
import java.lang.*;
import java.io.*;

class solution2
{
	static int[] tmp;	
	//static Set<ArrayList<Integer>> hs;
	public static void main (String[] args) throws java.lang.Exception
	{
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());//수의 개수
      int m = Integer.parseInt(st.nextToken());
      
      int[] arr = new int[n];
      tmp = new int[n];
      //hs = new HashSet<>();
      
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++){
      	int tmp = Integer.parseInt(st.nextToken());
      	arr[i] = tmp;
      }
      Arrays.sort(arr);
      dfs(0,0,n,m,arr);
      
	}
	
	public static void dfs(int idx, int depth, int n, int m, int[] arr){
		if(depth == m){
			for(int i=0; i<depth; i++){
				System.out.print(tmp[i] + " ");
			}
			System.out.println();
			return;
		}
		
		for(int i = idx; i < n; i++){
			tmp[depth] = arr[i];
			dfs(i, depth + 1, n, m, arr);
      //같은것이 나오는동안 스킵
			while(i <n-1 &&arr[i] == arr[i+1]){//앞에 조건 안들어가서 자꾸 런타임에러났음.
				i++;//만약 arr에 9 7 9 1 9 9 9 가 있다면 index 4부터는 while문에 걸려서 계속 i++이 될것임
            //그러다보면 i<n-1에 걸리게 되고while문 탈출 -> 중복되는 수열이 나오지 않게됨
			}
		}
	}
}

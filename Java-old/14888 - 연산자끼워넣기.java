import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
   static long[] arr;
   static long max = -1000000000;
   static long min = 1000000000;
   static int plus=0, minus=0, mul=0, div=0;
   static int n;
   public static void main (String[] args) throws java.lang.Exception
   {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      n = Integer.parseInt(st.nextToken());//n개의 수
      //연산자는 n-1개 순서는 쁠,마, 곱, 나
      arr = new long[n];
      st = new StringTokenizer(br.readLine());
      for(int i=0; i<n; i++){
         arr[i] = (Long.parseLong(st.nextToken()));
      }
 
      st = new StringTokenizer(br.readLine());
      plus = Integer.parseInt(st.nextToken());
      minus = Integer.parseInt(st.nextToken());
      mul = Integer.parseInt(st.nextToken());
      div = Integer.parseInt(st.nextToken());
      
      dfs(1,0,0,0,0,arr[0]);
      
      System.out.println(max);//최대값 출력
      System.out.println(min);//최소값 출력
   }
   
   public static void dfs(int idx, int p, int m, int mu, int di, long total){
   		if(idx == n){
   			max = Math.max(max, total);
   			min = Math.min(min, total);
   			return;
   		}
   		
   		if(p < plus){
   			dfs(idx+1, p+1, m, mu, di, total + arr[idx]);
   		}if(m < minus){
   			dfs(idx+1, p, m+1, mu, di, total - arr[idx]);
   		}if(mu < mul){
   			dfs(idx+1, p, m, mu+1, di, total * arr[idx]);
   		}if(di < div){
   			dfs(idx+1, p, m, mu, di+1, total / arr[idx]);
   		}
   }
}

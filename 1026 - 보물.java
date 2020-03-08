import java.util.*;
import java.util.regex.Pattern;
import java.io.*;

//https://www.acmicpc.net/problem/1026

public class Main {
   public static void sum(int[] A, int[] B, int n) {
      int sum = 0;
      for (int i = 0; i < n; i++) {
         sum += A[i] * B[i];
      }
      System.out.println(sum);
   }

   public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());// a,b배열의 길이
      int[] A = new int[n];
      int[] B = new int[n];
      ArrayList<Integer> a = new ArrayList<Integer>();
      ArrayList<Integer> b = new ArrayList<Integer>();
      ArrayList<Integer> b_1 = new ArrayList<Integer>();
      Deque<Integer> d = new LinkedList<>();

      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
         a.add(Integer.parseInt(st.nextToken())); // 나중에 재배열 해줄거기 때문에 일단 리스트에 담음
      }
      st = new StringTokenizer(br.readLine());
      for (int i = 0; i < n; i++) {
         b.add(Integer.parseInt(st.nextToken()));
      }

      Collections.sort(a);// 오름차순
      Collections.sort(b, Comparator.reverseOrder()); // 내림차순
      for (int i = 0; i < n; i++) {
    	  A[i] = a.get(i);
    	  B[i] = b.get(i);
      }
      sum(A, B, n);
   }
}

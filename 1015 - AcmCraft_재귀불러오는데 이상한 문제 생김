package exam;

import java.util.*;
import java.io.*;

class Pair {
   // 건설 시간과 그 건물을 짝꿍으로 만드는거임
   public int gunmul;
   public int sigan;

   Pair(int gunmul, int sigan) {
      this.gunmul = gunmul;
      this.sigan = sigan;
   }
}

public class ACM {

   public static int acm(int goal, ArrayList<Integer>[] a, int[] d, int time, int n) { // n은 건물수
      Stack<Pair> arr = new Stack<Pair>();

      if (goal == 1)
         return time;

      for (int i = 1; i < goal; i++) {
         for (int j = 0; j < a[i].size(); j++) {
            if (goal == a[i].get(j).intValue()) { // 목적지 건물번호를 갖고있는 배열을 찾아(여러개 일 수 있음)
               arr.add(new Pair(i, d[i]));
            }
         }
      }

      if (arr.size() > 1) { // goal을 가지고 있는 건물이 여러개라면
         for (int i = 1; i < arr.size(); i++) {
            Pair gijun = arr.get(0); // 기준설정
            if (gijun.sigan > arr.get(i).sigan) {
               time += gijun.sigan;// 그것들 중 제일 건설 시간이 오래걸리는거를 선택
               acm(gijun.gunmul, a, d, time, n);
            } else {
               gijun = arr.get(i);
               time += arr.get(i).sigan;// 그것들 중 제일 건설 시간이 오래걸리는거를 선택
               acm(arr.get(i).gunmul, a, d, time, n);
            }
         }
      } else if (arr.size() == 1) { // goal을 가지고 있는 건물이 하나뿐이라면
         time += arr.get(0).sigan;
         acm(arr.get(0).gunmul, a, d, time, n);
      } else {
         return time;
      }
      System.out.println(time);
      return time;
   }// 재귀함수 끝

   public static void main(String args[]) throws IOException {
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st = null; // ★일단 객체 선언
      int t = Integer.parseInt(reader.readLine()); // 테스트케이스

      /* 테스트 케이스 시작 */
      while (t-- > 0) {
         // 한 줄에 int형 을 두개 받고 공백을 기준으로 n과 k를 나누고 싶음
         st = new StringTokenizer(reader.readLine());// 1.Tokenizer객체를 하나 받고
         int n = Integer.parseInt(st.nextToken()); // 건물의 개수 - 차례로 토큰 하나씩 받아온다
         int k = Integer.parseInt(st.nextToken());// 규칙의 개수

         ArrayList<Integer>[] a = (ArrayList<Integer>[]) new ArrayList[n + 1];
         int[] d = new int[n + 1]; // 건물 건설 시간 배열

         // 각 건물에 연결되어 있는(건설순서) 순서 정해주기 위한
         for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<Integer>();
         }

         st = new StringTokenizer(reader.readLine()); // 각 건물의 건설시간 받기
         for (int i = 1; i <= n; i++) {
            d[i] = Integer.parseInt(st.nextToken());
         }

         for (int i = 1; i <= k; i++) {
            st = new StringTokenizer(reader.readLine()); // x->y 규칙을 받기위한 stringTokenizer
            a[Integer.parseInt(st.nextToken())].add(Integer.parseInt(st.nextToken())); // 제대로 작동합니다.
         }

         st = new StringTokenizer(reader.readLine());
         int w = Integer.parseInt(st.nextToken()); // 목적지 건물을 적어주세영

         int time = 0;
         // 재귀함수 호출
         time = acm(w, a, d, time, n);
         System.out.println(time + d[w]);
      }
   }// while문 끝
}

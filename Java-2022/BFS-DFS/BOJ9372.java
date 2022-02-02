import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ9372 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N , M, t = 0;
        t = Integer.parseInt(st.nextToken());//test case
        while(t-- > 0){//test case 만큼 도는 동안
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken()); //국가의 갯수
            M = Integer.parseInt(st.nextToken()); //비행기 종류 갯수
            
            ArrayList<Integer>[] list = new ArrayList[N+1]; //연결되어있는 국가를 받기 위한 배열
            boolean[] check = new boolean[N+1];

            for(int i = 1; i <= N; i++){
                list[i] = new ArrayList<>();//리스트 생성
            }
            for(int i = 0; i < M ; i++){
                
                st = new StringTokenizer(br.readLine());

                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                list[a].add(b);
                list[b].add(a);
            }

            int result = 0;
            Queue<Integer> queue = new LinkedList<Integer>();
            queue.add(1);
            check[1] = true;
            while(!queue.isEmpty()){
                result++;
                int next = queue.poll();

                for(int i = 0; i < list[next].size(); i++){
                    if(!check[list[next].get(i)]){
                        check[list[next].get(i)] = true;
                        queue.add(list[next].get(i));
                    }
                }
            }
            System.out.println(result-1);
            
        }
        
    }
}
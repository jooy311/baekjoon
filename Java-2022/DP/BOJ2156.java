import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2156 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine()); 

        int[] wine = new int[n];
        int[] dp = new int[n];

        for(int i = 0; i < n; i++){
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = wine[0]; //n = 1만 있는 경우

        //아...자꾸 런타임에러 나길래 생각해보니, n = 1인 경우 아래조건이 들어가야함.
        if(n > 1){
            dp[1] = wine[0] + wine[1]; //포도주가 2개 있는 경우
        }
        
        if(n > 2){
            dp[2] = Math.max(dp[1], Math.max(wine[0]+wine[2], wine[1] + wine[2]));
        
            for(int i = 3; i < n; i++){
                dp[i] = Math.max(dp[i-2] + wine[i], Math.max(dp[i-1], dp[i-3] + wine[i-1] + wine[i]));
            }
        }
        
        System.out.println(dp[n-1]);
    }
}

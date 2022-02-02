import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ1912 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] array = new int[n];
        for(int i = 0; i < n; i++){
            array[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[n];
        dp[0] = array[0];
        func(array, dp);
    }

    public static void func(int[] array, int[] dp){
        int max = array[0];
        for(int i = 1; i < array.length; i++){
            dp[i] = Math.max(array[i], dp[i-1] + array[i]);
            max = Math.max(max, dp[i]);
        }
        System.out.println(max);
    }
}

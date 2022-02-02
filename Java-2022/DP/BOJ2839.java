import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ2839 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); //설탕가게에 배달해야하는 kg수

        int cnt = 0;
        while(true){//3의 배수거나,그 이외
            if(N % 5 == 0){
                System.out.println((N / 5) + cnt);
                break;
            }
            if(N < 0 ){
                System.out.println(-1);
                break;
            }
            cnt++;
            N -= 3;
        }
        
    }
}

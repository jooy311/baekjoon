import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1654 {
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       // int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        
        int[] lansun = new int[K];
        
        //Arrays.sort(lansun);
        long top = 1000000;//Integer.MAX_VALUE;//lansun[K-1];
        long bottom = 1;

        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            lansun[i] = Integer.parseInt(st.nextToken());
            if(top > lansun[i]) top = lansun[i];
        }
        long result = 0;
        long mid = 0;

        while(top >= bottom){
            mid = (top + bottom)/2;  
            result = 0;          
            //System.out.println("top : "+top+ ", bottom : " + bottom+", mid : "+mid);
            for(int height : lansun){
               // if(mid != 0){
                    result += height/mid;
                  //  System.out.print(result + " ");
               // }
            }//System.out.println();
            
            //if(result == N) break;
            if(result >= N){
                bottom = mid+1;
            }else{
                top = mid-1;
            }
        }
        System.out.println(top);
    }
}
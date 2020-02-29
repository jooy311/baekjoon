import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public class Main {

	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; //객체생성
    
		String pattern = "(100+1+|01)+";//패턴생성
    
    // 테스트케이스
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());

		while (t-- > 0) {
      //스트링을 받음
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
      //해당 패턴이 맞는지 아닌지 체크하는 변수
			boolean check = Pattern.matches(pattern, s);
      
			if (check == false)
				System.out.println("NO");
			else
				System.out.println("YES");
		}
	}
}

import java.util.*;
import java.lang.*;
import java.io.*;
//처음에 피보나치 재귀로 풀었더니 시간초과남

class Main {
	static int d, k;
	static int A, B;// 첫째, 둘째날 떡의 개수
	static int[] tmp;
	static int[] sum;

	public static void main(String[] args) throws java.lang.Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		d = Integer.parseInt(st.nextToken());// 할머니가 산에서 내려온 날
		k = Integer.parseInt(st.nextToken());// 그날 할머니가 호랑이한테 준 떡의 개수
		tmp = new int[d + 1];
		sum = new int[d + 1];
		tmp[1] =1; tmp[2] =1; 
    
		int b = count_a(d-1);
		int a = tmp[d - 2];// A앞에 붙는 상수를 구하기 위해
		System.out.println("b " +b + " a " + a);

		// a*A + b*B = k가 되는 경우의 수를 구하고 첫번째 걸러지는 숫자들이 있으면 그냥 그거 출력
		for (int i = 1; i <= k; i++) {
				int j = k - (a*i) ;
				if (j <= 0)continue; 
				if(j % b != 0 || i > j/b) continue;//범위 설정
				B = j/b;
				A = i;
		}
		System.out.println(A);
		System.out.println(B);
	}

	public static int count_a(int day) {// 매개변수는 날짜수
		if (day == 1 || day == 2) {//이거 사실 굳이 안해도됨 위에서 설정해놔서
			tmp[day] = 1;
			return 1;
		}
		if (tmp[day] != 1) {
			tmp[day] = count_a(day - 1) + count_a(day - 2);
		}
		return tmp[day];
	}
}

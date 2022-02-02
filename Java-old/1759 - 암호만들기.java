import java.util.*;
import java.io.*;

//https://www.acmicpc.net/problem/2252\
//
class Pair2 {
	int idx;
	String alpha;

	public Pair2(int idx, String alpha) {
		this.idx = idx;
		this.alpha = alpha;
	}
}

public class solution2 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st;
		st = new StringTokenizer(br.readLine());

		int l = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		Pair2[] p = new Pair2[c];
		String[] s = new String[c];
		String[] pp = new String[l];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < c; i++)
			s[i] = st.nextToken();

		Arrays.sort(s);

		for (int i = 0; i < c; i++) {
			p[i] = new Pair2(i, s[i]);
		}

		choose(0, c, pp, p, 0, l);

	}

//한개의 모음과 최소 두개의 자음
	public static void check(String[] pp, int l) {
		int ja = 0;
		int mo = 0;
		
		for (int i = 0; i < l; i++) {
			if (pp[i].equals("a") || pp[i].equals("e") || pp[i].equals("i") || pp[i].equals("o") || pp[i].equals("u")) {
				mo++;
			} else
				ja++;
		}
		
		if (mo >= 1 && ja >= 2) {
			for (int k = 0; k < l; k++) {
				System.out.print(pp[k]);
			}
			System.out.println("");
		}
	}

	public static void choose(int start, int c, String[] pp, Pair2[] p, int depth, int l) {

		if (depth == l) {
			check(pp, l);
			return;
		}

		for (int i = start; i < c; i++) {
			pp[depth] = p[i].alpha;
			choose(i + 1, c, pp, p, depth + 1, l);
		}

	}

}

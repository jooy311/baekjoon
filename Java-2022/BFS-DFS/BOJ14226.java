import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

//private static ArrayList<Integer>[] list; 
class Pairs{
    int screen = 0;
    int clipboard = 0;
    int count = 0;
    public Pairs(int screen, int clipboard, int count){
        this.clipboard = clipboard;
        this.screen = screen;
        this.count = count;
    }
}

public class BOJ14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//StringTokenizer st = new StringTokenizer(br.readLine());
        
        int S = 0;
        S = Integer.parseInt(br.readLine());//S개의 이모티콘을 만들기 위한 시간의 최솟값

        boolean[][] check = new boolean[10001][10001];//화면 이모티콘, 클립보드 이모티콘 방문체크

        //bfs인데 정해진 좌표에서 동서남북방향으로 움직이는 방식과는 조금 다르다
        //움직이는 조건은
        //1. 복사 : 화면, 클립보드 = 화면, 초+1
        //2. 붙여넣기 : 화면 + 클립보드, 클립보드, 초+1
        //3. 삭제 : 화면-1, 클립보드, 초+1

        Queue<Pairs> queue = new LinkedList<Pairs>();
        queue.add(new Pairs(1,0,0));//처음시작은 화면에 하나가 있는 상태
        check[1][0] = true;
        while(!queue.isEmpty()){
            Pairs pairs = queue.poll();
            
            int nowScreen = pairs.screen;
            int nowClipboard = pairs.clipboard;
            int nowCount = pairs.count;

            if(nowScreen == S){//내가 원하는 갯수가 되면 break
                System.out.println(nowCount);
                break;
            }
            
            //복사(조건 필요없다...?)
            //nowClipboard = nowScreen;
            queue.add(new Pairs(nowScreen, nowScreen, nowCount+1));

            //붙여넣기(단, 클립보드가 0개면 안되고, 내가 원하는 갯수S를 넘으면 안되고 들린적있으면 안됨)
            if(!check[nowScreen + nowClipboard][nowClipboard] && nowClipboard > 0 && nowScreen+nowClipboard <= S){
                queue.add(new Pairs(nowScreen + nowClipboard, nowClipboard, nowCount+1));
                check[nowScreen + nowClipboard][nowClipboard] = true;
            }

            //삭제(단, 이동하려는 곳이 방문한적이 없어야하고, 스크린에 아무것도 없으면 안됨)
            if(nowScreen >= 1 && !check[nowScreen-1][nowClipboard]){
                queue.add(new Pairs(nowScreen-1, nowClipboard, nowCount+1));
                check[nowScreen-1][nowClipboard] = true;
            }
            
        }
        
    }
}
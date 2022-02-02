import java.util.*;
import java.lang.*;
import java.io.*;

class Main
{
	public static void main (String[] args) throws java.lang.Exception
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		int t = Integer.parseInt(st.nextToken());//출입기록수
		
		HashMap<String, String> map = new HashMap<String, String>();
		while(t-- >0){
			st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String in_out = st.nextToken();

			if(in_out.equals("enter")){
				//맵안에 같은 사람이 있는지 없는지 체크해야함
				if(map.containsKey(name)==false)//안에없으면
					map.put(name, in_out);//맵에 넣어준다
			}else if(in_out.equals("leave")){
				//leave라는건 반드시 맵안에 그 사람이 있다는 것이니까 
				//그냥 해당 키를 바로 뺀다
				map.remove(name);
			}
		}
		
		TreeMap<String, String> tm = new TreeMap<String, String>(Collections.reverseOrder());//키값을 역순으로 받기 위한 treemap선언
		tm.putAll(map);
		Iterator<String> tmi = tm.keySet().iterator();
    
		while(tmi.hasNext()){
			String str = tmi.next();
			System.out.println(str);
		
		}
			
	}
}

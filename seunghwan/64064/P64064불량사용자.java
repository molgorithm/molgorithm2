import java.util.HashSet;

public class P64064불량사용자 {
	//순열뽑기
	//banned_id랑 비교
	//통과하면 set에 넣기
	//set 갯수 출력
	
	static boolean visit[];
	static String choice[];
	static String g_user_id[];
	static String g_banned_id[];
	static HashSet<HashSet<String>> cases;
	public int solution(String[] user_id, String[] banned_id) {
		visit = new boolean[user_id.length];
		choice = new String[banned_id.length];
		g_user_id = user_id;
		g_banned_id = banned_id;
		cases = new HashSet<HashSet<String>>();
		perm(0);
		return cases.size();
	}
	
	static void perm(int n) {
		if(n == g_banned_id.length) {
			if(check()) {
				HashSet<String> casee = new HashSet<String>();
				for(String s : choice)
					casee.add(s);
				cases.add(casee);
			}
			return;
		}
		for(int i = 0 ; i < g_user_id.length ;i++) {
			if(visit[i])
				continue;
			choice[n] = g_user_id[i];
			visit[i] = true;
			perm(n+1);
			visit[i] = false;
		}
	}
	static boolean check() {
		for(int i = 0 ; i < g_banned_id.length ;i++) {
			if(choice[i].length() != g_banned_id[i].length())
				return false;
			for(int j = 0 ; j < choice[i].length() ; j++) {
				if(g_banned_id[i].charAt(j) == '*')
					continue;
				if(choice[i].charAt(j) != g_banned_id[i].charAt(j))
					return false;
			}
		}
		return true;
	}
}



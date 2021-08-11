import java.util.*;

class Solution {
    public int[] solution(String[][] places) {
        int[] answer = new int[places.length];
        char[][] map = new char[5][];
        
        for(int i=0; i<places.length; ++i){
            for(int j=0; j<5; ++j){
                map[j] = places[i][j].toCharArray();
            }
            answer[i] = isRight(map);
        }
        return answer;
    }
    
    public int isRight(char[][] map){
        for(int i=0; i<5; ++i){
            for(int j=0; j<5; ++j){
                if(map[i][j] == 'P'){
                    if(!check(map, i, j)) return 0;
                }
            }
        }
        
        return 1;
    }
    
    class Point{
        int y, x, count;
        boolean partition;
        Point(int y, int x, int count, boolean partition){
            this.y = y;
            this.x = x;
            this.count = count;
            this.partition = partition;
        }
    }
    public boolean check(char[][] map, int y, int x){
        Queue<Point> q = new LinkedList<>();
        boolean[][] visit = new boolean[5][5];
        int[] dy = {0,0,1,-1};
        int[] dx = {1,-1,0,0};
        
        q.add(new Point(y, x, 0, false));
        visit[y][x] = true;
        
        while(!q.isEmpty()){
            Point p = q.poll();
            if(p.count >= 2) continue;
            for(int i=0; i<4; ++i){
                int ry = p.y + dy[i];
                int rx = p.x + dx[i];
                if(ry < 0 || ry >= 5 || rx < 0 || rx >= 5 || visit[ry][rx]) continue;
                
                if(map[ry][rx] == 'X'){
                    q.add(new Point(ry, rx, p.count + 1, true));
                }else if(map[ry][rx] == 'P' && !p.partition){
                    return false;
                }else{
                    q.add(new Point(ry, rx, p.count + 1, p.partition));
                }
                visit[ry][rx] = true;
            }
        }
        
        return true;
    }
}
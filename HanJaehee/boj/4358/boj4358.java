import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 생태학1 {

    static int total = 0;
    static final int MAX_LEN = 128;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Trie trie = new Trie();
        String name;
        while(true) {
            name = br.readLine();
            if (name == null || name.isEmpty()) break;
            trie.insert(name);
            total++;
        }

        System.out.println(trie.start());
    }

    static class Node{
        int cnt;
        Node[] next;
        boolean isEnd;

        Node(){
            this.next = new Node[MAX_LEN];
            this.isEnd = false;
            this.cnt = 0;
        }
    }

    static class Trie{
        Node head = new Node();
        StringBuilder sb = new StringBuilder();

        void insert(String str){
            Node cur = head;

            for(int i=0; i<str.length(); i++){
                int idx = str.charAt(i);

                if(cur.next[idx] == null)
                    cur.next[idx] = new Node();
                cur = cur.next[idx];
            }

            cur.cnt++;
            cur.isEnd = true;
        }

        String start(){
            search(head, "");
            return sb.toString();
        }

        void search(Node cur, String now){
            if(cur.isEnd){
                sb
                        .append(now)
                        .append(String.format(" %.4f", cur.cnt*100.0/total))
                        .append("\n");
            }

            for(int i=0; i<MAX_LEN; i++){
                if(cur.next[i] == null) continue;
                search(cur.next[i], now + (char)i);
            }
        }
    }
}

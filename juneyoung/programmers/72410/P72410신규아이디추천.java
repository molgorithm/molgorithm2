package programmers;

import java.util.*;

public class 신규아이디추천_2021KAKAO_BLIND_REC {

    public String solution(String new_id) {
        StringBuilder sb = new StringBuilder();
        String id = new_id.toLowerCase()
                .replaceAll("[^a-z0-9-_.]", "")
                .replaceAll("[.]{2,}", ".")
                .replaceAll("^[.]|[.]$", "");
        id = id.length() == 0 ? "a" : id;
        id = id.length() >= 16 ? id.substring(0, 15) : id;
        id = id.replaceAll("[.]$", "");
        sb.append(id);
        for (int i = sb.length(); i < 3; i++)
            sb.append(sb.substring(sb.length() - 1));
        return sb.toString();
    }
}

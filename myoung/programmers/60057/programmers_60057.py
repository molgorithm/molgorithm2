def solution(s):
    if len(s) == 1:
        return 1
    answer = 1000
    for word_len in range(1, len(s)//2+1):
        prev = s[:word_len]
        cnt = 1
        temp_word = ''
        for j in range(word_len, len(s)+word_len, word_len):
            now = s[j:j+word_len]
            if prev == now:
                cnt += 1
            elif prev != now and cnt > 1:
                temp_word += (str(cnt)+prev)
                cnt = 1
                prev = now
            else:
                temp_word += prev
                prev = now
        if cnt > 1:
            temp_word += (str(cnt)+prev)
        answer = min(len(temp_word), answer)
    return answer


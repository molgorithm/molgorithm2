import re


def solution(str1, str2):
    str1, str2 = str1.lower(), str2.lower()

    def make_words(s):
        _dict = {}
        p = re.compile('[a-z]{2}')
        for i in range(len(s) - 1):
            _word = s[i:i + 2]
            if p.match(_word):
                if _word in _dict:
                    _dict[_word] += 1
                else:
                    _dict[_word] = 1
        return _dict

    def cal_answer(dict1, dict2):
        set1, set2 = set(dict1.keys()), set(dict2.keys())

        set_a = set1 & set2
        set_b = set1 | set2

        _a, _b = 0, 0
        for i in set_a:
            _a += min(dict1[i], dict2[i])
        for i in set_b:
            if i in dict1 and i in dict2:
                _b += max(dict1[i], dict2[i])
            elif i in dict1:
                _b += dict1[i]
            else:
                _b += dict2[i]
        try:
            answer = int(65536 * (_a / _b))
        except:
            answer = 65536
        return answer

    return cal_answer(make_words(str1), make_words(str2))

print(solution('E=M*C^2', 'e=m*c^2'))
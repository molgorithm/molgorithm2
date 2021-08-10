import re

def solution(s):
    answer = 0
    str_to_num = {
        'one': '1',
        'two': '2',
        'three': '3',
        'four': '4',
        'five': '5',
        'six': '6',
        'seven': '7',
        'eight': '8',
        'nine': '9',
        'zero': '0'
    }
    for string in str_to_num.keys():
        s = re.sub(string, str_to_num[string], s)
    return int(s)
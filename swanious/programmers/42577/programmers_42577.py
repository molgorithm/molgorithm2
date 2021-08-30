import re

def solution(phone_book):
    phone_book.sort()
    for i in range(len(phone_book) - 1):
        # if re.search(f"^{phone_book[i]}", phone_book[i+1]):
        #     return False
        if phone_book[i+1].startswith(phone_book[i]):
            return False

    return True
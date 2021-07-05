def solution(files):
    new_files = [[file] for file in files]
    for f in range(len(files)):
        file = files[f]
        for i in range(len(file)):
            if file[i].isdigit():
                break
        head = file[:i].lower()
        number = file[i:min(i+5, len(file))]
        for k in range(len(number)):
            if not number[k].isdigit():
                number = number[:k]
                break
        new_files[f].extend([head, int(number)])
    new_files.sort(key=lambda x: (x[1], x[2]))
    return [new_files[i][0] for i in range(len(files))]



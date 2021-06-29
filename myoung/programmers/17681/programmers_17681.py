def solution(n, arr1, arr2):
    def trans_map(arr):
        new_arr = []
        for i in range(n):
            val = ''.join(bin(arr[i]))[2:]
            while len(val) < n:
                val = '0' + val
            new_arr.append(val)
        return new_arr

    def add_map(map1, map2):
        new = []
        for y in range(n):
            line = ''
            for x in range(n):
                if map1[y][x] == map2[y][x] and map1[y][x] == '0':
                    line += ' '
                else:
                    line += '#'
            new.append(line)
        return new

    new1, new2 = trans_map(arr1), trans_map(arr2)
    return add_map(new1, new2)


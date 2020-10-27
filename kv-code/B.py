
def solve(arr):
    a, b, x, y, n = arr
    if ((a-x) > (b-y)):
        if ((b-y) > n):
            return (a-n)*(b-n)
        else:
            return (a*y)
    elif ((b-y) > (a-x)):
        if ((a-x) > n):
            return (b-n)*(a-n)
        else:
            return (b*x)
    else:
        return(a*b)


if __name__ == "__main__":
    T = int(input())
    while(T > 0):
        Arr = list(map(int, input().strip().split()))
        print(solve(Arr))
        T -= 1
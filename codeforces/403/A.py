import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	a = read(int)

	n *= 2

	f = [0 for i in range(2*n)]

	ans = 0
	max_ans = 0
	for x in a:
		if f[x] == 0:
			f[x] = 1
			ans += 1
			if ans > max_ans:
				max_ans = ans
		else:
			assert f[x] == 1
			f[x] = 0
			ans -= 1

	print max_ans

if __name__ == "__main__":
	solve()

import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	a = read(int)

	if n <= 2:
		print 0
		return

	a = sorted(a)
	minv, maxv = a[0], a[-1]

	ans = 0
	for x in a:
		if minv < x and x < maxv:
			ans += 1

	print ans

if __name__ == "__main__":
	solve()

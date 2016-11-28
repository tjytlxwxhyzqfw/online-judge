import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, t = read(int)
	a = read(int)

	ans = 1
	last = a[0]

	if n == 1:
		print 1
		return

	for x in a[1:]:
		if x - last > t:
			ans = 1
		else:
			ans += 1
		last = x

	print ans

if __name__ == "__main__":
	solve()

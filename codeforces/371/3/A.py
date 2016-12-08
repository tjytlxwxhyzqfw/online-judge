import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	l1, r1, l2, r2, k = read(int)
	left = max(l1, l2)
	rght = min(r1, r2)

	if left > rght:
		print 0
		return

	length = rght - left + 1
	if left <= k and k <= rght:
		length -= 1


	print length

if __name__ == "__main__":
	solve()

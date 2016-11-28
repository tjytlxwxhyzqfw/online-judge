import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n = int(read())
	print 2
	if n == 1:
		return

	for i in xrange(2, n+1):
		m = i * (i-1)
		x = i * pow(i+1, 2) - i + 1
		print x

if __name__ == "__main__":
	solve()

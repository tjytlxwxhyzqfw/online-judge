import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	a, b = read(int)
	for i in xrange(10000000):
		if a > b:
			break
		a *= 3
		b *= 2
	print i

if __name__ == "__main__":
	solve()

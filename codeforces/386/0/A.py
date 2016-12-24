import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	a = int(read())
	b = int(read())
	c = int(read())

	a /= 1
	b /= 2
	c /= 4

	k = min([a, b, c])

	print 7 * k

if __name__ == "__main__":
	solve()

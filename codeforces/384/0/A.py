import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, a, b = read(int)
	s = list(read())

	a -= 1
	b -= 1

	if s[a] == s[b]:
		print 0
	else:
		print 1

if __name__ == "__main__":
	solve()

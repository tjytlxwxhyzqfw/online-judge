import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	face = {"T": 4, "C": 6, "O": 8, "D": 12, "I": 20}
	ans = 0

	n = int(read())
	for i in xrange(n):
		s = read()
		ans += face[s[0]]

	print ans
	


if __name__ == "__main__":
	solve()

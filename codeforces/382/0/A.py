import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, m = read(int)
	s = list(read())

	for i in range(n):
		if s[i] in ("G", "T"):
			break

	for j in range(i+1, n):
		if  s[j] in ("G", "T"):
			break

	#print "[%2d, %2d]"%(i, j)

	k = i
	while True:
		if k == j:
			print "YES"
			return

		k += m
		if k >= n:
			print "NO"
			return

		if s[k] == '#':
			print "NO"
			return

if __name__ == "__main__":
	solve()

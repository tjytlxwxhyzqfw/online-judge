import re
import sys
import time

n = 0
s = []

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def compute(s, c):
	x = [s[i] if s[i] != c[i] else 0 for i in range(n)]
	xres = [0, 0]
	for i in x:
		if i == 'r':
			xres[0] += 1
		if i == 'b':
			xres[1] += 1
	x = min(xres) + abs(xres[0]-xres[1])
	return x

def solve():
	global s, n

	n = int(read())
	s = list(read())
	n = len(s)

	r = ['r' if i % 2 == 0 else 'b' for i in range(n)]
	b = ['b' if i % 2 == 0 else 'r' for i in range(n)]

	x = compute(s, r)
	y = compute(s, b)

	#print x, y
	print min(x, y)
	
if __name__ == "__main__":
	solve()

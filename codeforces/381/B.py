import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def solve():
	n, m = read(int)
	a = read(int)

	for i in range(1, n):
		a[i] += a[i-1]

	#print "a: %s"%a

	b = []
	for i in range(m):
		x, y = read(int)
		x -= 1
		y -= 1
		#print "(x, y): %d, %d"%(x, y)
		rght = a[y]
		left = a[x-1] if x > 0 else 0
		b.append(rght-left)

	#print "b: %s"%b

	mood = 0
	for x in b:
		if x > 0:
			mood += x
	print mood

if __name__ == "__main__":
	solve()

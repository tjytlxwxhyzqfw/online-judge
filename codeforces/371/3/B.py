import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

def check(tar, arr):
	b = [abs(x-tar) for x in arr]
	#print "b:%s"%b
	b = set(b)
	assert 0 in b
	return True if len(b) <= 2 else False

def solve():
	n = int(read())
	a = read(int)

	a = sorted(list(set(a)))
	n = len(a)

	if n <= 2:
		print "YES"
		return

	x, y, z = a[0:3]
	x = check(x, a)
	y = check(y, a)
	z = check(z, a)


	print "YES" if any([x, y, z]) else "NO"

if __name__ == "__main__":
	solve()

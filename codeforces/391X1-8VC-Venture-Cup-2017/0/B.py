import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

common = None

def solve():
	n, m = read(int)

	if n != m:
		print "YES" if n > m else "NO"
		return

	poland, enemy = [], []
	for i in range(n):
		poland.append(read())
	for i in range(m):
		enemy.append(read())

	global common
	common = set(poland) & set(enemy)

	ncommon = len(common)
	print "YES" if ncommon % 2 == 1 else "NO"

if __name__ == "__main__":
	solve()

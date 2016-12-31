import re
import sys
import time

def read(t=None):
	string = raw_input()
	return string if t is None else [t(x) for x in string.split()]

orda = ord('a')

def solve():
	s = list(read())
	t = list(read())

	m = [set() for i in range(26)]

	for i in range(len(s)):
		x, y = s[i], t[i]
		xid = ord(x) - ord('a')
		yid = ord(y) - ord('a')
		m[xid].add(yid)
		m[yid].add(xid)

	#for i in range(26):
		#print i, m[i]

	pairs = set()
	for i in range(26):
		x = m[i]
		if len(x) > 1:
			print -1
			return
		if len(x) == 1:
			j = list(m[i])[0]
			if i != j:
				if i > j:
					i, j = j, i
				pairs.add((i, j))

	print len(pairs)
	for x, y in pairs:
		print chr(x+orda), chr(y+orda)

if __name__ == "__main__":
	solve()
